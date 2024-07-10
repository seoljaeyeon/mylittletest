<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./include/head_login.jsp"></jsp:include>
<script>
	document.addEventListener("DOMContentLoaded", function() {
 		function togglePopup(popupId, action) {
            var popup_mypage = document.getElementById(popupId);
            if (action === 'show') {
                popup_mypage.classList.add('show');
            } else if (action === 'hide') {
                popup_mypage.classList.remove('show');
            } else if (action === 'toggle') {
                popup.classList.toggle('show');
            }
        }

        // 계정비활성화 팝업 열기 버튼에 클릭 이벤트 추가
        document.getElementById("idstopbtn").addEventListener("click", function() {
            togglePopup('popup_idstop', 'show');
        });

        // 계정찾기 팝업 닫기 버튼에 클릭 이벤트 추가
        document.getElementById("btn_close_idstop").addEventListener("click", function() {
            togglePopup('popup_idstop', 'hide');
        });

        // 닉네임 변경 팝업 열기 버튼에 클릭 이벤트 추가
        document.getElementById("nicknamebtn").addEventListener("click", function() {
            togglePopup('popup_idstop', 'hide');
            togglePopup('popup_nickname', 'show');
        });

        // 닉네임 변경 팝업 닫기 버튼에 클릭 이벤트 추가
        document.getElementById("btn_close_nickname").addEventListener("click", function() {
            togglePopup('popup_nickname', 'hide');
        });
        
        // 이메일 변경 팝업 열기 버튼에 클릭 이벤트 추가
        document.getElementById("email_btn").addEventListener("click", function() {
            togglePopup('popup_email', 'show');
        });

        // 이메일 변경 팝업 닫기 버튼에 클릭 이벤트 추가
        document.getElementById("btn_close_email").addEventListener("click", function() {
            togglePopup('popup_email', 'hide');
        });
        // 비밀번호 변경 팝업 열기 버튼에 클릭 이벤트 추가
        document.getElementById("password_btn").addEventListener("click", function() {
            togglePopup('popup_password', 'show');
        });

        // 비밀번호 변경 팝업 닫기 버튼에 클릭 이벤트 추가
        document.getElementById("btn_close_pw").addEventListener("click", function() {
            togglePopup('popup_password', 'hide');
        });
        // 프로필 사진 변경
        const profilePicture = document.getElementById("profilePicture");
        const pictureBtn = document.getElementById("picture_btn");
        const fileInput = document.getElementById("fileInput");
        const profileImg = document.getElementById("profileImg");

        const openFileDialog = () => {
            fileInput.click();
        };

        profilePicture.addEventListener("click", openFileDialog);
        pictureBtn.addEventListener("click", openFileDialog);

        fileInput.addEventListener("change", (event) => {
            const file = event.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = (e) => {
                    profileImg.src = e.target.result;
                };
                reader.readAsDataURL(file);
            }
        });
   	 
});
</script>
<style>
	.mypage_container{
		display: inline-flex;
    	margin-left: 120px;
   		width: fit-content;
        background-color: #474747;
        padding: 1.5rem 2rem 2rem 2rem;
        height:750px; 
        gap:50px;
        justify-content: center;
		align-items: center;
	}
	.profile-img {
	    width: 130px;
	    height: 130px;
	    border-radius: 50%;
	    margin-top:10px;
	}
	.profile_box{
		height:fit-content;
		width: 300px;
	}
	.picture{
		text-align:center;
		font-size:20px;
		width:298px;
		height:150px;
	}

	.profile{
		text-align:center;
		border: 1px solid black;
		border-radius:30px;
	}
	.change{
		color:#cccccc;
		font-size:12px;
		cursor: pointer;
	}
	.nickname, .email{
		margin-top:20px;
	}
	.password{
		appearance: none;
		background-color: #333333;
		color: #ffffff;
		border-radius: 10px;
		height: 3rem;
		width: 150px;
		justify-content: center;
		align-items: center;
		font-size: 1rem;
		text-align:center;
		display:flex;
		cursor:pointer;
		border: none;
		margin-top:30px;
		margin-left:75px;
	}
	.sub_menu{
		text-align:center;
		margin-top:80px;
	}
	.bookmark_list_btn,.alarm_list_btn{
		background-color: #333333;
		color: #ffffff;
		height: 4rem;
		width: 300px;
		margin-top:30px;
		cursor:pointer;
		justify-content: center;
		align-items: center;
		display:flex;
		border-radius:20px;
	}
	.alarm_container{
		display:flex;
		width:1000px;
		gap:30px;
	}
	.alarm_list{
		border:1px solid black;
		margin:20px;
		width:950px;
		margin-top:0px;
		border-radius:20px;
	}
	.alarm_title{
		margin-left:20px;
		font-size:30px;
		font-weight:bold;
		margin-top:20px;
	}
	.alarm_main{
		margin:20px;
		width:900px;	
	}
	.alarm{
		display:flex;
		border-bottom: 1px solid #cccccc;
	}
	.sub{
		height:25px;
		margin-top:10px;
		margin-bottom:8px;
	}
	.change{
		cursor:pointer;
	}
	/********************************* 팝업스타일  *****************************/
	
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
		.idstop_area {
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
		.idstop_title {
			font-size: 1.5rem;
			margin-bottom: 1rem;
			text-align:center;
			color:#000000;
		}
		.danger_note{
			color:#cccccc;
			font-size:12px;
			text-align:center
		}
		.idstop_input{
		    border: none;
		    outline: none;
		    font-size: 14px;
		    position:relative;
		    font-family: 'Pretendard-Regular';
		    width: 100%;
		    background-color:#ffffff;
		    color:#000000;
		    margin-top:10px;
		    border-bottom:1px solid #cccccc;
		}
		.idstop_btn {
			appearance: none;
			box-shadow: 0.3rem 0.3rem 0.7rem #cccccc, -0.3rem -0.3rem 0.7rem #dedede;
			background-color: #000000;
			color: #ffffff;
			border-radius: 20px;
			height: 3rem;
			width: 120px;
			display: flex;
			justify-content: center;
			align-items: center;
			font-size: 15px;
			text-align:center;
			margin-left:5.5rem;
			margin-top:10px;
		}
		
		.idstop_btn:hover { 
			background-color: #e0e0e0;
		}
		.nickname_area {
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
		.nickname_input_container{
			display:inline-flex;
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
		.nickname_title {
			font-size: 1.5rem;
			margin-bottom: 1rem;
			text-align:center;
			color:#000000;
		}
		.nickname_note{
			color:#cccccc;
			text-align:center
		}
		.nickname_input{
		    border: none;
		    outline: none;
		    font-size: 14px;
		    position:relative;
		    font-family: 'Pretendard-Regular';
		    width: 100%;
		    background-color:#ffffff;
		    color:#000000;
		    margin-top:10px;
		    border-bottom:1px solid #cccccc;
		    display:flex;
		    margin-left:40px;
		}
		.nickname_btn {
			appearance: none;
			box-shadow: 0.3rem 0.3rem 0.7rem #cccccc, -0.3rem -0.3rem 0.7rem #dedede;
			background-color: #000000;
			color: #ffffff;
			border-radius: 20px;
			height: 3rem;
			width: 120px;
			display: flex;
			justify-content: center;
			align-items: center;
			font-size: 15px;
			text-align:center;
			margin-left:5.5rem;
			margin-top:30px;
		}
		
		.nickname_btn:hover { 
			background-color: #e0e0e0;
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
		.find_title {
			font-size: 1.5rem;
			margin-bottom: 1rem;
			text-align:center;
			color:#000000;
		}
		.em_input{
		    border: none;
		    outline: none;
		    font-size: 1rem;
		    position:relative;
		    font-family: 'Pretendard-Regular';
		    width: 100%;
		    background-color:#ffffff;
		    color:#000000;
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
			font-size:12px;
		}
		.em_input_area {
		  	display:inline-flex;
		   	flex-direction:column;
		    width:80%;
		    background-color:#ffffff;
		    height:fit-content;
		}
		.em_input_container  {
		    padding-bottom: 0.3rem; border-bottom: 2px solid #cccccc;
		    display:flex; 
		    flex-direction: row;
		    transition: all 0.2s ease-out;
		    width:100%;
		    justify-content: space-between;
		    position:relative;
		    background-color:#ffffff;
		    height:fit-content;
		    margin-bottom:1rem;
		}
		.mail_box{
			margin-left:2rem;
			margin-top:2rem;
		}
		.mail_btn{
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
		text-align:center;
		margin-left:2rem;
    	}
    	.qu_input_area {
		  	display:inline-flex;
		   	flex-direction:column;
		    width:80%;
		    background-color:#ffffff;
		    height:fit-content;
		}
		.qu_input_container  {
		    padding-bottom: 0.3rem; border-bottom: 2px solid #cccccc;
		    display:flex; 
		    flex-direction: row;
		    transition: all 0.2s ease-out;
		    width:100%;
		    justify-content: space-between;
		    position:relative;
		    background-color:#ffffff;
		    margin-bottom:1rem;
		}
		.question_box{
			margin-left:2rem;
			margin-top:2rem;
		}
		.qu_input{
		    border: none;
		    outline: none;
		    font-size: 1rem;
		    position:relative;
		    font-family: 'Pretendard-Regular';
		    width: 100%;
		    background-color:#ffffff;
		    color:#000000;
		}
		.show{
		display: block;
		}
		
</style>
<!------------------------- 팝업영역 ----------------------------------->

<!------------------------계정 비활성화 팝업 ----------------------------->
	<div class="popup_wrap" id="popup_idstop">
		<div class="idstop_area">
			<div class="close">
				<button class="btn_close" type="button" id="btn_close_idstop">X</button>
			</div>
			<h1 class="idstop_title">계정 비활성화</h1>
			<div class="idstop_box">
				<div class="idstop_input_area">
					<div class="danger_note">경고 문구 영역</div>
					<div class="idstop_input_container" style="justify-content: center; position: relative;">
						<input class="idstop_input" id="idstop" name="" placeholder="계정 비활성화를 입력해주세요" autocomplete="off">
					</div>
				</div>
				<div style="display:inline-flex; flex-direction:row; gap:2rem;">
		            <div class="idstop_btn" id="idstop_com" onclick="location.href='mypage_alarm.jsp'">계정 비활성화</div>
		        </div>
			</div>
		</div>
	</div>
<!----------------------------닉네임 변경팝업 -------------------------------------------------->
<div class="popup_wrap" id="popup_nickname">
		<div class="nickname_area">
			<div class="close">
				<button class="btn_close" type="button" id="btn_close_nickname">X</button>
			</div>
			<h1 class="nickname_title">닉네임 변경</h1>
			<div class="nickname_box">
				<div class="nickname_input_area">
					<div class="nickname_input_container" style="justify-content: center; position: relative;">
						<input class="nickname_input" id="nickname" name="" placeholder="사용할 닉네임 입력" autocomplete="off">
						<div class="nickname_note" style="margin-top:10px; height:22px; font-size:8px;">경고 문구 영역</div>
					</div>
				</div>
				<div style="display:inline-flex; flex-direction:row; gap:2rem;">
		            <div class="nickname_btn" id="nickname_com" onclick="location.href='mypage_alarm.jsp'">변경완료</div>
		        </div>
			</div>
		</div>
	</div>
<!-------------------------------------- 이메일 변경 팝업창 ------------------------------------------------------------------------------>
<div class="popup_wrap" id="popup_email">
		<div class="find_area">
			<div class="close">
				<button class="btn_close" type="button" id="btn_close_email">X</button>
			</div>
			<h1 class="find_title">새 메일 등록</h1>
			<div class="mail_box">
				<div class="em_input_area" id="email">
					<div class="em_input_container">
						<input class="em_input" id="userMail" name="userMail" placeholder="이메일" style="margin-right: 1rem;" autocomplete="off">
						<div class="send_email_btn">전송</div>
					</div>
				</div>
				<div class="em_input_area">
					<div class="em_input_container"
						style="margin-bottom: 2rem; justify-content: center; position: relative;">
						<input class="em_input" id="code" name="code" placeholder="이메일 코드 확인" autocomplete="off">
					</div>
				</div>
			<div style="display:inline-flex; flex-direction:row; gap:2rem;">
	            <div class="mail_btn" id="em_com" onclick="location.href='mypage_alarm.jsp'">인증완료</div>
	        </div>
			</div>
		</div>
	</div>
	<!---------------------------------------------- 비밀번호 변경 팝업창  --------------------------------------------------->
	<div class="popup_wrap" id="popup_password">
		<div class="find_area">
			<div class="close">
				<button class="btn_close" type="button" id="btn_close_pw">X</button>
			</div>
			<h1 class="find_title">비밀번호 변경</h1>
			<div class="question_box">
				<div class="qu_input_area">
					<div class="qu_input_container" style="justify-content: center; position: relative;">
						<input class="qu_input" id="id_question" name="" placeholder="기존 비밀번호 입력" autocomplete="off">
					</div>
					<div class="qu_input_container" style="justify-content: center; position: relative;">
						<input class="qu_input" id="id_question" name="" placeholder="새 비밀번호 입력" autocomplete="off">
					</div>
					<div class="qu_input_container" style="justify-content: center; position: relative;">
						<input class="qu_input" id="id_question" name="" placeholder="새 비밀번호 확인 " autocomplete="off">
					</div>
				</div>
				<div style="display:inline-flex; flex-direction:row; gap:2rem;">
		            <div class="mail_btn" id="pw_complete"  onclick="location.href='mypage_alarm.jsp'">변경완료</div>
		        </div>
			</div>
		</div>
	</div>
<!------------------------- 팝업영역 끝 ----------------------------------->
<div class="mypage_container">
	<div class="profile_box">
	 <input type="file" id="fileInput" style="display:none;">
		<div class="profile">
			<div class="picture" id="profilePicture">
				<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr4LGLIS6YPlOt2SVkhqYHMFc0sS2LdqpfOAYPIGb-TrZ2gr_u-01NvYdSXg&s" alt="프로필사진" id="profileImg" class="profile-img">
			</div>
			<div class="change" id="picture_btn">변경하기</div>
			<div class="nickname">
				USER_NICKNAME
			<div class="change" id="nicknamebtn">변경하기</div>
			</div>
			<div class="email">
				user@email.com
			<div class="change" id="email_btn">변경하기</div>
			</div>
			<div class="password" id="password_btn">비밀번호 변경</div>
			<div class="change" id="idstopbtn" style="margin-top:15px; margin-bottom:15px;">계정 비활성화</div>
		</div>
		<div class="sub_menu">
			<div class="bookmark_list_btn" onclick="location.href='mypage_bookmark.jsp'">즐겨찾기 & 북마크 목록</div>
			<div class="alarm_list_btn" style="margin-top:50px;" onclick="location.href='mypage_alarm.jsp'">알림 목록</div>
		</div>	
	</div>
	<div class="alarm_container">
		<div class="alarm_list">
			<div class="alarm_title">알림 목록</div>
			<div class="alarm_main">
				<div class="alarm">
					<div class="sub" style="width:150px; font-size:20px; font-weight:bold;">분류</div>
					<div class="sub" style="width:450px; font-size:20px; font-weight:bold;">알림 내용</div>
					<div class="sub" style="width:300px; font-size:20px; font-weight:bold;">시간</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px; font-size:18px;">좋아요</div>
					<div class="sub" style="width:450px; overflow:hidden; font-size:18px;">***님이 내문제에 좋아요를 눌렀습니다.***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px; font-size:18px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px; font-size:18px;">좋아요</div>
					<div class="sub" style="width:450px; overflow:hidden; font-size:18px;">***님이 내문제에 좋아요를 눌렀습니다.***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px; font-size:18px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px; font-size:18px;">댓글</div>
					<div class="sub" style="width:450px; overflow:hidden; font-size:18px;"">***님이 내문제에 댓글을 남겼습니다. ***님이 내문제에 댓글을 남겼습니다.</div>
					<div class="sub" style="width:300px; font-size:18px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px; font-size:18px;">신고</div>
					<div class="sub" style="width:450px; overflow:hidden; font-size:18px;"">회원님의 신고가 처리됐습니다.</div>
					<div class="sub" style="width:300px; font-size:18px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px; font-size:18px;">댓글</div>
					<div class="sub" style="width:450px; overflow:hidden; font-size:18px;"">***님이 내문제에 댓글을 남겼습니다. ***님이 내문제에 댓글을 남겼습니다.</div>
					<div class="sub" style="width:300px; font-size:18px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px; font-size:18px;">좋아요</div>
					<div class="sub" style="width:450px; overflow:hidden; font-size:18px;">***님이 내문제에 좋아요를 눌렀습니다.***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px; font-size:18px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px; font-size:18px;">좋아요</div>
					<div class="sub" style="width:450px; overflow:hidden; font-size:18px;">***님이 내문제에 좋아요를 눌렀습니다.***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px; font-size:18px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px; font-size:18px;">좋아요</div>
					<div class="sub" style="width:450px; overflow:hidden; font-size:18px;">***님이 내문제에 좋아요를 눌렀습니다.***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px; font-size:18px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px; font-size:18px;">좋아요</div>
					<div class="sub" style="width:450px; overflow:hidden; font-size:18px;">***님이 내문제에 좋아요를 눌렀습니다.***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px; font-size:18px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px; font-size:18px;">좋아요</div>
					<div class="sub" style="width:450px; overflow:hidden; font-size:18px;">***님이 내문제에 좋아요를 눌렀습니다.***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px; font-size:18px;">2024-07-02 10:36:15</div>
				</div>
			</div>
			<div class="page" style="text-align:center;"> ◀ 1  2  3  4  5  6  7  8  9  10  ▶  </div>
		</div>
	</div>
</div>


<jsp:include page="./include/tail.jsp"></jsp:include>