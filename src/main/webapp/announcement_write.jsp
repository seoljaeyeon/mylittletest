<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./include/head.jsp"></jsp:include>
<style>
	.a {
	border: 2px solid black;	
	border-radius: 30px;
	width: 800px;
	height: 500px;
	display: inline-block;
	}
	
	.b {
	margin-left: 50px;
	margin-right: 50px;	
	margin-top: 20px;

	}						
</style>
	<div class="a">
	<div class="b">&nbsp&nbsp<span style="font-size: 20px; font-weight: bolder;">공지사항</span>
	<br><br>
	<div style="border: none; outline: none; border-bottom: 1px solid white;">
	<input style="font-size: 25px; font-weight: bolder;" placeholder="제목을 입력해주세요.">
	<span style="margin-left: 286px;"><a href="announcement_list.jsp"><button type="button">돌아가기</button></a></span></div>
	<br>
	<div style="margin-top: 30px; margin-bottom: 30px;">
	본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 
	본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다.
	본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다.
	본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 
	본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다.  
	감사합니다.
	</div>
	<div style="border: none; outline: none; border-bottom: 1px solid white;"></div>
	<br><br>
	<div><input type="file"></div>
	<div align="right"><select><option selected>지금작성</option></select>
	<button type="button">작성완료</button>
	</div>
	</div>
	</div>
<jsp:include page="./include/tail.jsp"></jsp:include>		