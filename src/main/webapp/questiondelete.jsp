<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./include/head_login.jsp"></jsp:include>
<style>
	.back_btn{
		appearance: none;
		background-color: #696969;
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
		margin-left:330px;
		margin-top:50px;
	}
</style>
<div class="question_delete_box" style="margin:auto; width:800px; height:800px; text-align: center; border:1px solid black; border-radius:20px; background-color:#333333">
	<div><span style="font-size:400px;">☠</span></div>
	<div style="font-size:40px; font-weight:bolder">비활성화된 문제입니다</div>
	<div class="back_btn" onclick="location.href='questionlist_login.jsp'">돌아가기</div>
</div>
<jsp:include page="./include/tail.jsp"></jsp:include>	