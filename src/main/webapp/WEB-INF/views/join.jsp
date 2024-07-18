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
    
    const sendEmailBtn = document.querySelector('.send_email_btn');
    const timerMessage = document.getElementById('timerMessage');
    let timerInterval;

    function startTimer(duration, display) {
        let timer = duration;
        updateDisplay(timer, display); // 초기화면 설정

        console.log('타이머 시작:', timer); // 콘솔에 시작 메시지 출력

        timerInterval = setInterval(function () {
            let minutes = parseInt(timer / 60, 10);
            let seconds = parseInt(timer % 60, 10);

            minutes = minutes < 10 ? "0" + minutes : minutes;
            seconds = seconds < 10 ? "0" + seconds : seconds;

            display.textContent = minutes + ":" + seconds;
            console.log('남은 시간:', minutes, '분', seconds, '초'); // 콘솔에 남은 시간 출력

            if (--timer < 0) {
                clearInterval(timerInterval);
                display.textContent = "유효시간이 끝났습니다";
                console.log('타이머 종료'); // 콘솔에 종료 메시지 출력
            }
        }, 1000);
    }

    function updateDisplay(timer, display) {
        let minutes = parseInt(timer / 60, 10);
        let seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;
    }

    sendEmailBtn.addEventListener('click', function () {
        clearInterval(timerInterval); // 기존 타이머 제거
        const fiveMinutes = 5 * 60; // 5분을 초 단위로 계산
        startTimer(fiveMinutes, timerMessage); // 타이머 시작
    });
});
function goBack() {
    window.history.back();
}
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
	cursor:pointer;
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
    margin-left:80px;
}

a {
	color: #cccccc;
	font-size: 12px;
}
element.style {
    margin-left: 60px;
    display: flex;
}
</style>

<div class="join_area">
	<h1 class="join_title">환영합니다!</h1>
	<div class="emoji"  style="font-size:80px; text-align:center;">🐨</div>
	<script>
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	
	</script>
	<form class="input_area" name="join" id="join" method="post"
		action="${pageContext.request.contextPath}/join">
		<sec:csrfInput />
		    	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		
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
		<div class="pw_input_area" id="email_input">
			<div class="pw_input_container">
				<input class="pw_input" id="email" name="email" placeholder="이메일"
					style="margin-right: 1rem;" autocomplete="off">
			</div>
			<div class="send_email_btn">전송</div>
			<script>
				$(".send_email_btn").on("click", sendEmail);
				function sendEmail() {
					var email = $("#email").val();
					$.ajax({
						url : "/mylittletest/JoinCodeSend",
						type : "GET",
						data : {
							email : email
						},
			            beforeSend: function(xhr) {
		                    xhr.setRequestHeader(csrfHeader, csrfToken);
			            },
						success : function(response) {
							response.trim();
							if (response == "success") {
								alert("메일 전송에 성공했습니다");
								return;
							} else {
								alert("메일 전송에 실패했습니다. 다시 시도해주세요.");
								return;
							}
						},
						error : function(xhr, status, error) {
							alert("이메일 전송 중 오류가 발생했습니다: " + xhr.responseText);
						}
					});
				}
			</script>
		</div>
		<div class="id_input_area">
			<div class="id_input_container"
				style="margin-bottom: 2rem; justify-content: center; position: relative;">
				<input class="id_input" id="code" name="code"
					placeholder="이메일 확인 코드" autocomplete="off">
				<span id="timerMessage" style="text-align:right; color:#ffa500; font-weight:bolder; opacity: 0.3; position: absolute;  top: 110%; left: 0; margin: 0;">💡발송된 코드는 5분간 유효합니다.</span>
			</div>
		</div>
		<div class="id_find_area">
			<div class="id_find_container"
				style="margin-bottom: 2rem; justify-content: center; position: relative;">
				<div class="idquestion">계정정보찾기 질문</div>
				<div class="idfind_box">
					<select id="securityQuestion" name="securityQuestion"
						class="idfind">
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
				<input class="id_input" id="securityAnswer" name="securityAnswer"
					placeholder="질문 답변 입력" autocomplete="off">
			</div>
		</div>
		<button class="join_btn" type="button">회원가입</button>
		<script>
			$(".join_btn").on("click", checkValidity);
			function checkValidity() {
	            var authCode = $("#code").val();

	            $.ajax({
	                url: "/mylittletest/CheckCode",
	                type: "POST",
	                data: {
	                    code: authCode
	                },
		            beforeSend: function(xhr) {
	                    xhr.setRequestHeader(csrfHeader, csrfToken);
		            },
	                success: function(response) {
	                    response = response.trim();
	                    if (response === "valid") {
	                        alert("인증에 성공했습니다");
	                        $("#join").submit();
	                    } else {
	                        alert("인증 코드가 유효하지 않습니다.");
	                    }
	                },
	                error: function(xhr, status, error) {
	                    alert("인증 중 오류가 발생했습니다: " + xhr.responseText);
	                }
	            });
	        }
		</script>
		<br>
		<div class="login_box" style="margin-left:60px; display:flex;">
			<div onclick="goBack();" style="cursor:pointer;">돌아가기</div><div style="margin-left:15px; cursor:pointer;" onclick="location.href='/mylittletest/login'">기존계정 로그인하기</div>
		</div>
	</form>
</div>

<jsp:include page="./include/tail.jsp"></jsp:include>