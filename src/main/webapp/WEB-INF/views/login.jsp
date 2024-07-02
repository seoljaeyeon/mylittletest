<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<jsp:include page="./include/head.jsp"></jsp:include>
<script>
	document.addEventListener("DOMContentLoaded", function() {
		function togglePopup(popupId, action) {
			var popup = document.getElementById(popupId);
			if (action === 'show') {
				popup.classList.add('show');
			} else if (action === 'hide') {
				popup.classList.remove('show');
			} else if (action === 'toggle') {
				popup.classList.toggle('show');
			}
		}

		// 계정찾기 팝업 열기 버튼에 클릭 이벤트 추가
		document.getElementById("popup_open").addEventListener("click",
				function() {
					togglePopup('popup', 'toggle');
				});

		// 계정찾기 팝업 닫기 버튼에 클릭 이벤트 추가
		document.getElementById("btn_close").addEventListener("click",
				function() {
					togglePopup('popup', 'hide');
				});

		// 아이디 찾기 팝업 열기 버튼에 클릭 이벤트 추가
		document.getElementById("findid").addEventListener("click", function() {
			togglePopup('popup', 'hide');
			togglePopup('popup_ID', 'show');
		});

		// 아이디 찾기 팝업 닫기 버튼에 클릭 이벤트 추가
		document.getElementById("btn_closeID").addEventListener("click",
				function() {
					togglePopup('popup_ID', 'hide');
				});

		// 비밀번호 찾기 팝업 열기 버튼에 클릭 이벤트 추가
		document.getElementById("findpw").addEventListener("click", function() {
			togglePopup('popup', 'hide');
			togglePopup('popup_ID', 'hide');
			togglePopup('popup_EM', 'hide');
			togglePopup('popupSEND', 'hide');
			togglePopup('popup_PW', 'show');
		});

		document.getElementById("findpw1").addEventListener("click",
				function() {
					togglePopup('popup', 'hide');
					togglePopup('popup_ID', 'hide');
					togglePopup('popup_EM', 'hide');
					togglePopup('popupSEND', 'hide');

					togglePopup('popup_PW', 'show');
				});

		document.getElementById("findpw2").addEventListener("click",
				function() {
					togglePopup('popup', 'hide');
					togglePopup('popup_ID', 'hide');
					togglePopup('popup_EM', 'hide');
					togglePopup('popupSEND', 'hide');

					togglePopup('popup_PW', 'show');
				});

		// 비밀번호 찾기 팝업 닫기 버튼에 클릭 이벤트 추가
		document.getElementById("btn_closePW").addEventListener("click",
				function() {
					togglePopup('popup_PW', 'hide');
				});

		// 이메일 찾기 팝업 열기 버튼에 클릭 이벤트 추가
		document.getElementById("findem").addEventListener("click", function() {
			togglePopup('popup', 'hide');
			togglePopup('popup_EM', 'show');
		});

		// 이메일 찾기 팝업 닫기 버튼에 클릭 이벤트 추가
		document.getElementById("btn_closeEM").addEventListener("click",
				function() {
					togglePopup('popup_EM', 'hide');
				});

		// 이메일 찾기 팝업 열기 버튼에 클릭 이벤트 추가(비밀번호)
		document.getElementById("findpem").addEventListener("click",
				function() {
					togglePopup('popup', 'hide');
					togglePopup('popup_PEM', 'show');
				});

		// 이메일 찾기 팝업 닫기 버튼에 클릭 이벤트 추가(비밀번호)
		document.getElementById("btn_closePEM").addEventListener("click",
				function() {
					togglePopup('popup_PEM', 'hide');
				});

		// 질문으로 찾기 팝업 열기 버튼에 클릭 이벤트 추가(계정)
		document.getElementById("findqu").addEventListener("click", function() {
			togglePopup('popup', 'hide');
			togglePopup('popup_QU', 'show');
		});

		// 질문으로 찾기 팝업 닫기 버튼에 클릭 이벤트 추가(계정)
		document.getElementById("btn_closeQU").addEventListener("click",
				function() {
					togglePopup('popup_QU', 'hide');
				});

		// 질문으로 찾기 팝업 열기 버튼에 클릭 이벤트 추가(비밀번호)
		document.getElementById("findpqu").addEventListener("click",
				function() {
					togglePopup('popup', 'hide');
					togglePopup('popup_PQU', 'show');
				});

		// 질문으로 찾기 팝업 닫기 버튼에 클릭 이벤트 추가(비밀번호)
		document.getElementById("btn_closePQU").addEventListener("click",
				function() {
					togglePopup('popup_PQU', 'hide');
				});

		// 메일 발송 알림 팝업(계정)
		document.getElementById("em_comlete").addEventListener("click",
				function() {
					togglePopup('popup', 'hide');
					togglePopup('popupSEND', 'show');
				});

		// 메일 발송 알림 팝업(계정) (닫기)
		document.getElementById("btn_closeSEND").addEventListener("click",
				function() {
					togglePopup('popupSEND', 'hide');
				});
		// 질문 알림 팝업(계정)
		document.getElementById("qu_comlete").addEventListener("click",
				function() {
					togglePopup('popup', 'hide');
					togglePopup('popupIM', 'show');
				});

		// 질문 알림 팝업(계정) (닫기)
		document.getElementById("btn_closeIM").addEventListener("click",
				function() {
					togglePopup('popupIM', 'hide');
				});

		// 비밀번호 변경팝업창(질문)
		document.getElementById("pw_complete").addEventListener("click",
				function() {
					togglePopup('popup', 'hide');
					togglePopup('popup_complete', 'show');
				});

		// 비밀번호 변경팝업창(질문) (닫기)
		document.getElementById("btn_closecomplete").addEventListener("click",
				function() {
					togglePopup('popup_complete', 'hide');
				});

		// 비밀번호 변경팝업창(메일)
		document.getElementById("mail_complete").addEventListener("click",
				function() {
					togglePopup('popup', 'hide');
					togglePopup('popup_mailcom', 'show');
				});

		// 비밀번호 변경팝업창(메일) (닫기)
		document.getElementById("btn_closemailcom").addEventListener("click",
				function() {
					togglePopup('popup_mailcom', 'hide');
				});

		// 비밀번호 변경완료팝업창(메일)
		document.getElementById("mail_com").addEventListener("click",
				function() {
					togglePopup('popup', 'hide');
					togglePopup('popup_pwcomplete', 'show');
				});

		// 비밀번호 변경완료팝업창(메일) (닫기)
		document.getElementById("btn_close_pwcomplete").addEventListener(
				"click", function() {
					togglePopup('popup_pwcomplete', 'hide');
				});

		// 비밀번호 변경완료팝업창(질문)
		document.getElementById("complete").addEventListener("click",
				function() {
					togglePopup('popup', 'hide');
					togglePopup('popup_qucomplete', 'show');
				});

		// 비밀번호 변경완료팝업창(메일) (닫기)
		document.getElementById("btn_close_qucomplete").addEventListener(
				"click", function() {
					togglePopup('popup_qucomplete', 'hide');
				});
	});
