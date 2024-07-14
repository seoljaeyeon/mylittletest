<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<jsp:include page="./include/head.jsp"></jsp:include>
<!-- CSRF 메타 태그 추가 -->
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link href="https://cdn.quilljs.com/1.3.7/quill.snow.css"
	rel="stylesheet">
<script src="https://cdn.quilljs.com/1.3.7/quill.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/quill-image-resize-module@3.0.0/image-resize.min.js"></script>
<script>
	document
			.addEventListener(
					"DOMContentLoaded",
					function() {
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
						
						// 입력 필드(input)와 해당하는 밑줄 요소를 가져옵니다.
						const titleInput = document
								.getElementById('noteTitle');
						const titleUnderline = document
								.getElementById('title_underline');

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
							const textWidth = getTextWidth(input.value, window
									.getComputedStyle(input).font);
							// 밑줄의 너비를 텍스트의 너비에 맞게 설정합니다.
							underline.style.width = textWidth + 'px';
						}

						// 텍스트의 너비를 계산하여 반환하는 함수
						function getTextWidth(text, font) {
							// 캔버스 엘리먼트를 가져오거나 새로 생성합니다. getTextWidth.canvas가 존재하지 않으면 새로운 캔버스 엘리먼트를 생성하고,
							// getTextWidth.canvas에 할당합니다.
							const canvas = getTextWidth.canvas
									|| (getTextWidth.canvas = document
											.createElement("canvas"));
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
							theme : 'snow', // snow 테마 사용 (기본)
							placeholder : '내용을 입력해주세요. (이미지는 드래그해서 넣어주세요.)',
							modules : {
								toolbar : [ [ {
									'header' : [ 1, 2, 3, 4, 5, 6, false ]
								} ], [ 'bold', 'italic', 'underline' ],
										[ 'link', 'image', 'video' ],
										[ 'clean' ] ],
								imageResize : {
									// 이미지 리사이즈 옵션 설정 (필요에 따라 설정 가능)
									modules : [ 'Resize', 'DisplaySize',
											'Toolbar' ]
								}
							}
						});

						var commentaryEditor = new Quill(
								'#commentary_editor',
								{
									theme : 'snow', // snow 테마 사용 (기본)
									placeholder : '해설을 입력해주세요',
									modules : {
										toolbar : [
												[ {
													'header' : [ 1, 2, 3, 4, 5,
															6, false ]
												} ],
												[ 'bold', 'italic', 'underline' ],
												[ 'link', 'image', 'video' ],
												[ 'clean' ] ],
										imageResize : {
											// 이미지 리사이즈 옵션 설정 (필요에 따라 설정 가능)
											modules : [ 'Resize',
													'DisplaySize', 'Toolbar' ]
										}
									}
								});

						// 이미지를 드래그앤 드랍할 수 있도록 Quill 에디터 설정
						var editorContainer = document.getElementById('editor');
						editorContainer.addEventListener('dragover',
								function(e) {
									e.preventDefault();
									editorContainer.classList.add('dragover');
								});

						editorContainer.addEventListener('dragleave', function(
								e) {
							e.preventDefault();
							editorContainer.classList.remove('dragover');
						});

						editorContainer
								.addEventListener(
										'drop',
										function(e) {
											e.preventDefault();
											editorContainer.classList
													.remove('dragover');
											var file = e.dataTransfer.files[0];
											if (file
													&& file.type
															.startsWith('image/')) {
												var reader = new FileReader();
												reader.onload = function(event) {
													var range = editor
															.getSelection();
													var index = range ? range.index
															: editor
																	.getLength();
													editor.clipboard
															.dangerouslyPasteHTML(
																	index,
																	'<img src="' + event.target.result + '">');
												};
												reader.readAsDataURL(file);
											}
										});

						// 이미지를 드래그앤 드랍할 수 있도록 Quill 해설 에디터 설정
						var commentaryEditorContainer = document
								.getElementById('commentary_editor');
						commentaryEditorContainer.addEventListener('dragover',
								function(e) {
									e.preventDefault();
									commentaryEditorContainer.classList
											.add('dragover');
								});

						commentaryEditorContainer.addEventListener('dragleave',
								function(e) {
									e.preventDefault();
									commentaryEditorContainer.classList
											.remove('dragover');
								});

						commentaryEditorContainer
								.addEventListener(
										'drop',
										function(e) {
											e.preventDefault();
											commentaryEditorContainer.classList
													.remove('dragover');
											var file = e.dataTransfer.files[0];
											if (file
													&& file.type
															.startsWith('image/')) {
												var reader = new FileReader();
												reader.onload = function(event) {
													var range = commentaryEditor
															.getSelection();
													var index = range ? range.index
															: commentaryEditor
																	.getLength();
													commentaryEditor.clipboard
															.dangerouslyPasteHTML(
																	index,
																	'<img src="' + event.target.result + '">');
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
										var index = range ? range.index
												: quillEditor.getLength();
										quillEditor.clipboard
												.dangerouslyPasteHTML(index,
														'<img src="' + event.target.result + '">');
									};
									reader.readAsDataURL(file);
								}
							}
						}

						// 파일 선택 버튼 처리
						document
								.getElementById('file')
								.addEventListener(
										'change',
										function(e) {
											var files = e.target.files;
											for (var i = 0; i < files.length; i++) {
												var file = files[i];
												if (file.type
														.startsWith('image/')) {
													var reader = new FileReader();
													reader.onload = function(
															event) {
														var range = editor
																.getSelection();
														var index = range ? range.index
																: editor
																		.getLength();
														editor.clipboard
																.dangerouslyPasteHTML(
																		index,
																		'<img src="' + event.target.result + '">');
													};
													reader.readAsDataURL(file);
												}
											}
										});

						// 저장 버튼 클릭 시 각 에디터의 내용을 hidden input에 설정
						document
								.getElementById('write_btn')
								.addEventListener(
										'click',
										function() {
											var editorHtml = editor.root.innerHTML;
											var commentaryHtml = commentaryEditor.root.innerHTML;
											document
													.getElementById('editorContent').value = editorHtml;
											document
													.getElementById('commentary_editorContent').value = commentaryHtml;
											document
													.getElementById('noteContent').value = editorHtml;
											document
													.getElementById('noteCommentary').value = commentaryHtml;
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
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
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

.custom-placeholder {
	color: white;
	font-style: italic;
	font-weight: bolder;
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
				<input type="file" class="upload" name="file" id="file" multiple>
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
					name="write_btn" value="작성"> <input type="button"
					class="reset_btn" id="reset_btn" name="reset_btn" value="취소">
			</div>
		</div>
	</form>
</div>
<jsp:include page="./include/tail.jsp"></jsp:include>
