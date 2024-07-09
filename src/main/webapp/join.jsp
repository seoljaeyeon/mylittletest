<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="./include/head.jsp"></jsp:include>
<link rel="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<script>
document.addEventListener("DOMContentLoaded", function() {
    var pwBtn = document.getElementById('PwBtn');
    var pwcBtn = document.getElementById('PwcBtn');
    var pwInput = document.getElementById('userPw');
    var pwcInput = document.getElementById('Pwc');

    pwBtn.addEventListener('click', function() {
        if (pwInput.type === 'password') {
            pwInput.type = 'text';
            pwBtn.innerHTML = '🙈';
        } else {
            pwInput.type = 'password';
            pwBtn.innerHTML = '👁️';
        }
    });

    pwcBtn.addEventListener('click', function() {
        if (pwcInput.type === 'password') {
            pwcInput.type = 'text';
            pwcBtn.innerHTML = '🙈';
        } else {
            pwcInput.type = 'password';
            pwcBtn.innerHTML = '👁️';
        }
    });
});
</script>
<style>
.join_area {
	width: 30rem;
	height: fit-content;
	border-radius: 5rem;
	background-color:#474747;
	margin: 0 auto;
	display: flex;
	padding: 0.5rem;
	margin-top: 0.5rem;
	flex-direction: column;
	align-items: center;
	padding-bottom: 0.5rem;
	border:none;
	box-shadow : 0.3rem 0.3rem 0.7rem #cccccc, -0.3rem -0.3rem 0.7rem #cccccc;
}

.join_title {
	margin-top: 1rem;
	margin-bottom: 1rem;
	height: max-content;
	font-size: 2rem;
}

.input_area {
	display: inline-flex;
	flex-direction: column;
	align-items: center;
	width: 70%;
	margin-top: 15px;
}

.id_input_area {
	display: inline-flex;
	flex-direction: column;
	justify-content: center;;
	width: 100%;
	margin-bottom: 0.5rem;
}

.id_input, .pw_input {
	border: none;
	outline: none;
	font-size: 20px;
	position: relative;
	width: 100%;
	background-color: transparent;
	font-family: 'Pretendard-Regular';
}

.id_input_container {
	padding-bottom: 0.3rem;
	border-bottom: 2px solid #cccccc;
	transition: all 0.2s ease-out;
	width: 100%;
	justify-content: space-between;
	position: relative;
}

.pw_input_area {
	width: 100%;
	display: inline-flex;
	flex-direction: row;
	margin-bottom: 2rem;
	position: relative;
}

.pw_input_container {
	padding-bottom: 0.3rem;
	border-bottom: 2px solid #cccccc;
	display: flex;
	flex-direction: row;
	transition: all 0.2s ease-out;
	width: 100%;
	justify-content: space-between;
	position: relative;
}

.show_password_btn {
	opacity: 0.5;
	transition: all 0.2s ease-out;
}
.show_password_btn:active {
    opacity:1;
}

.show_password_btn:hover {
    opacity:1;
}

.send_email_btn {
	box-shadow: 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
	background-color: #696969;
	color: #ffffff;
	border-radius: 2rem;
	height: 2rem;
	width: 5rem;
	padding: auto;
	display: flex;
	justify-content: center;
	align-items: center;
}

.id_find_area {
	width: 100%;
	display: inline-flex;
	flex-direction: row;
	position: relative;
	height: fit-content;
}

.id_find_container {
	padding-bottom: 0.3rem;
	transition: all 0.2s ease-out;
	width: 100%;
	justify-content: space-between;
	position: relative;
	display: flex;
}

.idquestion {
	font-size: 15px;
}

.idfind_box {
	margin-left: 7px;
}

.join_btn {
  	box-shadow : 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
    background-color: #696969;
    color: #ffffff;
    border-radius: 5rem;
    height: 3rem;
    width: 10rem;
    padding: auto;
    display:flex;
    justify-content: center;
    align-items: center;
    font-family: 'Pretendard-Regular';
    border: none;
    font-size: 1rem;
    cursor:pointer;
}

a {
	color: #cccccc;
	font-size: 12px;
}
</style>
	<div class="join_area">
		<h1 class="join_title">환영합니다!</h1>
		<div class="emoji"  style="font-size:80px; text-align:center;">🐨</div>
		<form class="input_area" name="join" id="join" method="post" action="joincomplete.jsp">
			<div class="id_input_area">
				<div class="id_input_container">
					<input class="id_input" id="userID" name="userID" placeholder="계정"
						autocomplete="off">
				</div>
			</div>
			<div class="pw_input_area">
				<div class="pw_input_container">
					<input class="pw_input" type="password" id="userPw" name="userPw"
						placeholder="비밀번호">
					<text id="PwBtn" class="show_password_btn" autocomplete="off">👁️</text>
				</div>
			</div>
			<div class="pw_input_area" id="pw_check">
				<div class="pw_input_container">
					<input class="pw_input" type="password" id="Pwc" name="Pwc"
						placeholder="비밀번호 확인">
					<text id="PwcBtn" class="show_password_btn" autocomplete="off">👁️</text>
				</div>
			</div>
			<div class="id_input_area">
				<div class="id_input_container">
					<input class="id_input" id="nickname" name="nickname"
						placeholder="닉네임" autocomplete="off">
				</div>
			</div>
			<div class="pw_input_area" id="email">
				<div class="pw_input_container">
					<input class="pw_input" id="userMail" name="userMail"
						placeholder="이메일" style="margin-right: 1rem;" autocomplete="off">
				</div>
				<div class="send_email_btn">전송</div>
			</div>
			<div class="id_input_area">
				<div class="id_input_container"
					style="margin-bottom: 2rem; justify-content: center; position: relative;">
					<input class="id_input" id="code" name="code"
						placeholder="이메일 확인 코드" autocomplete="off">
					<p
						style="opacity: 0.3; position: absolute; top: 110%; left: 0; margin: 0;">💡
						발송된 코드는 5분간 유효합니다.</p>
				</div>
			</div>
			<div class="id_find_area">
				<div class="id_find_container"
					style="margin-bottom: 2rem; justify-content: center; position: relative;">
					<div class="idquestion">계정정보찾기 질문</div>
					<div class="idfind_box">
						<select id="idfind" class="idfind">
							<option value="1">나의 출신고향은?</option>
							<option value="2">나의 출신 초등학교는?</option>
							<option value="3">가장 좋아하는 색깔은?</option>
							<option value="4">가장 좋아하는 음식은?</option>
							<option value="5">다시 태어나면 되고싶은것은></option>
						</select>
					</div>
				</div>
			</div>
			<div class="id_input_area">
				<div class="id_input_container"
					style="margin-bottom: 2rem; justify-content: center; position: relative;">
					<input class="id_input" id="id_question" name=""
						placeholder="질문 답변 입력" autocomplete="off">
				</div>
			</div>
			<button class="join_btn" type="button" onclick="location.href='joincomplete.jsp'">회원가입</button>
			<br>
			<div class="login_box" style="margin-left:10px;">
				<a href="index.jsp">돌아가기</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="login.jsp">기존
					계정 로그인하기</a>
			</div>
		</form>
	</div>

<jsp:include page="./include/tail.jsp"></jsp:include>