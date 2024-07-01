<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./include/head.jsp"></jsp:include>
	<style>
	.container{
		display: inline-flex;
    	width: fit-content;
    	flex-direction:column;
    	height: calc(90vh - 8.8rem);
    	margin-left: 30px;
	}
	.subject_input{
		display: block;
	    background-color: #333333;
	    font-family: 'Pretendard-Regular';
	    font-size: 1rem;
	    padding: 0.33rem 0.66rem;
	    width:5rem;
	    height:100%;
	    min-width:5rem;
	    border: none;
	    text-align: center;
	    transition: all 0.05s ease-in-out;
	    border-radius:10px;
	    box-shadow : 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
	    color:#ffffff;
	}
	
	.subject-input-container{
		display: flex;
	    flex-direction: row;
	    justify-content: space-between; 
	    align-items: center;
	    max-width: 100%;
	    width:100%;
	    height:3rem;
	    position: relative;
	}
	.title_input {
	    width: fit-content;
	    width: 17rem;
	    max-width: 100%;
	    font-family: 'Pretendard-ExtraBold';
	    font-size: 2rem;
	    color:#ffffff;
	    border: none;
	    outline: none;
	    background-color: transparent;
	    padding: 0.5rem 0 0.3rem 0;
	    white-space: nowrap;
	    overflow: hidden;
	    border-bottom: 3px solid #cccccc;
	    caret-color: black;
	}
	.editor {
	 	margin-top: 1rem;
	    margin-bottom: 1rem;
	    border-radius: 1rem 1rem 1rem 1rem;
	    padding: 2rem;
	    font-family: 'Pretendard-Regular';
	    font-size: 1rem;
	    color:#ffffff;
	    background-color: #474747;
	    position:relative;
	    width: 800px;
	    max-width: 100%;
	    height: 400px;
	    border: none;
	    box-shadow : 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
	    font-size: 30px
	}
	.box{
		display: flex;
		justify-content: flex-start;
		margin-bottom: 1rem;
		width:850px;
	}
	.hint_container{
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
	    box-shadow : 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
	    color:#ffffff;
	    background-color:#474747;
	}
	.answer_container{
		height:fit-content;
		
	}
	#answer{
		width:850px;
		resize:vertical;
		height:50px;
		font-size: 15px;
		border-radius:5px;
		border: none;
	    box-shadow : 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
	    background-color:#474747;
	    color:#ffffff;
	}
	.commentary_editor{
		margin-top: 2rem;
	    margin-bottom: 1rem;
	    border-radius: 1rem 1rem 1rem 1rem;
	    padding: 2rem;
	    font-family: 'Pretendard-Regular';
	    font-size: 1rem;
	    color:#2F2F2F;
	    background-color: #474747;
	    position:relative;
	    max-width: 100%;
	    height: 10vh;
	    border: none;
	    font-size: 30px;
	    width:800px;
	    box-shadow : 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
	    color:#ffffff;
	}
	[contenteditable="true"]:empty:before {
   	content: attr(placeholder);
	}
	.minibox{
		display: flex;
		justify-content: flex-start;
		margin-bottom: 1rem;
		width:850px;
	}
	.danger_container{
		margin-right: auto;
		background-position: center;
    	background-size: cover;
    	border:1px solid black;
    	width:500px;
    	height:50px;
    	text-align:center;
	}

	.button_container{
		background-position: center;
    	background-size: cover;
    	margin-top:20px;
    	display:flex;
    	
	}
	input::placeholder {
		color:#ffffff;
	}
	textarea::placeholder {
		color:#ffffff;
	}
	.write_btn,.reset_btn{
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
		text-align:center;
		margin-left:2rem;
	}
	</style>
	
		<form id="writeFrm" name="wiriteFrm" action="modifyok.jsp">
			<div class="container">
				<div class="subject-input-container">
					<div class="subject-input-shadow">
						<div class="subject-container">
							<input class="subject_input" type="text"  id="subject" name="subject" placeholder="JAVA" spellcheck="false" maxlength=20 autocomplete = "off">
						</div>
					</div>
	                <span class="subject-hidden-input"></span>
					<div id="overlay-container" class="overlay-container">
						<div class="dropdown-menu">
						</div>
					</div>
	            </div>
				<div class="title-container">
	                <input class="title_input" type="text" id="title" name="title" placeholder="제목입니다" spellcheck="false" autofocus autocomplete = "off">
	                <span class="title_hidden_input"></span>
	                <input type="hidden" id="user_id" name="user_id" value="userid">
	            </div>
				<div id=editor class="editor" contenteditable="true" placeholder="이전에 작성한 내용을 입니다"></div>
				<input type="hidden" name="editorContent" id="editorContent">
				<div class="box">
					<div class=hint_container><input class="hint_input" type="text" id="hint" name="hint" placeholder="힌트입니다"></div>
					<div class=file_container><input type="file"></div>
				</div>	
				<div class="answer_container"><textarea id="answer" name="answer" placeholder="정답을 입력해주세요">9</textarea></div>
				<div class="commentary_editor" id="commentary_editor" contenteditable="true" placeholder="이전에 작성한 해설입니다"></div>
				<input type="hidden" name="editorContent" id="editorContent">
				<div class="minibox">
					<div class="danger_container">저작권 경고</div>
					<div class="button_container">
						<input type="button" class="write_btn" id="write_btn" name="write_btn" value="수정">
						<input type="button" class="reset_btn" id="reset_btn" name="reset_btn" value="취소">
					</div>
				</div>	
			</div>
		</form>
		
<jsp:include page="./include/tail.jsp"></jsp:include>