<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>신고하기 팝업창</title>
	</head>
	<style>
		.a {
		border: 2px solid black;			
		}
	</style>
	<body>
		<div class="a">
		<div style="display: inline-block;"><h3>신고하기</h3></div>&nbsp&nbsp
		<div style="display: inline-block;"><input type="text" style="height:50px; text-align: center;" placeholder="경 고 문 구"></div>
		<div>
		<select>
			<option selected>신고분류</option>
			<option>문제</option>
			<option>댓글</option>
		</select>
		</div>
		<br><br>
		&nbsp&nbsp&nbsp&nbsp<div style="display: inline-block;"><button type="button">완료</button></div>
		&nbsp&nbsp&nbsp&nbsp<div style="display: inline-block;"><button type="button">취소</button></div>
		</div>
	</body>
</html>