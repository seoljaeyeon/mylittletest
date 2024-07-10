<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./include/head_login.jsp"></jsp:include>
<link href="https://cdn.quilljs.com/1.3.7/quill.snow.css" rel="stylesheet">
<script src="https://cdn.quilljs.com/1.3.7/quill.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/quill-image-resize-module@3.0.0/image-resize.min.js"></script>
<style>
	.a {	
		width: 100%;
		height: 100%;
		display: inline-block;
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
        width: 100%;
        max-width: 100%;
        height: 450px;
        border: none;
        box-shadow: 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
    }
    .ql-toolbar.ql-snow {
    	background-color:#696969;
    	width:400px;
    	border-radius:20px;
    	margin-top:20px;
    }								
</style>
	<form id="modifyFrm" name="modifyFrm" action="announcement_modifyok.jsp">	
		<div class="a">
		<div>&nbsp&nbsp<span style="font-size: 20px; font-weight: bolder;">공지사항</span>
		<br><br>
		<div>
		<input type="text" id="title" name="title" style="font-size: 35px; font-weight: bolder;  background-color: #474747; color: white;  border-width: 0;" placeholder="기존의 제목입니다.">
			<div align="right">
				<a href="announcement_list.jsp">
					<button type="button" style="height: 40px; width: 100px; border-radius: 10px; background-color: transparent; color: white;">돌아가기</button>
				</a>
			</div>
		</div>
		<br>
		<div style="border: none; outline: none; border-bottom: 1px solid white;"></div>
        <div id="editor" class="editor"  data-placeholder="내용을 입력해주세요" ></div>
        <input type="hidden" name="editorContent" id="editorContent">
		<div style="border: none; outline: none; border-bottom: 1px solid white;"></div>
		<br><br>
		<div><input type="file" id="file" name="file" style="height: 40px; width: 200px;"></div>
		<div align="right">
			<input type="submit" id="write_btn" name="write_btn" value="수정완료" style="height: 40px; width: 100px; border-radius: 10px; background-color: transparent; color: white;">
		</div>
		</div>
		</div>
	</form>	
<jsp:include page="./include/tail.jsp"></jsp:include>
<script>
//Quill Editor 초기화
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
    document.getElementById('editorContent').value = editorHtml;
    
});
</script>		