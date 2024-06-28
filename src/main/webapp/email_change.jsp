<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>이메일 변경 팝업창</title>
	</head>
	<style>
		body {
		  margin: 0;
		}
				
		.item {
		  position: absolute;
		  left: 50%;
		  top: 50%;
		  transform: translate(-50%, -50%);
		  padding: 30px;
		}		
	</style>
	<body>
		<div class="item">
		<input type="text" placeholder="새 이메일을 입력하세요." style="border: 2px solid black; width: 400px; height: 30px; font-size: 20px;">
		<button type="button">전송</button>
		</div>		
	</body>
</html>