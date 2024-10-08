<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./include/head_login.jsp"></jsp:include>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link href="https://cdn.quilljs.com/1.3.7/quill.snow.css" rel="stylesheet">
<script src="https://cdn.quilljs.com/1.3.7/quill.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/quill-image-resize-module@3.0.0/image-resize.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/fabric.js/4.4.0/fabric.min.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function() {
	
	// HTML에서 subject라는 id를 가진 input 요소를 가져옵니다.
	const inputField = document.getElementById('subject');

	// 드롭다운 메뉴를 생성하여 dropdown-menu 클래스를 추가합니다.
	const dropdownMenu = document.createElement('div');
	dropdownMenu.classList.add('dropdown-menu');

	// input 요소의 부모 노드에 드롭다운 메뉴를 추가합니다.
	inputField.parentNode.appendChild(dropdownMenu);

	// 기존 데이터 배열입니다. 중복 데이터를 포함하고 있습니다.
	const existingData = ['JAVA', 'Javascript', 'JSP', 'Spring', 'JPA', 'CSS', 'Mybatis', 'EL', 'JAVA', 'JSP', 'JSP', 'CSS'];

	// input 요소에 input 이벤트 리스너를 추가합니다.
	inputField.addEventListener('input', function() {
	    // 입력된 값의 양쪽 공백을 제거하고 대문자로 변환합니다.
	    const inputValue = inputField.value.trim().toUpperCase();

	    // 드롭다운 메뉴의 내용을 초기화합니다.
	    dropdownMenu.innerHTML = '';

	    // 입력 값이 비어있으면 드롭다운 메뉴를 숨깁니다.
	    if (inputValue === '') {
	        dropdownMenu.style.display = 'none';
	        return;
	    }

	    // 기존 데이터 배열에서 입력된 값과 일치하는 데이터만 필터링합니다.
	    const matchingData = existingData.filter(item => item.toUpperCase().includes(inputValue));

	    // 필터링된 데이터의 각 항목이 몇 번 중복되는지 세는 함수를 호출합니다.
	    const dataCounts = countDataOccurrences(matchingData);

	    // 각 데이터 항목과 그 개수를 기반으로 드롭다운 옵션을 생성합니다.
	    Object.keys(dataCounts).forEach(item => {
	        const count = dataCounts[item]; // 해당 데이터의 개수를 가져옵니다.
	        const option = document.createElement('div');
	        option.textContent = item + ' (' + count + '문제)'; // 텍스트 내용을 설정합니다.
	        option.classList.add('dropdown-item'); // dropdown-item 클래스를 추가합니다.

	        // 옵션을 클릭하면 input 값에 해당 데이터를 설정하고 드롭다운 메뉴를 숨깁니다.
	        option.addEventListener('mousedown', function() {
	            inputField.value = item;
	            dropdownMenu.style.display = 'none';
	        });

	        // 생성한 옵션을 드롭다운 메뉴에 추가합니다.
	        dropdownMenu.appendChild(option);
	    });

	    // 필터링된 데이터가 존재하면 드롭다운 메뉴를 보여줍니다.
	    if (Object.keys(dataCounts).length > 0) {
	        dropdownMenu.style.display = 'block';
	    } else {
	        dropdownMenu.style.display = 'none';
	    }
	});

	// input 요소가 포커스를 잃으면 일정 시간 후에 드롭다운 메뉴를 숨깁니다.
	inputField.addEventListener('blur', function() {
	    setTimeout(() => {
	        dropdownMenu.style.display = 'none';
	    }, 100);
	});

	// 배열에서 각 데이터 항목이 몇 번 중복되는지 세는 함수입니다.
	function countDataOccurrences(dataArray) {
	    const counts = {};
	    dataArray.forEach(item => {
	        counts[item.toUpperCase()] = counts[item.toUpperCase()] ? counts[item.toUpperCase()] + 1 : 1;
	    });
	    return counts;
	}
	    
	
	// 입력 필드(input)와 해당하는 밑줄 요소를 가져옵니다.
    const titleInput = document.getElementById('title_input');
    const titleUnderline = document.getElementById('title_underline');

    // 사용자가 입력할 때마다 밑줄의 너비를 조정합니다.
    titleInput.addEventListener('input', function() {
        adjustUnderlineWidth(titleInput, titleUnderline);
    });

    // 입력 필드에 포커스가 들어올 때 밑줄의 너비를 초기화합니다.
    titleInput.addEventListener('focus', function() {
        titleUnderline.style.width = '0';
    });

    // 입력 필드에서 포커스가 빠져나갈 때 다시 밑줄의 너비를 조정합니다.
    titleInput.addEventListener('blur', function() {
        adjustUnderlineWidth(titleInput, titleUnderline);
    });

    // 밑줄의 너비를 조정하는 함수
    function adjustUnderlineWidth(input, underline) {
        // 입력된 텍스트의 너비를 가져오는 getTextWidth라는 보조 함수를 사용합니다.
        const textWidth = getTextWidth(input.value, window.getComputedStyle(input).font);
        // 밑줄의 너비를 텍스트의 너비에 맞게 설정합니다.
        underline.style.width = textWidth + 'px';
    }

    // 텍스트의 너비를 계산하여 반환하는 함수
    function getTextWidth(text, font) {
    	// 캔버스 엘리먼트를 가져오거나 새로 생성합니다. getTextWidth.canvas가 존재하지 않으면 새로운 캔버스 엘리먼트를 생성하고,
    	// getTextWidth.canvas에 할당합니다.
        const canvas = getTextWidth.canvas || (getTextWidth.canvas = document.createElement("canvas"));
     	// 캔버스에서 2D 그래픽 컨텍스트를 가져옵니다.
    	const context = canvas.getContext("2d");
    	// 그래픽 컨텍스트에 폰트 스타일을 설정합니다.
        context.font = font;
    	// 지정된 텍스트의 너비를 측정하여 metrics 객체에 저장합니다.
        const metrics = context.measureText(text);
     	// 측정된 텍스트의 너비를 반환합니다.
        return metrics.width;
    }

    // Quill Editor 초기화
    var editor = new Quill('#editor', {
        theme: 'snow',  // snow 테마 사용 (기본)
        placeholder:'내용을 입력해주세요. (이미지는 드래그해서 넣어주세요.)',
        modules: {
            toolbar: [
                [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
                ['bold', 'italic', 'underline'],
                ['link', 'image', 'video'],
                ['clean']
            ],
            imageResize: {
                // 이미지 리사이즈 옵션 설정 (필요에 따라 설정 가능)
                modules: ['Resize', 'DisplaySize', 'Toolbar']
            }
        }
    });

    var commentaryEditor = new Quill('#commentary_editor', {
        theme: 'snow',  // snow 테마 사용 (기본)
    	placeholder:'해설을 입력해주세요. (이미지는 드래그해서 넣어주세요.)',
        modules: {
            toolbar: [
                [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
                ['bold', 'italic', 'underline'],
                ['link', 'image', 'video'],
                ['clean']
            ],
            imageResize: {
                // 이미지 리사이즈 옵션 설정 (필요에 따라 설정 가능)
                modules: ['Resize', 'DisplaySize', 'Toolbar']
            }
        }
    });

    // 이미지를 드래그앤 드랍할 수 있도록 Quill 에디터 설정
    var editorContainer = document.getElementById('editor');
    editorContainer.addEventListener('dragover', function(e) {
        e.preventDefault();
        editorContainer.classList.add('dragover');
    });

    editorContainer.addEventListener('dragleave', function(e) {
        e.preventDefault();
        editorContainer.classList.remove('dragover');
    });

    editorContainer.addEventListener('drop', function(e) {
        e.preventDefault();
        editorContainer.classList.remove('dragover');
        var file = e.dataTransfer.files[0];
        if (file && file.type.startsWith('image/')) {
            var reader = new FileReader();
            reader.onload = function(event) {
                var range = editor.getSelection();
                var index = range ? range.index : editor.getLength();
                editor.clipboard.dangerouslyPasteHTML(index, '<img src="' + event.target.result + '">');
            };
            reader.readAsDataURL(file);
        }
    });
 	
    // 이미지를 드래그앤 드랍할 수 있도록 Quill 해설 에디터 설정
    var commentaryEditorContainer = document.getElementById('commentary_editor');
    commentaryEditorContainer.addEventListener('dragover', function(e) {
        e.preventDefault();
        commentaryEditorContainer.classList.add('dragover');
    });

    commentaryEditorContainer.addEventListener('dragleave', function(e) {
        e.preventDefault();
        commentaryEditorContainer.classList.remove('dragover');
    });

    commentaryEditorContainer.addEventListener('drop', function(e) {
        e.preventDefault();
        commentaryEditorContainer.classList.remove('dragover');
        var file = e.dataTransfer.files[0];
        if (file && file.type.startsWith('image/')) {
            var reader = new FileReader();
            reader.onload = function(event) {
                var range = commentaryEditor.getSelection();
                var index = range ? range.index : commentaryEditor.getLength();
                commentaryEditor.clipboard.dangerouslyPasteHTML(index, '<img src="' + event.target.result + '">');
            };
            reader.readAsDataURL(file);
        }
    });
    
    function handleDrop(e, quillEditor) {
        var files = e.dataTransfer.files;
        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            if (file.type.startsWith('image/')) {
                var reader = new FileReader();
                reader.onload = function(event) {
                    var range = quillEditor.getSelection();
                    var index = range ? range.index : quillEditor.getLength();
                    quillEditor.clipboard.dangerouslyPasteHTML(index, '<img src="' + event.target.result + '">');
                };
                reader.readAsDataURL(file);
            }
        }
    }

    // 파일 선택 버튼 처리
    document.getElementById('file_input').addEventListener('change', function(e) {
        var files = e.target.files;
        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            if (file.type.startsWith('image/')) {
                var reader = new FileReader();
                reader.onload = function(event) {
                    var range = editor.getSelection();
                    var index = range ? range.index : editor.getLength();
                    editor.clipboard.dangerouslyPasteHTML(index, '<img src="' + event.target.result + '">');
                };
                reader.readAsDataURL(file);
            }
        }
    });

    	
    // 저장 버튼 클릭 시 각 에디터의 내용을 hidden input에 설정
    document.getElementById('write_btn').addEventListener('click', function() {
        var editorHtml = editor.root.innerHTML;
        var commentaryHtml = commentaryEditor.root.innerHTML;
        document.getElementById('editorContent').value = editorHtml;
        document.getElementById('commentary_editorContent').value = commentaryHtml;
    });
});
</script>

