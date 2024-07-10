<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./include/head_login.jsp"></jsp:include>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<style>
	.a {	
	width: 100%;
	height: 660px;
	display: inline-block;
	}
	
	.announcement_title {
	width: 80%;
	}
		
	a { color: #333; }
	a:visited { color: white; }
	a:hover { color: white; }
	a:active { color: white; }
	a {text-decoration-line: none;}									
</style>

<script>
	let allChecked = false;
	
	function toggleCheck() {
	    if (allChecked) {
	        $("input[type='checkbox']").prop("checked", false);
	        $("#toggleButton").val("전체 선택");
	    } else {
	        $("input[type='checkbox']").prop("checked", true);
	        $("#toggleButton").val("전체 해제");
	    }
	    allChecked = !allChecked;
	}
</script>

	<div class="a">
	<div>&nbsp&nbsp<span style="font-size: 30px; font-weight: bolder;">공지사항</span>
	&nbsp&nbsp&nbsp&nbsp
	<span style="margin-left: 650px;">
		<a href="announcement_list.jsp">
			<button type="button" style="height: 40px; width: 95px; border-radius: 17px; background-color: #333333; color: white;">돌아가기</button>
		</a>
	</span>
	<div style="display: inline-block;">
		<form style="width: 565px; background: #333333; padding: 10px; display: block; border-radius: 17px; margin-left: 50px;">
			<input type="text" style="width: 510px; height: 25px; font-size:16px; background-color: #333333; border: none; outline: none; border-radius: 17px; color: white;" type="text" placeholder="Search" spellcheck="false">
			<button style="align-items: center; border: none; background: #333333; border-radius: 17px; font-size:16px;">🔍</button>
		</form>			
	</div>
	</div>
	<div style="margin-top: 20px; display: inline-block;">
		<input id="toggleButton" style="width: 110px; height: 35px; border-radius: 17px; background-color: #333333; color: white;" type="button" onclick="toggleCheck()" value="전체 선택">
	</div>
	<span><button type="button" style="width: 110px; height: 35px; border-radius: 17px; background-color: #333333; color: white;">예약 해제</button></span>
	<br><br>
		<table style="width: 100%; margin-left: auto; margin-right: auto;">
			<tr style="height: 50px;">
				<td class="announcement_title" style="font-weight: bolder;">✔ &nbsp 제목</td>
				<td style="font-weight: bolder;" colspan="2">예약 시간</td>
			</tr>
			<tr style="height: 50px;">
				<td>
					<label><input type="checkbox" style="accent-color: green;"></label>
					<a href="announcement_modify.jsp">&nbsp 공지사항 입니다. 다들 집중하세요.</a>
				</td>
				<td><button type="button" style="height: 30px; border-radius: 10px; background-color: transparent; color: white;">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px;">
				<td>
					<label><input type="checkbox" style="accent-color: green;"></label>
					<a href="announcement_modify.jsp">&nbsp 공지사항 입니다. 다들 집중하세요.</a>
				</td>
				<td><button type="button" style="height: 30px; border-radius: 10px; background-color: transparent; color: white;">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px;">
				<td>
					<label><input type="checkbox" style="accent-color: green;"></label>
					<a href="announcement_modify.jsp">&nbsp 공지사항 입니다. 다들 집중하세요.</a>
				</td>
				<td><button type="button" style="height: 30px; border-radius: 10px; background-color: transparent; color: white;">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px;">
				<td>
					<label><input type="checkbox" style="accent-color: green;"></label>
					<a href="announcement_modify.jsp">&nbsp 공지사항 입니다. 다들 집중하세요.</a>
				</td>
				<td><button type="button" style="height: 30px; border-radius: 10px; background-color: transparent; color: white;">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px;">
				<td>
					<label><input type="checkbox" style="accent-color: green;"></label>
					<a href="announcement_modify.jsp">&nbsp 공지사항 입니다. 다들 집중하세요.</a>
				</td>
				<td><button type="button" style="height: 30px; border-radius: 10px; background-color: transparent; color: white;">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px;">
				<td>
					<label><input type="checkbox" style="accent-color: green;"></label>
					<a href="announcement_modify.jsp">&nbsp 공지사항 입니다. 다들 집중하세요.</a>
				</td>
				<td><button type="button" style="height: 30px; border-radius: 10px; background-color: transparent; color: white;">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px;">
				<td>
					<label><input type="checkbox" style="accent-color: green;"></label>
					<a href="announcement_modify.jsp">&nbsp 공지사항 입니다. 다들 집중하세요.</a>
				</td>
				<td><button type="button" style="height: 30px; border-radius: 10px; background-color: transparent; color: white;">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px;">
				<td>
					<label><input type="checkbox" style="accent-color: green;"></label>
					<a href="announcement_modify.jsp">&nbsp 공지사항 입니다. 다들 집중하세요.</a>
				</td>
				<td><button type="button" style="height: 30px; border-radius: 10px; background-color: transparent; color: white;">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>	
			<tr style="height: 50px;">
				<td>
					<label><input type="checkbox" style="accent-color: green;"></label>
					<a href="announcement_modify.jsp">&nbsp 공지사항 입니다. 다들 집중하세요.</a>
				</td>
				<td><button type="button" style="height: 30px; border-radius: 10px; background-color: transparent; color: white;">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px;">
				<td>
					<label><input type="checkbox" style="accent-color: green;"></label>
					<a href="announcement_modify.jsp">&nbsp 공지사항 입니다. 다들 집중하세요.</a>
				</td>
				<td><button type="button" style="height: 30px; border-radius: 10px; background-color: transparent; color: white;">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px;">
				<td style="text-align:center;" colspan="99">
					◀ 1 2 3 4 5 6 7 8 9 ▶
				</td>
			</tr>																																												
		</table>
	</div>
<jsp:include page="./include/tail.jsp"></jsp:include>	