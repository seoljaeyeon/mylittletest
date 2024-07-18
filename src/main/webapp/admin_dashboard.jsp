<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./include/head_login.jsp"></jsp:include>
<style>
	.a {	
	width: 100%;
	height: 100%;
	display: inline-block;
	}	

	td { text-align: center; }
	
	a { color: #333; }
	a:visited { color: white; }
	a:hover { color: white; }
	a:active { color: white; }
	
	a {text-decoration-line: none;}								
</style>

	<div class="a">
	<div>&nbsp&nbsp<span style="font-size: 30px; font-weight: bolder; margin-left: 50px;">관리자 메뉴 - 대시보드</span>
	<div style="display: inline-block;">
		<form style="width: 565px; background: #333333; padding: 10px; display: block; border-radius: 17px; margin-left: 500px;">
			<input type="text" style="width: 510px; height: 25px; font-size:16px; background-color: #333333; border: none; outline: none; border-radius: 17px; color: white;" type="text" placeholder="Search" spellcheck="false">
			<button style="align-items: center; border: none; background: #333333; border-radius: 17px; font-size:16px;">🔍</button>
		</form>			
	</div>
	</div>
	<br><br>
	<div>		
		<table style="width: 90%; margin-left: 50px; margin-right: 50px;">
			<tr style="height: 50px">
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
			<tr style="height: 50px;">
				<td>10</td>
				<td>댓글 신고</td>
				<td style="text-align: left;"><a href="#">이 강아지같은 놈들 다 죽어라</a></td>
				<td>
					<select id="mySelect" onchange="checkSelection()" style="height: 30px; width: 85px; border-radius: 10px; text-align: center; background-color: transparent; color: white;">
						<option value="1" selected style="color: black;">미응답</option>
						<option value="2" style="color: black;">비활성화</option>
						<option value="3" style="color: black;">반려</option>
					</select>
				</td>
				<td><input type="button" value="처리" id="mybutton" style="height: 30px; width: 65px; border-radius: 10px; text-align: center; background-color: transparent; color: white;"></td>
				<td>24/06/27 11:04:30</td>
				<td>-</td>
				<td><div style="display: inline-flex; align-items: center;"><img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" style="width: auto; height: 30px; border-radius: 30px; display: inline-flex; align-items: center;">&nbsp담당자이름</div></td>
				<td></td>
			</tr>
			<tr style="height: 50px;">
				<td>9</td>
				<td>문제 신고</td>
				<td style="text-align: left;"><a href="#">이 강아지같은 놈들 다 죽어라</a></td>
				<td>
					<select style="height: 30px; width: 85px; border-radius: 10px; text-align: center; background-color: transparent; color: white;">
						<option selected style="color: black;">미응답</option>
						<option style="color: black;">비활성화</option>
						<option style="color: black;">반려</option>
					</select>
				</td>
				<td><input type="button" value="처리" id="mybutton" style="height: 30px; width: 65px; border-radius: 10px; text-align: center; background-color: transparent; color: white;"></td>
				<td>24/06/27 11:04:30</td>
				<td>-</td>
				<td><div style="display: inline-flex; align-items: center;"><img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" style="width: auto; height: 30px; border-radius: 30px; display: inline-flex; align-items: center;">&nbsp담당자이름</div></td>
				<td></td>				
			</tr>
			<tr style="height: 50px;">
				<td>8</td>
				<td>문제 신고</td>
				<td style="text-align: left;"><a href="#">강성욱의 허리 사이즈</a></td>
				<td>
					<select style="height: 30px; width: 85px; border-radius: 10px; text-align: center; background-color: transparent; color: white;">
						<option selected style="color: black;">미응답</option>
						<option style="color: black;">비활성화</option>
						<option style="color: black;">반려</option>
					</select>
				</td>
				<td><input type="button" value="처리" id="mybutton" style="height: 30px; width: 65px; border-radius: 10px; text-align: center; background-color: transparent; color: white;"></td>
				<td>24/06/27 11:04:30</td>
				<td>24/06/27 11:04:30</td>
				<td><div style="display: inline-flex; align-items: center;"><img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" style="width: auto; height: 30px; border-radius: 30px; display: inline-flex; align-items: center;">&nbsp담당자이름</div></td>
				<td><div style="display: inline-flex; align-items: center;"><img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" style="width: auto; height: 30px; border-radius: 30px; display: inline-flex; align-items: center;">&nbsp처리자이름</div></td>				
			</tr>
			<tr style="height: 50px;">
				<td>7</td>
				<td>문제 신고</td>
				<td style="text-align: left;"><a href="#">혹시 저희 저작권 음성 파일이 올라..</a></td>
				<td>
					<select style="height: 30px; width: 85px; border-radius: 10px; text-align: center; background-color: transparent; color: white;">
						<option selected style="color: black;">미응답</option>
						<option style="color: black;">비활성화</option>
						<option style="color: black;">반려</option>
					</select>
				</td>
				<td><input type="button" value="처리" id="mybutton" style="height: 30px; width: 65px; border-radius: 10px; text-align: center; background-color: transparent; color: white;"></td>
				<td>24/06/27 11:04:30</td>
				<td>-</td>
				<td><div style="display: inline-flex; align-items: center;"><img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" style="width: auto; height: 30px; border-radius: 30px; display: inline-flex; align-items: center;">&nbsp담당자이름</div></td>
				<td></td>
			</tr>
			<tr style="height: 50px;">
				<td>6</td>
				<td>댓글 신고</td>
				<td style="text-align: left;"><a href="#">혹시 제휴 가능할까요?</a></td>
				<td>
					<select style="height: 30px; width: 85px; border-radius: 10px; text-align: center; background-color: transparent; color: white;">
						<option selected style="color: black;">미응답</option>
						<option style="color: black;">비활성화</option>
						<option style="color: black;">반려</option>
					</select>
				</td>
				<td><input type="button" value="처리" id="mybutton" style="height: 30px; width: 65px; border-radius: 10px; text-align: center; background-color: transparent; color: white;"></td>
				<td>24/06/27 11:04:30</td>
				<td>24/06/27 11:04:30</td>
				<td><div style="display: inline-flex; align-items: center;"><img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" style="width: auto; height: 30px; border-radius: 30px; display: inline-flex; align-items: center;">&nbsp담당자이름</div></td>
				<td><div style="display: inline-flex; align-items: center;"><img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" style="width: auto; height: 30px; border-radius: 30px; display: inline-flex; align-items: center;">&nbsp처리자이름</div></td>				
			</tr>
			<tr style="height: 50px;">
				<td>5</td>
				<td>댓글 신고</td>
				<td style="text-align: left;"><a href="#">뒷통수 조심해라 밤길 어둡다</a></td>
				<td>
					<select style="height: 30px; width: 85px; border-radius: 10px; text-align: center; background-color: transparent; color: white;">
						<option selected style="color: black;">미응답</option>
						<option style="color: black;">비활성화</option>
						<option style="color: black;">반려</option>
					</select>
				</td>
				<td><input type="button" value="처리" id="mybutton" style="height: 30px; width: 65px; border-radius: 10px; text-align: center; background-color: transparent; color: white;"></td>
				<td>24/06/27 11:04:30</td>
				<td>24/06/27 11:04:30</td>
				<td><div style="display: inline-flex; align-items: center;"><img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" style="width: auto; height: 30px; border-radius: 30px; display: inline-flex; align-items: center;">&nbsp담당자이름</div></td>
				<td><div style="display: inline-flex; align-items: center;"><img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" style="width: auto; height: 30px; border-radius: 30px; display: inline-flex; align-items: center;">&nbsp처리자이름</div></td>				
			</tr>
			<tr style="height: 50px;">
				<td>4</td>
				<td>문제 신고</td>
				<td style="text-align: left;"><a href="#">이딴 문제를 쳐 올리고 앉아있네</a></td>
				<td>
					<select style="height: 30px; width: 85px; border-radius: 10px; text-align: center; background-color: transparent; color: white;">
						<option selected style="color: black;">미응답</option>
						<option style="color: black;">비활성화</option>
						<option style="color: black;">반려</option>
					</select>
				</td>
				<td><input type="button" value="처리" id="mybutton" style="height: 30px; width: 65px; border-radius: 10px; text-align: center; background-color: transparent; color: white;"></td>
				<td>24/06/27 11:04:30</td>
				<td>24/06/27 11:04:30</td>
				<td><div style="display: inline-flex; align-items: center;"><img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" style="width: auto; height: 30px; border-radius: 30px; display: inline-flex; align-items: center;">&nbsp담당자이름</div></td>
				<td><div style="display: inline-flex; align-items: center;"><img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" style="width: auto; height: 30px; border-radius: 30px; display: inline-flex; align-items: center;">&nbsp처리자이름</div></td>				
			</tr>
			<tr style="height: 50px;">
				<td>3</td>
				<td>댓글 신고</td>
				<td style="text-align: left;"><a href="#">빅뱅 승리</a></td>
				<td>
					<select style="height: 30px; width: 85px; border-radius: 10px; text-align: center; background-color: transparent; color: white;">
						<option selected style="color: black;">미응답</option>
						<option style="color: black;">비활성화</option>
						<option style="color: black;">반려</option>
					</select>
				</td>
				<td><input type="button" value="처리" id="mybutton" style="height: 30px; width: 65px; border-radius: 10px; text-align: center; background-color: transparent; color: white;"></td>
				<td>24/06/27 11:04:30</td>
				<td>-</td>
				<td><div style="display: inline-flex; align-items: center;"><img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" style="width: auto; height: 30px; border-radius: 30px; display: inline-flex; align-items: center;">&nbsp담당자이름</div></td>
				<td></td>				
			</tr>	
			<tr style="height: 50px;">
				<td>2</td>
				<td>문제 신고</td>
				<td style="text-align: left;"><a href="#">대한민국 제일의 멍청이</a></td>
				<td>
					<select style="height: 30px; width: 85px; border-radius: 10px; text-align: center; background-color: transparent; color: white;">
						<option selected style="color: black;">미응답</option>
						<option style="color: black;">비활성화</option>
						<option style="color: black;">반려</option>
					</select>
				</td>
				<td><input type="button" value="처리" id="mybutton" style="height: 30px; width: 65px; border-radius: 10px; text-align: center; background-color: transparent; color: white;"></td>
				<td>24/06/27 11:04:30</td>
				<td>24/06/27 11:04:30</td>
				<td><div style="display: inline-flex; align-items: center;"><img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" style="width: auto; height: 30px; border-radius: 30px; display: inline-flex; align-items: center;">&nbsp담당자이름</div></td>
				<td><div style="display: inline-flex; align-items: center;"><img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" style="width: auto; height: 30px; border-radius: 30px; display: inline-flex; align-items: center;">&nbsp처리자이름</div></td>				
			</tr>
			<tr style="height: 50px;">
				<td>1</td>
				<td>댓글 신고</td>
				<td style="text-align: left;"><a href="#">번호좀 알려주세요 ㅎ</a></td>
				<td>
					<select style="height: 30px; width: 85px; border-radius: 10px; text-align: center; background-color: transparent; color: white;">
						<option selected style="color: black;">미응답</option>
						<option style="color: black;">비활성화</option>
						<option style="color: black;">반려</option>
					</select>
				</td>
				<td><input type="button" value="처리" id="mybutton" style="height: 30px; width: 65px; border-radius: 10px; text-align: center; background-color: transparent; color: white;"></td>
				<td>24/06/27 11:04:30</td>
				<td>-</td>
				<td><div style="display: inline-flex; align-items: center;"><img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" style="width: auto; height: 30px; border-radius: 30px; display: inline-flex; align-items: center;">&nbsp담당자이름</div></td>
				<td></td>				
			</tr>
			<tr style="height: 50px;">
				<td style="text-align:center;" colspan="99">
					◀ 1 2 3 4 5 6 7 8 9 ▶
				</td>
			</tr>																																												
		</table>
	</div>		
	</div>
<jsp:include page="./include/tail.jsp"></jsp:include>	