<style>
    .container {
        display: inline-flex;
        width: fit-content;
        flex-direction: column;
        height: calc(90vh - 8.8rem);
        margin-left: 30px;
    }
    .writeFrm {
        width: fit-content;
        flex-direction: column;
        height: calc(90vh - 8.8rem);
        margin-left: 30px;
    }
    .subject_input {
        display: block;
        background-color: #333333;
        font-size: 1rem;
        padding: 0.33rem 0.66rem;
        width: 5rem;
        height: 100%;
        min-width: 5rem;
        border: none;
        text-align: center;
        transition: all 0.05s ease-in-out;
        border-radius: 10px;
        box-shadow: 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
        color: #ffffff;
    }
    .subject_input::placeholder {
	    color:#b6b6b6;
	    text-align: center;
	}
	.subject_input:hover {
	    animation: tangle 0.25s ease-in-out;
	}
    .subject-hidden-input {
		    visibility:hidden;
		    font-size: 1rem;
		    min-width: 50px;
		    border-radius: 1rem;
		    padding: 0.33rem 0.66rem;
		    width: fit-content;
		    text-align: center;
		    position: absolute;
		    top:0;
		    left:0;
		    white-space: pre; /* 공백 문자를 그대로 유지 */
		    height: 0; /* 높이를 0으로 설정하여 공간 차지 안 함 */
		    overflow: hidden; /* 내용이 넘칠 경우 숨김 */
		}
    .subject-input-container {
        display: flex;
        flex-direction: row;
        justify-content: space-between; 
        align-items: center;
        max-width: 100%;
        width: 100%;
        height: 3rem;
        position: relative;
    }
		.dropdown-menu {
		      display: none;
		    position: absolute;
		    top: 100%;
		    left: 0;
		    z-index: 1000;
		    background-color: #474747;
		    border: 1px solid #ccc;
		    border-radius: 5px;
		    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
		    max-height: 10rem; /* 최대 높이 설정 */
		    overflow-y: auto;
		}
		
		.dropdown-item {
		    padding: 0.5rem;
		    cursor: pointer;
		    color: #ffffff;
		}
		
		.dropdown-item:hover {
		    background-color: #444444;
		}
    .title_input {
        font-size: 25px;
        color: #ffffff;
        background-color: transparent;
        border: none;
        outline: none;
        padding: 0.5rem 0;
        width: auto;
        max-width: 100%;
    }
    .title_container {
        position: relative;
        margin-bottom: 1rem;
    }
    .title_underline {
        position: absolute;
        left: 0;
        bottom: 0;
        width: 0;
        height: 5px;
        background-color: #cccccc;
        transition: width 0.3s ease-in-out;
    }
    .editor {
        margin-bottom: 1rem;
        border-radius: 1rem;
        padding: 2rem;
        font-family: 'Pretendard-Regular';
        font-size: 1rem;
        color: #ffffff;
        background-color: #474747;
        position: relative;
        width: 865px;
        max-width: 100%;
        height: 200px;
        border: none;
        box-shadow: 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
    }
    .box {
        display: flex;
        justify-content: flex-start;
        margin-bottom: 1rem;
        width: 850px;
    }
    .hint_container {
        margin-right: auto;
        background-position: center;
        background-size: cover;
    }
    .file_container {
        background-position: center;
        background-size: cover;
    }
    .hint_input {
        border: none;
        outline: none;
        border-radius: 5px;
        font-size: 20px;
        display: block;
        box-shadow: 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
        color: #ffffff;
        background-color: #474747;
    }
    .answer_container {
        height: fit-content;
    }
    #answer {
        width: 850px;
        resize: vertical;
        height: 50px;
        font-size: 15px;
        border-radius: 5px;
        border: none;
        box-shadow: 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
        background-color: #474747;
        color: #ffffff;
    }
    .commentary_editor {
        margin-bottom: 1rem;
        border-radius: 1rem;
        padding: 1rem;
        font-family: 'Pretendard-Regular';
        font-size: 1rem;
        color: #2F2F2F;
        background-color: #474747;
        position: relative;
        max-width: 100%;
        height: 200px;
        border: none;
        width: 865px;
        box-shadow: 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
        color: #ffffff;
    }
  
    .minibox {
        display: inline-flex;
        justify-content: space-between;
        margin-bottom: 2rem;
        max-width: 100%;
    }
    .danger_container {
        background-position: center;
        background-size: cover;
        border: 1px solid black;
        width: 500px;
        height: 50px;
        text-align: center;
    }
    .button_container {
        background-position: center;
        background-size: cover;
        margin-top: 20px;
        display: flex;
    }
    input::placeholder {
        color: #ffffff;
    }
    textarea::placeholder {
        color: #ffffff;
    }
    .write_btn, .reset_btn {
        box-shadow: 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
        background-color: #333333;
        color: #ffffff;
        border-radius: 10px;
        height: 2rem;
        width: 50px;
        padding: auto;
        display: flex;
        justify-content: center;
        align-items: center;
        font-family: 'Pretendard-Regular';
        font-size: 1rem;
        text-align: center;
        margin-left: 2rem;
    }
    .ql-toolbar.ql-snow {
    	background-color:#696969;
    	width:400px;
    	border-radius:20px;
    	margin-top:5px;
    }
    .dragover {
        border: 2px dashed #aaa; /* 드래그앤 드랍 시 보여질 스타일 */
        background-color: rgba(0, 0, 0, 0.1);
    }
    

	

