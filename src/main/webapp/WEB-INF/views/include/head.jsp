<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>		
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<!-- CSRF 메타 태그 추가 -->
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>



</head>
<body>
	<style>
body {
	background-color: #292929;
	font-family: 'Inter', sans-serif;
	box-sizing: border-box;
	padding: 1rem;
	margin: 0;
	color: #eeeeee;
}

.side_container {
	width: 10rem;
	background-color: #474747;
	display: flex;
	flex-direction: column;
	align-items: center;
	gap: 2rem;
	padding: 1.5rem 2rem 2rem 2rem;
	border-radius: 2rem;
	height: calc(90vh - 8.8rem);
	min-height: calc(90vh - 8.8rem);
	max-height: calc(90vh - 8.8rem);
	min-width:10rem;
}

.side_button {
	width: 100%;
	background-color: #333333;
	color: #eeeeee;
	border-radius: 1rem;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 1rem;
	min-height: 4rem;
	height: 3rem;
	font-weight: 600;
	box-shadow: 0.25rem 0.25rem 0.5rem 0rem rgba(0, 0, 0, 0.2);
	cursor:pointer;
}

.logo {
	min-width: 10rem;
	width: 10rem;
	border-radius: 1.5rem;
	height: 7rem;
	display: flex;
	justify-content: center;
	align-items: center;
	place-items: center;
	overflow: hidden;
	margin-right: 2rem;
	cursor:pointer;
}

.titlebar {
	width: 100wh;
	height: 8rem;
	border-radius: 2rem;
	background-color: #474747;
	margin-bottom: 0.8rem;
	display: flex;
	align-items: center;
	padding: 0.6rem 4rem 0.6rem 2rem;
	gap: 2rem;
	overflow: hidden;
}

.ad-container {
	width: calc(100% -15rem);
	height: 100%;
	overflow: hidden;
	display: flex;
	gap: 2rem;
	flex-direction: row;
	overflow: hidden;
	align-items: center;
}

.top-ads {
	min-width: 20rem;
	width: 20rem;
	border-radius: 1.5rem;
	height: 6rem;
	display: flex;
	justify-content: center;
	align-items: center;
	place-items: center;
	overflow: hidden;
	box-shadow: 0.25rem 0.25rem 0.5rem 0rem rgba(0, 0, 0, 0.2);
}

.ads_img {
	width: 100%;
}

