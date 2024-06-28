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
		.report_area {
			background-color: #ffffff;
			width: 300px;
			max-width: 40rem;
			position: absolute;
			top: 50%;
			left: 50%;
			transform: translate(-50%, -50%);
			padding: 1rem;
			border-radius: 1rem;
			box-shadow: 0 0 1rem rgba(0, 0, 0, 0.1);
		}
		.report_list{
			display:inline-flex;
			margin-bottom:0.5rem;
		}
		.report_note{
		 	display:inline-flex;
			margin-bottom:0.5rem;
		}
		

	.report_btn{
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
	#reportnote{
		width:220px;
		resize:vertical;
		height:122px;
		font-size: 15px;
		border-radius:5px;
	    background-color:#ffffff;
	    color:#000000;
	}   
			    
</style>
<body>
	<div class="popup_wrap" id="popup_qucomplete">
		<div class="report_area">
			<h1 class="report_title">신고하기</h1>
			<div class="report_list">
				<span style="font-weight:bold;">신고분류</span>
				<div class=report_choice style="margin-left:8px;">
					<select id="reportlist" class="reportlist">
							<option value="1">욕설/반말/부적절한 언어</option>
							<option value="2">저작권 침해</option>
							<option value="3">도배성 게시글</option>
							<option value="4">광고성 게시물</option>
							<option value="5">회원 비방</option>
						</select>
				</div>
			</div>
			<div class="report_note">
				<span style="font-weight:bold; font-size:15px;">신고내용</span>
				<div class=report_box style="margin-left:0.8rem"><textarea id="reportnote"></textarea></div>
			</div>
			<div class="reportbtn" style="display:inline-flex; flex-direction:row; gap:2rem; ">
	            <div class="report_btn" id="findid">신고</div>
	            <div class="report_btn" id="findpw" style="background-color:#ffffff;color:black; ">취소</div>
	        </div>
		</div>
	</div>
</body>
</html>