</style>

<div class="container">
    <form id="writeFrm" class="writeFrm" name="writeFrm" action="writeok.jsp" enctype="multipart/form-data"> 
        <div class="subject-input-container">
    		<div class="subject-input-shadow">
        		<div class="subject-container">
            		<input class="subject_input" type="text" id="subject" name="subject" placeholder="과목 입력" spellcheck="false" maxlength="20" autocomplete="off">
       			</div>
    		</div>
	    	<span class="subject-hidden-input"></span>
	    	<div id="overlay-container" class="overlay-container">
	       	 	<div class="dropdown-menu"></div>
	    	</div>
		</div>
        <div class="title_container">
            <input class="title_input" type="text" id="title_input" name="title" placeholder="제목을 입력해주세요"
                spellcheck="false" autocomplete="off">
            <span class="title_underline" id="title_underline"></span>
        </div>
        <div id="editor" class="editor"  data-placeholder="내용을 입력해주세요" ></div>
        <input type="hidden" name="editorContent" id="editorContent">
        <div class="box">
            <div class="hint_container"><input class="hint_input" type="text" id="hint" name="hint" placeholder="힌트를 입력해주세요"></div>
            	<div class="file_container">
            		 <input type="file" id="mediaFiles" name="mediaFiles[]" accept="image/*,audio/*" multiple>
            	</div>
        </div>
        <div class="answer_container"><textarea id="answer" name="answer" placeholder="정답을 입력해주세요"></textarea></div>
        <div id="commentary_editor" class="commentary_editor"  data-placeholder="해설을 입력해주세요"></div>
        <input type="hidden" name="commentary_editorContent" id="commentary_editorContent">
        <div class="minibox">
            <div class="danger_container">저작권 경고</div>
            <div class="button_container">
                <input type="button" class="write_btn" id="write_btn" name="write_btn"  onclick="location.href='questionsolve.jsp'" value="작성">
                <input type="button" class="reset_btn" id="reset_btn" name="reset_btn" value="취소">
            </div>
        </div>
    </form>
</div>
<jsp:include page="./include/tail.jsp"></jsp:include>