.main-content {
	width: calc(100% - 14rem);
	background-color: #474747;
	padding: 1.5rem 2rem 2rem 2rem;
	border-radius: 2rem;
	height: calc(90vh - 8.8rem);
	min-height: calc(90vh - 8.8rem);
	max-height: calc(90vh - 8.8rem);
	min-width:800px;
}
  /* 팝업스타일  */
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
		
        .loginpopup_area {
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
		.loginpopup_title {
			font-size: 18px;
			margin-bottom: 3rem;
			text-align:center;
			color:#000000;
		}
		.loginpopup_btn,.deletepopup_btn{
			box-shadow: 0.3rem 0.3rem 0.7rem #cccccc, -0.3rem -0.3rem 0.7rem #dedede;
			background-color: #000000;
			color: #ffffff;
			border-radius: 1rem;
			height: 3rem;
			width: 100px;
			padding: auto;
			display: flex;
			justify-content: center;
			align-items: center;
			font-size: 1rem;
			text-align:center;
			margin-left:1rem;
			font-weight:bold;
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
		.show {
			display:block;
		}	
</style>
	<div class="titlebar">
		<div class="logo" onclick="location.href='/mylittletest/index'">
			<span style="font-size: 5rem">🤓</span>
		</div>
		<div class="ad-container">
			<div class="top-ads">
				<img
					src="https://res.heraldm.com/content/image/2022/06/17/20220617000554_0.jpg"
					class="ads_img" alt="">
			</div>
			<div class="top-ads">
				<img
					src="https://cdn.getnews.co.kr/news/photo/202103/522191_209313_3950.jpg"
					class="ads_img" alt="">
			</div>
			<div class="top-ads">
				<img
					src="https://ojsfile.ohmynews.com/PHT_IMG_FILE/2023/1129/IE003233784_PHT.jpg"
					class="ads_img" alt="">
			</div>
			<div class="top-ads">
				<img
					src="https://cdn.getnews.co.kr/news/photo/202103/522191_209313_3950.jpg"
					class="ads_img" alt="">
			</div>
			<div class="top-ads">
				<img
					src="https://res.heraldm.com/content/image/2022/06/17/20220617000554_0.jpg"
					class="ads_img" alt="">
			</div>
		</div>
	</div>
	<div style="display: flex; flex-direction: row; gap: 0.8rem;">
		<aside class="side_container">
			<c:choose>
				<c:when test="${ userVO != null }">
					 <div class="side_button" onclick="location.href='/mylittletest/mypage_alarm'">
	                	마이페이지
		           	 </div>
		            <div class="side_button" onclick="location.href='/mylittletest/index'">
		                나의 학습
		            </div>        
		            <div class="side_button" onclick="location.href='/mylittletest/questionlist'">
		                문제 둘러보기
		            </div>        
		            <div class="side_button" onclick="location.href='/mylittletest/announcement_list'">
		                공지사항
		            </div>        
		            <div class="side_button manager_contact_button" id="admin_btn" >
		                관리자 연락
		            </div>
		            <div class="side_button manager_contact_button" onclick="location.href='/mylittletest/admin_dashboard'"  >
		                관리자 메뉴
		            </div>
		             <div class="side_button manager_contact_button" id="logout_btn" name="logout_btn">
		                로그아웃
		            </div>
		            <script>
   var csrfToken = $("meta[name='_csrf']").attr("content");
   var csrfHeader = $("meta[name='_csrf_header']").attr("content");
   
   document.getElementById('logout_btn').addEventListener('click', function() {
	    fetch('/mylittletest/logout', {
	        method: 'POST',
	        headers: {
               'Content-Type': 'application/x-www-form-urlencoded',
               [csrfHeader]: csrfToken
	        }
	    })
	    .then(response => {
	        if (response.ok) {
	            window.location.href = '/mylittletest/login';
	        } else {
	            console.error('Logout failed:', response);
	        }
	    })
	    .catch(error => {
	        console.error('Error:', error);
	    });
	});
</script>
		            <hr style="width:100%; opacity:0.6; margin-top:auto">
		            <div class="side_button">
		                웹사이트 운영 정책
		            </div>
				</c:when>
				<c:otherwise>
					<div class="side_button" onclick="location.href='/mylittletest/login'">로그인</div>
					<div class="side_button" id="mystudy_btn">나의 학습</div>
					<div class="side_button" onclick="location.href='/mylittletest/questionlist'">문제 둘러보기</div>
					<div class="side_button" onclick="location.href='/mylittletest/announcement_list'">공지사항</div>
					<div class="side_button manager_contact_button" id="admin_btn">관리자 연락</div>
					<hr style="width: 100%; opacity: 0.6; margin-top: auto">
					<div class="side_button">웹사이트 운영 정책</div>
				</c:otherwise>
			</c:choose>
		</aside>
		 <!-- 팝업 영역  -->
        <div class="popup_wrap" id="popup_login">
			<div class="loginpopup_area">
				<h1 class="loginpopup_title">로그인이 필요한 메뉴입니다</h1>
				<div class="loginbtn" style="display:inline-flex; flex-direction:row; gap:2rem; ">
		            <div class="loginpopup_btn" id="loginok" onclick="location.href='login.jsp'">로그인</div>
		            <div class="deletepopup_btn" id="logindelete" style="background-color:#ffffff;color:black; ">취소</div>
		        </div>
			</div>
		</div>
		<!-- 팝업 영역  -->
		<!-- 관리자 연락 팝업창  -->
		<div class="popup_wrap" id="popup_admin">
			<div class="loginpopup_area">
				<div class="close"><button class="btn_close" id="btn_close" type="button">X</button></div>
				<div class="admin_title" style="color:black; font-size:20px; font-weight:bolder; margin-top:24px; margin-bottom:24px; text-align:center;">mailto 활용/<br>관리자메일주소로 이메일</div>
			</div>
		</div>
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
       
       document.getElementById("admin_btn").addEventListener("click", function() {
           togglePopup('popup_admin', 'toggle');
       });

       // 관리자 팝업 닫기 버튼에 클릭 이벤트 추가
       document.getElementById("btn_close").addEventListener("click", function() {
           togglePopup('popup_admin', 'hide');
       });

       // 로그인 팝업 열기 버튼에 클릭 이벤트 추가
       document.getElementById("mystudy_btn").addEventListener("click", function() {
           togglePopup('popup_login', 'toggle');
       });

       // 로그인 팝업 닫기 버튼에 클릭 이벤트 추가
       document.getElementById("logindelete").addEventListener("click", function() {
           togglePopup('popup_login', 'hide');
       });
       
       document.getElementById("admin_btn").addEventListener("click", function() {
           togglePopup('popup_admin', 'toggle');
       });

       // 로그인 팝업 닫기 버튼에 클릭 이벤트 추가
       document.getElementById("btn_close").addEventListener("click", function() {
           togglePopup('popup_admin', 'hide');
       });
     	
	});
</script>
		<main class="main-content">