</script>
<style>
.login_area {
	width: 30rem;
	height: fit-content;
	border-radius: 5rem;
	margin: 0 auto;
	display: flex;
	padding: 1rem;
	margin-top: 3rem;
	flex-direction: column;
	align-items: center;
	padding-bottom: 3rem;
	background-color: #474747;
	border: none;
	box-shadow: 0.3rem 0.3rem 0.7rem #cccccc, -0.3rem -0.3rem 0.7rem #cccccc;
}

.login_title {
	margin-top: 3rem;
	margin-bottom: 1rem;
	height: max-content;
	font-size: 2rem;
	color: #000000;
}

.id_input_area {
	display: inline-flex;
	flex-direction: column;
	justify-content: center;;
	width: 80%;
	margin-bottom: 1.5rem;
	margin-top: 2rem;
	background-color: #474747;
}

.id_input, .pw_input {
	border: none;
	outline: none;
	font-size: 1rem;
	position: relative;
	font-family: 'Pretendard-Regular';
	width: 100%;
	background-color: #474747;
	color: #F8F8FF;
}

.id_input_container {
	padding-bottom: 0.3rem;
	border-bottom: 2px solid #cccccc;
	transition: all 0.2s ease-out;
	width: 100%;
	display: flex;
	flex-direction: row;
	gap: 1rem;
	justify-content: space-between;
	position: relative;
	background-color: #474747;
}

