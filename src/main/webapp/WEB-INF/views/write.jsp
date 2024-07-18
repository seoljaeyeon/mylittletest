<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<jsp:include page="./include/head.jsp"></jsp:include>
<!-- CSRF 메타 태그 추가 -->
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="https://cdn.quilljs.com/1.3.7/quill.snow.css"rel="stylesheet">
<script src="https://cdn.quilljs.com/1.3.7/quill.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/quill-image-resize-module@3.0.0/image-resize.min.js"></script>
<script>
	document.addEventListener("DOMContentLoaded",function() {
		   // CSRF token 설정
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");

        console.log("CSRF Token:", csrfToken);
        console.log("CSRF Header:", csrfHeader);

        $("#writeFrm").submit(function(event) {
	        var form = $(this)[0];
	        var data = new FormData(form);

	        $.ajax({
	            type: "POST",
	            enctype: 'multipart/form-data',
	            url: "/mylittletest/write",
	            data: data,
	            processData: false,
	            contentType: false,
	            cache: false,
	            timeout: 600000,
	            beforeSend: function(xhr) {
                    xhr.setRequestHeader(csrfHeader, csrfToken);
                    xhr.setRequestHeader("Accept", "application/json"); 
	            },
	            success: function(response) {
					if (response.status == "success") {
						window.location.href = response.url;
					} else if (response.status == "login_needeed") {
						window.location.href = response.url;
					} else {
						window.location.href = response.url;
					}
	            },
	            error: function(e) {
	                console.log("ERROR : ", e);
	                window.location.href = "/mylittletest/write"
	            }
	        });
	        event.preventDefault();
	    });
        
        // 중복 데이터 필터링 및 드롭다운 메뉴 설정
        const inputField = document.getElementById('categoryTitle');
        const dropdownMenu = document.createElement('div');
        
        dropdownMenu.classList.add('dropdown-menu');
        inputField.parentNode.appendChild(dropdownMenu);
        
     // 기존 데이터 배열입니다.
		const existingData = [];
		
		// 서버에서 데이터를 받아옴
		 fetch('/category')
            .then(response => response.json())
            .then(data => {
                existingData = data.map(category => category.name);
            });
       
        inputField.addEventListener('input', function() {
            const inputValue = inputField.value.trim().toUpperCase();
            dropdownMenu.innerHTML = '';
            if (inputValue === '') {
                dropdownMenu.style.display = 'none';
                return;
            }
            
            
            
            const matchingData = existingData.filter(item => item.toUpperCase().includes(inputValue));
            const dataCounts = countDataOccurrences(matchingData);
            
            Object.keys(dataCounts).forEach(item => {
                const count = dataCounts[item];
                const option = document.createElement('div');
                option.textContent = item + ' (' + count + '문제)';
                option.classList.add('dropdown-item');
                option.addEventListener('mousedown', function() {
                    inputField.value = item;
                    dropdownMenu.style.display = 'none';
                });
                dropdownMenu.appendChild(option);
            });
            dropdownMenu.style.display = Object.keys(dataCounts).length > 0 ? 'block' : 'none';
        });
        inputField.addEventListener('blur', function() {
            setTimeout(() => {
                dropdownMenu.style.display = 'none';
            }, 100);
        });

        function countDataOccurrences(dataArray) {
            const counts = {};
            dataArray.forEach(item => {
                counts[item.toUpperCase()] = counts[item.toUpperCase()] ? counts[item.toUpperCase()] + 1 : 1;
            });
            return counts;
        }

        // 제목 밑줄 조정
        const titleInput = document.getElementById('noteTitle');
        const titleUnderline = document.getElementById('title_underline');
        titleInput.addEventListener('input', function() {
            adjustUnderlineWidth(titleInput, titleUnderline);
        });
        titleInput.addEventListener('focus', function() {
            titleUnderline.style.width = '0';
        });
        titleInput.addEventListener('blur', function() {
            adjustUnderlineWidth(titleInput, titleUnderline);
        });

        function adjustUnderlineWidth(input, underline) {
            const textWidth = getTextWidth(input.value, window.getComputedStyle(input).font);
            underline.style.width = textWidth + 'px';
        }

        function getTextWidth(text, font) {
            const canvas = getTextWidth.canvas || (getTextWidth.canvas = document.createElement("canvas"));
            const context = canvas.getContext("2d");
            context.font = font;
            const metrics = context.measureText(text);
            return metrics.width;
        }

        // Quill Editor 초기화
        var editor = new Quill('#editor', {
            theme: 'snow',
            placeholder: '내용을 입력해주세요. (이미지는 드래그해서 넣어주세요.)',
            modules: {
                toolbar: [[{ 'header': [1, 2, 3, 4, 5, 6, false] }], ['bold', 'italic', 'underline'], ['link', 'image', 'video'], ['clean']],
                imageResize: {
                    modules: ['Resize', 'DisplaySize', 'Toolbar']
                }
            }
        });

        var commentaryEditor = new Quill('#commentary_editor', {
            theme: 'snow',
            placeholder: '해설을 입력해주세요',
            modules: {
                toolbar: [[{ 'header': [1, 2, 3, 4, 5, 6, false] }], ['bold', 'italic', 'underline'], ['link', 'image', 'video'], ['clean']],
                imageResize: {
                    modules: ['Resize', 'DisplaySize', 'Toolbar']
                }
            }
        });

        // 드래그앤드랍 이미지 처리
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

        // 파일 선택 버튼 처리
        $('#customFile').on('change', function() {
            var fileName = $(this).val().split('\\').pop();
            $(this).next('.custom-file-label').html(fileName);
        });
});
function goBack() {
    window.history.back();
}
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
	font-family: 'Pretendard-Regular';
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

