<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./include/head.jsp"></jsp:include>
<style>
	.mypage_container{
		display: inline-flex;
    	margin-left: 120px;
   		width: fit-content;
        background-color: #474747;
        padding: 1.5rem 2rem 2rem 2rem;
        height:750px; 
        gap:50px;
	}
	.profile_box{
		height:fit-content;
		width: 300px;
	}
	.picture{
		text-align:center;
		font-size:100px;
	}
	.profile{
		text-align:center;
		border: 1px solid black;
		border-radius:30px;
	}
	.change{
		color:#cccccc;
		font-size:12px;
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
		height:40px;
		margin-top:10px;
		
	}
</style>

<div class="mypage_container">
	<div class="profile_box">
		<div class="profile">
			<div class="picture">
				🤡
				<div class="change" id="picture_change">변경하기</div>
			</div>
			<div class="nickname">
				USER_NICKNAME
				<div class="change" id="nickname_change">변경하기</div>
			</div>
			<div class="email">
				user@email.com
				<div class="change" id="email_change">변경하기</div>
			</div>
			<div class="password" id="password_change">비밀번호 변경</div>
			<div class="change" style="margin-top:15px; margin-bottom:15px;">계정비활성화</div>
		</div>
		<div class="sub_menu">
			<div class="bookmark_list_btn">즐겨찾기 & 북마크 목록</div>
			<div class="alarm_list_btn" style="margin-top:50px;">알림 목록</div>
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
					<div class="sub" style="width:150px;">좋아요</div>
					<div class="sub" style="width:450px;">***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px;">좋아요</div>
					<div class="sub" style="width:450px;">***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px;">좋아요</div>
					<div class="sub" style="width:450px;">***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px;">좋아요</div>
					<div class="sub" style="width:450px;">***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px;">좋아요</div>
					<div class="sub" style="width:450px;">***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px;">좋아요</div>
					<div class="sub" style="width:450px;">***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px;">좋아요</div>
					<div class="sub" style="width:450px;">***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px;">좋아요</div>
					<div class="sub" style="width:450px;">***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px;">좋아요</div>
					<div class="sub" style="width:450px;">***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px;">2024-07-02 10:36:15</div>
				</div>
				<div class="alarm">
					<div class="sub" style="width:150px;">좋아요</div>
					<div class="sub" style="width:450px;">***님이 내문제에 좋아요를 눌렀습니다.</div>
					<div class="sub" style="width:300px;">2024-07-02 10:36:15</div>
				</div>
			</div>
		</div>
	</div>
</div>


<jsp:include page="./include/tail.jsp"></jsp:include>