.pw_input_area {
	display: inline-flex;
	flex-direction: column;
	margin-bottom: 2rem;
	width: 80%;
	background-color: #474747;
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
	background-color: #474747;
}

.find_id {
	color: #cccccc;
	margin-top: 0.3rem;
	margin-bottom: 0.2rem;
	align-self: flex-end;
	border-bottom: 1px solid inherit;
}

.login_btn {
	box-shadow: 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
	background-color: #696969;
	color: #ffffff;
	border-radius: 5rem;
	height: 3rem;
	width: 10rem;
	padding: auto;
	display: flex;
	justify-content: center;
	align-items: center;
	font-family: 'Pretendard-Regular';
	border: none;
	font-size: 1rem;
	cursor: pointer;
}

.join_btn {
	margin-top: 2rem;
	width: fit-content;
	color: #cccccc;
	border-bottom: 1px solid #cccccc;
	transition: all 0.2s ease-out;
}

.popup_wrap {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	z-index: 1000;
	overflow: auto;
}

.find_area {
	background-color: #ffffff;
	width: 300px;
	max-width: 40rem;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	padding: 2rem;
	border-radius: 1rem;
	box-shadow: 0 0 1rem rgba(0, 0, 0, 0.1);
}

.btn_close {
	background: none;
	border: none;
	cursor: pointer;
	position: absolute;
	top: 1rem;
	right: 1rem;
	font-size: 1.5rem;
	color: #000000;
}

.find_title {
	font-size: 1.5rem;
	margin-bottom: 1rem;
	text-align: center;
	color: #000000;
}

.find_btn {
	padding: 1rem 2rem;
	background-color: #000000;
	border-radius: 10px;
	cursor: pointer;
	transition: background-color 0.3s ease;
	color: #ffffff;
	font-weight: bold;
	font-size: 12px;
}

.find_btn:hover {
	background-color: #e0e0e0;
}

.show {
	display: block;
}

.findbtn {
	margin-top: 2rem;
	width: fit-content;
	text-align: center;
}

.em_input {
	border: none;
	outline: none;
	font-size: 1rem;
	position: relative;
	font-family: 'Pretendard-Regular';
	width: 100%;
	background-color: #ffffff;
	color: #000000;
}

.send_email_btn {
	box-shadow: 0.3rem 0.3rem 0.7rem #cccccc, -0.3rem -0.3rem 0.7rem #dedede;
	background-color: #000000;
	color: #ffffff;
	border-radius: 2rem;
	height: 1.5rem;
	width: 4rem;
	padding: auto;
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: 12px;
}

.em_input_area {
	display: inline-flex;
	flex-direction: column;
	width: 80%;
	background-color: #ffffff;
	height: fit-content;
}

.em_input_container {
	padding-bottom: 0.3rem;
	border-bottom: 2px solid #cccccc;
	display: flex;
	flex-direction: row;
	transition: all 0.2s ease-out;
	width: 100%;
	justify-content: space-between;
	position: relative;
	background-color: #ffffff;
	height: fit-content;
	margin-bottom: 1rem;
}

.mail_box {
	margin-left: 2rem;
	margin-top: 2rem;
}

.mail_btn {
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
	box-shadow: 0.3rem 0.3rem 0.7rem #cccccc, -0.3rem -0.3rem 0.7rem #dedede;
	background-color: #000000;
	color: #ffffff;
	border-radius: 5rem;
	height: 3rem;
	width: 150px;
	padding: auto;
	display: flex;
	justify-content: center;
	align-items: center;
	font-family: 'Pretendard-Regular';
	font-size: 1rem;
	text-align: center;
	margin-left: 2rem;
}

.qu_input_area {
	display: inline-flex;
	flex-direction: column;
	width: 80%;
	background-color: #ffffff;
	height: fit-content;
}

.qu_input_container {
	padding-bottom: 0.3rem;
	border-bottom: 2px solid #cccccc;
	display: flex;
	flex-direction: row;
	transition: all 0.2s ease-out;
	width: 100%;
	justify-content: space-between;
	position: relative;
	background-color: #ffffff;
	margin-bottom: 1rem;
}

.question_box {
	margin-left: 2rem;
	margin-top: 2rem;
}