#noteAnswer {
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
	background-color: #696969;
	width: 400px;
	border-radius: 20px;
	margin-top: 5px;
}

.dragover {
	border: 2px dashed #aaa; /* 드래그앤 드랍 시 보여질 스타일 */
	background-color: rgba(0, 0, 0, 0.1);
}


</style>

<div class="container">
	<form id="writeFrm" class="writeFrm" name="writeFrm"
		action="/mylittletest/write" method="post" enctype="multipart/form-data">
    	<sec:csrfInput/>
		<div class="subject-input-container">
			<div class="subject-input-shadow">
				<input type="hidden" name="userNo" id="userNo" value="${userVO.userNo}">
				<div class="subject-container">
					<input class="subject_input" type="text" id="categoryTitle"
						name="categoryTitle" placeholder="과목 입력" spellcheck="false"
						maxlength="20" autocomplete="off">
				</div>
			</div>
			<span class="subject-hidden-input"></span>
			<div id="overlay-container" class="overlay-container">
				<div class="dropdown-menu"></div>
			</div>
		</div>
		<div class="title_container">
			<input class="title_input" type="text" id="noteTitle"
				name="noteTitle" placeholder="제목을 입력해주세요" spellcheck="false"
				autocomplete="off"> <span class="title_underline"
				id="title_underline"></span>
		</div>
		<div id="editor" class="editor" data-placeholder="내용을 입력해주세요"></div>
		<input type="hidden" name="editorContent" id="editorContent">
		<input type="hidden" name="noteContent" id="noteContent">
		<div class="box">
			<div class="hint_container">
				<input class="hint_input" type="text" id="noteHint" name="noteHint"
					placeholder="힌트를 입력해주세요">
			</div>
			<div class="file_container">
				 <input type="file" id="mediaFiles" name="mediaFiles[]" accept="image/*,audio/*" multiple>
			</div>
		</div>
		<div class="answer_container">
			<textarea id="noteAnswer" name="noteAnswer" placeholder="정답을 입력해주세요"></textarea>
		</div>
		<div id="commentary_editor" class="commentary_editor"
			data-placeholder="해설을 입력해주세요"></div>
		<input type="hidden" name="commentary_editorContent"
			id="commentary_editorContent"> <input type="hidden"
			name="noteCommentary" id="noteCommentary">
		<div class="minibox">
			<div class="danger_container">저작권 경고</div>
			<div class="button_container">
				<input type="submit" class="write_btn" id="write_btn"
					name="write_btn" value="작성"> 
			    <button type="button" class="reset_btn" id="reset_btn" name="reset_btn" onclick="goBack();">취소</button>
			</div>
		</div>
	</form>
</div>
<jsp:include page="./include/tail.jsp"></jsp:include>
