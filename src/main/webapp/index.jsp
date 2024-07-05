<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./include/head.jsp"></jsp:include>
<style>
	.main_container{
		display: inline-flex;
    	flex-direction:column;
    	margin-left: 120px;
   		width: calc(100% - 14rem);
        background-color: #474747;
        padding: 1.5rem 2rem 2rem 2rem;
        border-radius: 2rem;
        height: 680px;
        gap:10px;
	}
	.head_box{
		width:100%;
		height: fit-content;
		display:inline-flex;
		min-width: 1106px;
	}
	.goal_box {
		border-right:1px solid black;
		height:140px;
		width:368px;
		min-width:368px;
		
	}
	.goal_title{
		font-size:40px;
		text-align:center;
		height:fit-content;
	}
	.setting{
		display:flex;
		margin-top:1rem;
		justify-content: flex-end;
		margin-right:2rem;
	}
	.goal_btn{
		margin-left:2rem; 	 
		margin-right: 65px;
		background-position: center;
    	background-size: cover;
    	-webkit-appearance: none;
		-moz-appearance: none;
		appearance: none;
		background-color: #333333;
		color: #ffffff;
		border-radius: 10px;
		height: 3rem;
		width: 120px;
		padding: auto;
		justify-content: center;
		align-items: center;
		font-size: 1rem;
		text-align:center;
		display:flex;
		cursor:pointer;
		border: none;
	}
	.goal_set{
		background-position: center;
    	background-size: cover;
		float:right;
		font-size:30px;
		min-width:65px;
	}
	.goal_achieve{
		 float: right;
	}
	.goal_success{
			display:inline-flex;
			width: 200px;
			hight:50px;
			gap: 0.2rem;
			flex-wrap:wrap;
			font-size:14px;
	}
	.list_box{
		display:inline-flex;
		width:1100px;
		justify-content: flex-end;
		margin-top:30px;
		gap:5px;
	}
	.list_set{
		display:flex;
		float:right;
		font-size:20px;
	}
	.sub_box{
		display:inline-flex;
		width: 1100px;
		gap: 5rem;
		flex-wrap:wrap;
		font-size:40px;
		margin-left:30px;
		margin-top:10px;
	}
	.sub_menu{
		border:1px solid black;
		width: 45%;
		height: 8rem;
		text-align:center;
		justify-content: center;
		align-items: center;
		display:flex;
		background-color:#333333;
		border-radius:10px;
	}
</style>
<div class="main_container">
	<div class="head_box">
		<div class="goal_box">
			<div class="goal_title">😀 오늘의 목표</div>
			<div class="setting" style="margin-top:30px">		
				<div class="goal_btn">설정 완료</div>
				<div class="goal_set"><span style="font-size:16px;">▲▼</span> 90개</div>
			</div>
		</div>
		<div class="goal_box">
			<div class="goal_title">🎯 현재 달성률</div>
			<div class="setting">		
				<div class="goal_achieve" style="font-size:35px; margin-top:20px"><span style="font-size:15px;">(88/100)</span> 88%</div>
			</div>
		</div>
		<div class="goal_box">
			<div class="goal_title">🏆 최근 정답률</div>
			<div class="setting">	
				<div class="goal_success">
					<div class="success_count"><span>1일 전 10%</span></div>
					<div class="success_count"><span>2일 전 20%</span></div>
					<div class="success_count"><span>3일 전 50%</span></div>
					<div class="success_count"><span>4일 전 80%</span></div>
					<div class="success_count"><span>5일 전 70%</span></div>
					<div class="success_count"><span>6일 전 60%</span></div>
				</div>	
				<div class="goal_set" style="margin-top:20px; font-size:35px;">95%</div>
			</div>
		</div>
	</div>
	<div class="list_box">
		<div class="list_set" onclick="location.href=''">⏳문제 목록 관리</div>
		<div class="list_set" onclick="location.href=''">⏳오늘 본 문제 목록</div>
	</div>
	<div class="sub_box">
		<div class="sub_menu" onclick="location.href=''">🤓문제 쓰기</div>
		<div class="sub_menu" onclick="location.href=''">📚내 문제 풀기</div>
		<div class="sub_menu" onclick="location.href=''">📘맞춘 문제 복습</div>
		<div class="sub_menu" onclick="location.href=''">📕틀린 문제 복습</div>
		<div class="sub_menu" onclick="location.href=''">📖오늘 본 문제 복습</div>
		<div class="sub_menu" onclick="location.href=''">❤북마크 문제 복습</div>
	</div>
</div>



<jsp:include page="./include/tail.jsp"></jsp:include>