.qu_input {
	border: none;
	outline: none;
	font-size: 1rem;
	position: relative;
	font-family: 'Pretendard-Regular';
	width: 100%;
	background-color: #ffffff;
	color: #000000;
}

.id_find_area {
	width: 100%;
	display: inline-flex;
	flex-direction: row;
	position: relative;
}

.id_find_container {
	transition: all 0.2s ease-out;
	width: 100%;
	justify-content: space-between;
	position: relative;
	display: flex;
}

.idquestion {
	font-size: 12px;
	color: black;
}

.idfind_box {
	margin-left: 7px;
}

.mail_btn {
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
	box-shadow: 0.3rem 0.3rem 0.7rem #cccccc, -0.3rem -0.3rem 0.7rem #dedede;
	background-color: #000000;
	color: #ffffff;
	border-radius: 10px;
	height: 3rem;
	width: 150px;
	padding: auto;
	display: flex;
	justify-content: center;
	align-items: center;
	font-family: 'Pretendard-Regular';
	font-size: 1rem;
	text-align: center;
	margin-left: 2rem;
}
</style>
<body>
	<!-- 팝업 영역 -->
	<div class="popup_wrap" id="popup">
		<div class="find_area">
			<div class="close">
				<button class="btn_close" id="btn_close" type="button">X</button>
			</div>
			<h1 class="find_title">계정정보찾기</h1>
			<div class="findbtn"
				style="display: inline-flex; flex-direction: row; gap: 2rem; margin-left: 20px;">
				<div class="find_btn" id="findid">ID 찾기</div>
				<div class="find_btn" id="findpw">PW 찾기</div>
			</div>
		</div>
	</div>
	<div class="popup_wrap" id="popup_ID">
		<div class="find_area">
			<div class="close">
				<button class="btn_close" id="btn_closeID" type="button">X</button>
			</div>
			<h1 class="find_title">ID 찾기</h1>
			<div class="findbtn"
				style="display: inline-flex; flex-direction: row; gap: 2rem;">
				<div class="find_btn" id="findem">메일로 찾기</div>
				<div class="find_btn" id="findqu">질문으로 찾기</div>
			</div>
		</div>
	</div>
	<div class="popup_wrap" id="popup_PW">
		<div class="find_area">
			<div class="close">
				<button class="btn_close" id="btn_closePW" type="button">X</button>
			</div>
			<h1 class="find_title">패스워드 찾기</h1>
			<div class="findbtn"
				style="display: inline-flex; flex-direction: row; gap: 2rem;">
				<div class="find_btn" id="findpem">메일로 찾기</div>
				<div class="find_btn" id="findpqu">질문으로 찾기</div>
			</div>
		</div>
	</div>
	<!--이메일 팝업 계정찾기 -->
	<div class="popup_wrap" id="popup_EM">
		<div class="find_area">
			<div class="close">
				<button class="btn_close" type="button" id="btn_closeEM">X</button>
			</div>
			<h1 class="find_title">메일로 찾기</h1>
			<div class="mail_box">
				<div class="em_input_area" id="email">
					<div class="em_input_container">
						<input class="em_input" id="userMail" name="userMail"
							placeholder="이메일" style="margin-right: 1rem;" autocomplete="off">
						<div class="send_email_btn">전송</div>
					</div>
				</div>
				<div class="em_input_area">
					<div class="em_input_container"
						style="margin-bottom: 2rem; justify-content: center; position: relative;">
						<input class="em_input" id="code" name="code"
							placeholder="이메일 확인 코드" autocomplete="off">
					</div>
				</div>
				<div style="display: inline-flex; flex-direction: row; gap: 2rem;">
					<div class="mail_btn" id="em_comlete">인증완료</div>
				</div>
			</div>
		</div>
	</div>
	<!--이메일 팝업 비밀번호찾기 -->
	<div class="popup_wrap" id="popup_PEM">
		<div class="find_area">
			<div class="close">
				<button class="btn_close" type="button" id="btn_closePEM">X</button>
			</div>
			<h1 class="find_title">메일로 찾기</h1>

			<div class="mail_box">
				<div class="em_input_area">
					<div class="em_input_container">
						<input class="em_input" id="userID" name="userID" placeholder="계정"
							autocomplete="off">
					</div>
				</div>
				<div class="em_input_area" id="email">
					<div class="em_input_container">
						<input class="em_input" id="userMail" name="userMail"
							placeholder="이메일" style="margin-right: 1rem;" autocomplete="off">
						<div class="send_email_btn">전송</div>
					</div>
				</div>
				<div class="em_input_area">
					<div class="em_input_container"
						style="margin-bottom: 2rem; justify-content: center; position: relative;">
						<input class="em_input" id="code" name="code"
							placeholder="이메일 확인 코드" autocomplete="off">
					</div>
				</div>
				<div style="display: inline-flex; flex-direction: row; gap: 2rem;">
					<div class="mail_btn" id="mail_complete">인증완료</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 질문팝업 계정찾기 -->
	<div class="popup_wrap" id="popup_QU">
		<div class="find_area">
			<div class="close">
				<button class="btn_close" type="button" id="btn_closeQU">X</button>
			</div>
			<h1 class="find_title">질문으로 찾기</h1>
			<div class="question_box">
				<div class="id_find_area">
					<div class="id_find_container"
						style="margin-bottom: 1rem; margin_right: 10px; justify-content: center; position: relative;">
						<div class="idquestion">질문 선택</div>
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
				<div class="qu_input_area">
					<div class="qu_input_container"
						style="justify-content: center; position: relative;">
						<input class="qu_input" id="id_question" name=""
							placeholder="질문 답변 입력" autocomplete="off">
					</div>
				</div>
				<div style="display: inline-flex; flex-direction: row; gap: 2rem;">
					<div class="mail_btn" id="qu_comlete">인증완료</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 질문팝업 비밀번호찾기 -->
	<div class="popup_wrap" id="popup_PQU">
		<div class="find_area">
			<div class="close">
				<button class="btn_close" type="button" id="btn_closePQU">X</button>
			</div>
			<h1 class="find_title">질문으로 찾기</h1>
			<div class="question_box">
				<div class="qu_input_area">
					<div class="qu_input_container">
						<input class="qu_input" id="userID" name="userID" placeholder="계정"
							autocomplete="off">
					</div>
				</div>
				<div class="id_find_area">
					<div class="id_find_container"
						style="margin-bottom: 1rem; margin_right: 10px; justify-content: center; position: relative;">
						<div class="idquestion">질문 선택</div>
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
				<div class="qu_input_area">
					<div class="qu_input_container"
						style="justify-content: center; position: relative;">
						<input class="qu_input" id="id_question" name=""
							placeholder="질문 답변 입력" autocomplete="off">
					</div>
				</div>
				<div style="display: inline-flex; flex-direction: row; gap: 2rem;">
					<div class="mail_btn" id="pw_complete">인증완료</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 메일 발송 알림팝업  -->
	<div class="popup_wrap" id="popupSEND">
		<div class="find_area">
			<div class="close">
				<button class="btn_close" id="btn_closeSEND" type="button">X</button>
			</div>
			<h1 class="find_title">
				ID정보가 메일로<br>발송됐습니다
			</h1>
			<div class="findbtn"
				style="display: inline-flex; flex-direction: row; gap: 2rem; margin-left: 20px;">
				<div class="find_btn" id="login" onclick="location.href='login.jsp'">로그인</div>
				<div class="find_btn" id="findpw1">PW찾기</div>
			</div>
		</div>
	</div>
	<!-- 질문으로 계정알림  -->
	<div class="popup_wrap" id="popupIM">
		<div class="find_area">
			<div class="close">
				<button class="btn_close" id="btn_closeIM" type="button">X</button>
			</div>
			<h1 class="find_title">계정정보안내</h1>
			<div class="look_id"
				style="color: #696969; font-size: 12px; text-align: center;">회원님의
				계정은 '계정****' 입니다</div>
			<div class="findbtn"
				style="display: inline-flex; flex-direction: row; gap: 2rem; margin-left: 30px;">
				<div class="find_btn" id="login" onclick="location.href='login.jsp'">로그인</div>
				<div class="find_btn" id="findpw2">PW찾기</div>
			</div>
		</div>
	</div>
	<!-- 질문 인증후 비밀번호 변경  -->
	<div class="popup_wrap" id="popup_complete">
		<div class="find_area">
			<div class="close">
				<button class="btn_close" type="button" id="btn_closecomplete">X</button>
			</div>
			<h1 class="find_title">비밀번호 변경</h1>
			<div class="question_box">
				<div class="qu_input_area">
					<div class="qu_input_container"
						style="justify-content: center; position: relative;">
						<input class="qu_input" id="id_question" name=""
							placeholder="새 비밀번호 입력" autocomplete="off">
					</div>
					<div class="qu_input_container"
						style="justify-content: center; position: relative;">
						<input class="qu_input" id="id_question" name=""
							placeholder="새 비밀번호 확인 " autocomplete="off">
					</div>
				</div>
				<div style="display: inline-flex; flex-direction: row; gap: 2rem;">
					<div class="mail_btn" id="complete">변경완료</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 메일 인증후 비밀번호 변경창 -->
	<div class="popup_wrap" id="popup_mailcom">
		<div class="find_area">
			<div class="close">
				<button class="btn_close" type="button" id="btn_closemailcom">X</button>
			</div>
			<h1 class="find_title">비밀번호 변경</h1>
			<div class="question_box">
				<div class="qu_input_area">
					<div class="qu_input_container"
						style="justify-content: center; position: relative;">
						<input class="qu_input" id="id_question" name=""
							placeholder="새 비밀번호 입력" autocomplete="off">
					</div>
					<div class="qu_input_container"
						style="justify-content: center; position: relative;">
						<input class="qu_input" id="id_question" name=""
							placeholder="새 비밀번호 확인 " autocomplete="off">
					</div>
				</div>
				<div style="display: inline-flex; flex-direction: row; gap: 2rem;">
					<div class="mail_btn" id="mail_com">변경완료</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 메일로 비밀번호 변경완료 알림창  -->
	<div class="popup_wrap" id="popup_pwcomplete">
		<div class="find_area">
			<div class="close">
				<button class="btn_close" id="btn_close_pwcomplete" type="button">X</button>
			</div>
			<h1 class="find_title">비밀번호가 변경되었습니다</h1>
			<div class="findbtn"
				style="display: inline-flex; flex-direction: row; gap: 2rem; margin-left: 20px;">
				<div class="find_btn" id="findid"
					onclick="location.href='login.jsp'">로그인</div>
				<div class="find_btn" id="findpw">전체 로그아웃</div>
			</div>
		</div>
	</div>
	<!-- 질문으로 비밀번호 변경완료 알림창  -->
	<div class="popup_wrap" id="popup_qucomplete">
		<div class="find_area">
			<div class="close">
				<button class="btn_close" id="btn_close_qucomplete" type="button">X</button>
			</div>
			<h1 class="find_title">비밀번호가 변경되었습니다</h1>
			<div class="findbtn"
				style="display: inline-flex; flex-direction: row; gap: 2rem; margin-left: 20px;">
				<div class="find_btn" id="findid"
					onclick="location.href='login.jsp'">로그인</div>
				<div class="find_btn" id="findpw">전체 로그아웃</div>
			</div>
		</div>
	</div>
	<!--팝업 영역  -->
	<form action="/mylittletest/login" method="post"
		name="loginform" id="loginform">
			<sec:csrfInput />

		<div class="login_area">
			<h1 class="login_title" style="color: #F8F8FF;">로그인하기</h1>
			<div class="emoji">
				<img src="./img/studying_11772194.png"
					style="width: 100px; height: 100px;">
			</div>
			<div class="id_input_area">
				<div class="id_input_container">
					<input class="id_input" id="userId" name="userId" placeholder="계정"
						autocomplete="off">
				</div>
			</div>
			<div class="pw_input_area">
				<div class="pw_input_container">
					<input class="pw_input" type="password" id="password"
						name="password" placeholder="비밀번호">
				</div>
				<div class="find_id" id="popup_open">계정을 잃어버렸으면?</div>
			</div>
			<div style="display: inline-flex; flex-direction: row; gap: 2rem;">
				<input type="submit" name="loginbtn">로그인 하기
			</div>
		</div>
		<div class="join_btn"
			onclick="location.href='/mylittletest/join'">회원가입
			하기</div>
	</form>
</body>

<jsp:include page="./include/tail.jsp"></jsp:include>