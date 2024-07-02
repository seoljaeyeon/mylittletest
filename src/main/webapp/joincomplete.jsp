<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./include/head.jsp"></jsp:include>
<style>
		.join_area {
		   width: 30rem;
		    height: fit-content;
		    border-radius: 5rem;
		    background-color: #474747;
		    margin: 0 auto;
		    display: flex;
		    padding: 1rem;
		    margin-top: 3rem;
		    flex-direction: column;
		    align-items: center;
		    padding-bottom:3rem;
		    border:none;
		    box-shadow : 0.3rem 0.3rem 0.7rem #cccccc, -0.3rem -0.3rem 0.7rem #cccccc;
		}
		.join_title {
		    margin-top:3rem;
		    margin-bottom: 1rem;
		    height:max-content;
		    font-size:2rem;
		}
		.login_btn{
			-webkit-appearance: none;
		    -moz-appearance: none;
		    appearance: none;
		    box-shadow : 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
		    background-color: #000000;
		    color: #ffffff;
		    border-radius: 5rem;
		    height: 3rem;
		    width: 9rem;
		    padding: auto;
		    display:flex;
		    justify-content: center;
		    align-items: center;
		    font-family:'Pretendard-Regular';
		    font-size: 1rem;
		    margin-top:20px;
		   }
		   .index_btn{
			-webkit-appearance: none;
		    -moz-appearance: none;
		    appearance: none;
		    box-shadow : 0.3rem 0.3rem 0.7rem #696969, -0.3rem -0.3rem 0.7rem #696969;
		    background-color: #ffffff;
		    color: #000000;
		    border-radius: 5rem;
		    height: 3rem;
		    width: 9rem;
		    padding: auto;
		    display:flex;
		    justify-content: center;
		    align-items: center;
		    font-family:'Pretendard-Regular';
		    font-size: 1rem;
		    margin-top:20px;
		    font-weight:bold;
		   }
	</style>
<!-- 컨텐츠 영역  -->	
	<div class="join_area">
        <h1 class="join_title">환영합니다!</h1>
       <div class="emoji"><img src="./img/idea.png" style="width:100px; height:100px;"></div>
       <div style="opacity:0.3; width: 60%; margin-top:3rem; text-align:center; color:#eeeeee">회원가입이 완료됐습니다.<br>로그인하고 이제 학습을 시작해볼까요?</div>
        <div style="display:inline-flex; flex-direction:row; gap:2rem;">
            <div class="login_btn" onclick="location.href='./login.jsp'">로그인 하기</div>
            <div class="index_btn" onclick="location.href='./index.jsp'">메인으로 이동</div>
        </div>
    </div>
<!-- 컨텐츠 영역  -->	
<jsp:include page="./include/tail.jsp"></jsp:include>