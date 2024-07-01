<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./include/head.jsp"></jsp:include>
<style>
	.a {	
	width: 1100px;
	height: 660px;
	display: inline-block;
	}
	
	.b {
	margin-left: 50px;
	margin-right: 50px;	
	margin-top: 20px;
	}
	
	table {
   		margin-left: 50px; 
	    margin-right: 50px;
	}
	td { text-align: center; }
	tr { height: 30px; }							
</style>
	<div class="a">
	<div class="b">&nbsp&nbsp<span style="font-size: 25px; font-weight: bolder;">관리자 메뉴 - 대시보드</span>
	<span><input style="width: 230px;" type="text" placeholder="Search"></span>
	</div>
	<br><br>
		<table style="width: 1400px;">
			<tr>
				<td>작업 번호</td>
				<td>작업 분류</td>
				<td>내용 요약</td>
				<td>처리방법</td>
				<td>처리버튼</td>
				<td>민원 날짜</td>
				<td>처리 날짜</td>
				<td>담당자</td>
				<td>처리자</td>
			</tr>
			<tr>
				<td>10</td>
				<td>댓글 신고</td>
				<td style="text-align: left;">이 강아지같은 놈들 다 죽어라</td>
				<td>
					<select>
						<option selected>미응답</option>
						<option>비활성화</option>
						<option>반려</option>
					</select>
				</td>
				<td><button type="button">처리</button></td>
				<td>24/06/27 11:04:30</td>
				<td>-</td>
				<td>담당자이름</td>
				<td>처리자이름</td>
			</tr>
			<tr>
				<td>9</td>
				<td>문제 신고</td>
				<td style="text-align: left;">이 강아지같은 놈들 다 죽어라</td>
				<td>
					<select>
						<option selected>미응답</option>
						<option>비활성화</option>
						<option>반려</option>
					</select>
				</td>
				<td><button type="button">처리</button></td>
				<td>24/06/27 11:04:30</td>
				<td>-</td>
				<td>담당자이름</td>
				<td>처리자이름</td>				
			</tr>
			<tr>
				<td>8</td>
				<td>문제 신고</td>
				<td style="text-align: left;">강성욱의 허리 사이즈</td>
				<td>
					<select>
						<option selected>미응답</option>
						<option>비활성화</option>
						<option>반려</option>
					</select>
				</td>
				<td><button type="button">처리</button></td>
				<td>24/06/27 11:04:30</td>
				<td>-</td>
				<td>담당자이름</td>
				<td>처리자이름</td>				
			</tr>
			<tr>
				<td>7</td>
				<td>문제 신고</td>
				<td style="text-align: left;">혹시 저희 저작권 음성 파일이 올라..</td>
				<td>
					<select>
						<option selected>미응답</option>
						<option>비활성화</option>
						<option>반려</option>
					</select>
				</td>
				<td><button type="button">처리</button></td>
				<td>24/06/27 11:04:30</td>
				<td>-</td>
				<td>담당자이름</td>
				<td>처리자이름</td>
			</tr>
			<tr>
				<td>6</td>
				<td>댓글 신고</td>
				<td style="text-align: left;">혹시 제휴 가능할까요?</td>
				<td>
					<select>
						<option selected>미응답</option>
						<option>비활성화</option>
						<option>반려</option>
					</select>
				</td>
				<td><button type="button">처리</button></td>
				<td>24/06/27 11:04:30</td>
				<td>-</td>
				<td>담당자이름</td>
				<td>처리자이름</td>				
			</tr>
			<tr>
				<td>5</td>
				<td>댓글 신고</td>
				<td style="text-align: left;">뒷통수 조심해라 밤길 어둡다</td>
				<td>
					<select>
						<option selected>미응답</option>
						<option>비활성화</option>
						<option>반려</option>
					</select>
				</td>
				<td><button type="button">처리</button></td>
				<td>24/06/27 11:04:30</td>
				<td>-</td>
				<td>담당자이름</td>
				<td>처리자이름</td>				
			</tr>
			<tr>
				<td>4</td>
				<td>문제 신고</td>
				<td style="text-align: left;">이딴 문제를 쳐 올리고 앉아있네</td>
				<td>
					<select>
						<option selected>미응답</option>
						<option>비활성화</option>
						<option>반려</option>
					</select>
				</td>
				<td><button type="button">처리</button></td>
				<td>24/06/27 11:04:30</td>
				<td>-</td>
				<td>담당자이름</td>
				<td>처리자이름</td>				
			</tr>
			<tr>
				<td>3</td>
				<td>댓글 신고</td>
				<td style="text-align: left;">빅뱅 승리</td>
				<td>
					<select>
						<option selected>미응답</option>
						<option>비활성화</option>
						<option>반려</option>
					</select>
				</td>
				<td><button type="button">처리</button></td>
				<td>24/06/27 11:04:30</td>
				<td>-</td>
				<td>담당자이름</td>
				<td>처리자이름</td>				
			</tr>	
			<tr>
				<td>2</td>
				<td>문제 신고</td>
				<td style="text-align: left;">대한민국 제일의 멍청이</td>
				<td>
					<select>
						<option selected>미응답</option>
						<option>비활성화</option>
						<option>반려</option>
					</select>
				</td>
				<td><button type="button">처리</button></td>
				<td>24/06/27 11:04:30</td>
				<td>-</td>
				<td>담당자이름</td>
				<td>처리자이름</td>				
			</tr>
			<tr>
				<td>1</td>
				<td>댓글 신고</td>
				<td style="text-align: left;">번호좀 알려주세요 ㅎ</td>
				<td>
					<select>
						<option selected>미응답</option>
						<option>비활성화</option>
						<option>반려</option>
					</select>
				</td>
				<td><button type="button">처리</button></td>
				<td>24/06/27 11:04:30</td>
				<td>-</td>
				<td>담당자이름</td>
				<td>처리자이름</td>				
			</tr>
			<tr>
				<td style="text-align:center;" colspan="99">
					◀ 1 2 3 4 5 6 7 8 9 ▶
				</td>
			</tr>																																												
		</table>
	</div>
<jsp:include page="./include/tail.jsp"></jsp:include>	