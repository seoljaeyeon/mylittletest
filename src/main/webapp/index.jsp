<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./include/head.jsp"></jsp:include>
<style>
	.main_container{
		display: inline-flex;
    	flex-direction:column;
    	height: calc(90vh - 8.8rem);
    	margin-left: 30px;
   		width: calc(100% - 14rem);
        background-color: #474747;
        padding: 1.5rem 2rem 2rem 2rem;
        border-radius: 2rem;
        height: calc(90vh - 8.8rem);
        min-height:calc(90vh - 8.8rem);
        max-height: calc(90vh - 8.8rem);
	}
	.head_box{
		width:100%;
		height: fit-content;
		display:inline-flex;
		flex-direction:column;
		border:1px solid black;
	}
	.goal_box {
		border:1px solid black;
		height:120px;
		width:30%;
	}
	.goal_title{
		font-size:40px;
		text-align:center;
		height:fit-content;
		font-weight:bold;
	}
	.setting{
		display:flex;
		justify-content: flex-start;
		margin-left:2rem;
		margin-top:1rem;
	}
	.goal_btn{
		margin-right: 75px;
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
		padding : 0 0;
		justify-content: center;
		align-items: center;
		font-size: 1rem;
		text-align:center;
		margin-left:3rem; 	 
	}
	.goal_set{
		background-position: center;
    	background-size: cover;
		float:right;
		font-size:30px;
	}
</style>
<div class="main_container">
	<div class="head_box">
		<div class="goal_box">
			<div class="goal_title">😀 오늘의 목표</div>
			<div class="setting">		
				<div class="goal_btn">설정 완료</div>
				<div class="goal_set">90개</div>
			</div>
		</div>
	</div>
</div>



<jsp:include page="./include/tail.jsp"></jsp:include>