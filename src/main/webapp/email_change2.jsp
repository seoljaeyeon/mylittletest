<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>이메일 변경 팝업창</title>
	</head>
	<style>
		.a {
		border: 2px solid black;	
		width: 250px;
		}
	</style>
	<body>
		<div class="a">
		<h2>이메일 변경</h2>
		<div style="display: inline-block;">
		<input style="border: none; outline: none; border-bottom: 1px solid black;" type="text" placeholder="새 이메일을 입력하세요.">&nbsp&nbsp<button type="button">전송</button></div>
		<br><br>
		<div style="display: inline-block;"><input style="border: none; outline: none; border-bottom: 1px solid black;" type="text" placeholder="인증번호를 입력하세요."></div>
		<br><br>
		&nbsp&nbsp&nbsp&nbsp<div style="display: inline-block;"><button type="button">완료</button></div>
		&nbsp&nbsp&nbsp&nbsp<div style="display: inline-block;"><button type="button">취소</button></div>
		</div>
	</body>
</html>