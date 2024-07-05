<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팝업창</title>
</head>
<style>
	.popup_wrap {
		    display: ; 
		    position: fixed;
		    top: 0;
		    left: 0;
		    width: 100%;
		 	height: 100%;
		   	background-color: rgba(0, 0, 0, 0.5); 
		   	z-index: 1000; 
		    overflow: auto; 
		}
		
		.nickname_input_container{
			display:inline-flex;
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
		
		.nickname_note{
			color:#cccccc;
			text-align:center
		}
		.nickname_input{
		    border: none;
		    outline: none;
		    font-size: 14px;
		    position:relative;
		    font-family: 'Pretendard-Regular';
		    width: 100%;
		    background-color:#ffffff;
		    color:#000000;
		    margin-top:10px;
		    border-bottom:1px solid #cccccc;
		    display:flex;
		    margin-left:40px;
		}
		.nickname_btn {
			appearance: none;
			box-shadow: 0.3rem 0.3rem 0.7rem #cccccc, -0.3rem -0.3rem 0.7rem #dedede;
			background-color: #000000;
			color: #ffffff;
			border-radius: 20px;
			height: 3rem;
			width: 120px;
			display: flex;
			justify-content: center;
			align-items: center;
			font-size: 15px;
			text-align:center;
			margin-left:5.5rem;
			margin-top:30px;
		}
		
		.nickname_btn:hover { 
			background-color: #e0e0e0;
		}
		.show{
			display: block;
		}
		.find_area {
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
		.find_title {
			font-size: 1.5rem;
			margin-bottom: 1rem;
			text-align:center;
			color:#000000;
		}
		.em_input{
		    border: none;
		    outline: none;
		    font-size: 1rem;
		    position:relative;
		    font-family: 'Pretendard-Regular';
		    width: 100%;
		    background-color:#ffffff;
		    color:#000000;
		}
		.send_email_btn {
			box-shadow: 0.3rem 0.3rem 0.7rem #cccccc, -0.3rem -0.3rem 0.7rem #dedede;
			background-color: #000000;
			color: #ffffff;
			border-radius: 2rem;
			height: 1.5rem;
			width: 4rem;
			padding: auto;
			display: flex;
			justify-content: center;
			align-items: center;
			font-size:12px;
		}
		.em_input_area {
		  	display:inline-flex;
		   	flex-direction:column;
		    width:80%;
		    background-color:#ffffff;
		    height:fit-content;
		}
		.em_input_container  {
		    padding-bottom: 0.3rem; border-bottom: 2px solid #cccccc;
		    display:flex; 
		    flex-direction: row;
		    transition: all 0.2s ease-out;
		    width:100%;
		    justify-content: space-between;
		    position:relative;
		    background-color:#ffffff;
		    height:fit-content;
		    margin-bottom:1rem;
		}
		.mail_box{
			margin-left:2rem;
			margin-top:2rem;
		}
		.mail_btn{
		-webkit-appearance: none;
		-moz-appearance: none;
		appearance: none;
		box-shadow: 0.3rem 0.3rem 0.7rem #cccccc, -0.3rem -0.3rem 0.7rem #dedede;
		background-color: #000000;
		color: #ffffff;
		border-radius: 5rem;
		height: 3rem;
		width: 150px;
		padding: auto;
		display: flex;
		justify-content: center;
		align-items: center;
		font-family: 'Pretendard-Regular';
		font-size: 1rem;
		text-align:center;
		margin-left:2rem;
	}
	
	.qu_input_area {
		  	display:inline-flex;
		   	flex-direction:column;
		    width:80%;
		    background-color:#ffffff;
		    height:fit-content;
		}
		.qu_input_container  {
		    padding-bottom: 0.3rem; border-bottom: 2px solid #cccccc;
		    display:flex; 
		    flex-direction: row;
		    transition: all 0.2s ease-out;
		    width:100%;
		    justify-content: space-between;
		    position:relative;
		    background-color:#ffffff;
		    margin-bottom:1rem;
		}
		.question_box{
			margin-left:2rem;
			margin-top:2rem;
		}
		.qu_input{
		    border: none;
		    outline: none;
		    font-size: 1rem;
		    position:relative;
		    font-family: 'Pretendard-Regular';
		    width: 100%;
		    background-color:#ffffff;
		    color:#000000;
		}
		.login_area {
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
		.login_title {
			font-size: 18px;
			margin-bottom: 3rem;
			text-align:center;
			color:#000000;
		}
		.login_btn,.delete_btn{
			-webkit-appearance: none;
			-moz-appearance: none;
			appearance: none;
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
			    
</style>
<body>
	<div class="popup_wrap" id="popup_login">
			<div class="login_area">
				<div class="close"><button class="btn_close" id="btn_close" type="button">X</button></div>
				<div class="admin_title" style="font-size:20px; font-weight:bolder; margin-top:24px; margin-bottom:24px; text-align:center;">mailto 활용/<br>관리자메일주소로 이메일</div>
			</div>
		</div>
</body>
</html>