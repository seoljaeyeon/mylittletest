<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./include/head.jsp"></jsp:include>
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
	a:hover { color: yellow; }
	a:active { color: red; }								
</style>
<script>
function checkAll() {
    $("input[type='checkbox']").prop("checked", true);
}
</script>
	<div class="a">
	<div>&nbsp&nbsp<span style="font-size: 30px; font-weight: bolder;">공지사항</span>
	&nbsp&nbsp&nbsp&nbsp
	<span style="margin-left: 650px;">
		<a href="announcement_list.jsp"><button type="button" style="height: 35px; border-radius: 10px;">돌아가기</button></a></span>
	<span style="margin-left: 30px;"><input style="width: 600px; height: 30px;" type="text" placeholder="Search"></span>
	</div>
	<div style="margin-top: 20px; display: inline-block;"><input style="width: 110px; height: 35px; border-radius: 10px;" type="button" onclick="checkAll()" value="전체 선택"></div>
	<span><button type="button" style="width: 110px; height: 35px; border-radius: 10px;">예약 해제</button></span>
	<br><br>
		<table style="width: 100%; margin-left: auto; margin-right: auto;">
			<tr style="height: 50px">
				<td class="announcement_title" style="font-weight: bolder;">✔ 제목</td>
				<td  style="font-weight: bolder;" colspan="2">예약 시간</td>
			</tr>
			<tr style="height: 50px">
				<td><label><input type="checkbox"></label><a href="announcement_modify.jsp" target="_blank">공지사항 입니다. 다들 집중하세요.</a></td>
				<td><button type="button">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px">
				<td><label><input type="checkbox"></label><a href="announcement_modify.jsp">공지사항 입니다. 다들 집중하세요.</a></td>
				<td><button type="button">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px">
				<td><label><input type="checkbox"></label><a href="announcement_modify.jsp">공지사항 입니다. 다들 집중하세요.</a></td>
				<td><button type="button">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px">
				<td><label><input type="checkbox"></label><a href="announcement_modify.jsp">공지사항 입니다. 다들 집중하세요.</a></td>
				<td><button type="button">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px">
				<td><label><input type="checkbox"></label><a href="announcement_modify.jsp">공지사항 입니다. 다들 집중하세요.</a></td>
				<td><button type="button">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px">
				<td><label><input type="checkbox"></label><a href="announcement_modify.jsp">공지사항 입니다. 다들 집중하세요.</a></td>
				<td><button type="button">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px">
				<td><label><input type="checkbox"></label><a href="announcement_modify.jsp">공지사항 입니다. 다들 집중하세요.</a></td>
				<td><button type="button">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px">
				<td><label><input type="checkbox"></label><a href="announcement_modify.jsp">공지사항 입니다. 다들 집중하세요.</a></td>
				<td><button type="button">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>	
			<tr style="height: 50px">
				<td><label><input type="checkbox"></label><a href="announcement_modify.jsp">공지사항 입니다. 다들 집중하세요.</a></td>
				<td><button type="button">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px">
				<td><label><input type="checkbox"></label><a href="announcement_modify.jsp">공지사항 입니다. 다들 집중하세요.</a></td>
				<td><button type="button">예약 해제</button></td>
				<td>2024-06-24 11:39:45</td>
			</tr>
			<tr style="height: 50px">
				<td style="text-align:center;" colspan="99">
					◀ 1 2 3 4 5 6 7 8 9 ▶
				</td>
			</tr>																																												
		</table>
	</div>
<jsp:include page="./include/tail.jsp"></jsp:include>	