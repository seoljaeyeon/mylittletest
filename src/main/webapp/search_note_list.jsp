<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./include/head.jsp"></jsp:include>
<style>
	.a {	
	width: 100%;
	height: 100%;
	display: inline-block;
	}	

	.announcement_title {
	width: 50%;
	}
	
	.like_no {
	width: 10%;
	}
	
	a { color: #333; }
	a:visited { color: white; }
	a:hover { color: yellow; }
	a:active { color: red; }	
	a {text-decoration-line: none;}								
</style>
	<div class="a">
	<div><div style="display: inline-block; font-size: 30px; font-weight: bolder; margin-left: 50px;">
		<a href="">내 문제</a>
	</div>
	<div style="display: inline-block; font-size: 30px; font-weight: bolder; margin-left: 50px;" id="search_note">
		<a href="#">조회한 문제</a>
	</div>
		<span style="margin-left: 750px;"><input style="width: 290px; height: 30px;" type="text" placeholder="Search"></span>
	</div>
	<br><br>
	<div id="container">	
		<form>	
			<table style="width: 90%; margin-left: 50px; margin-right: 50px;">
				<tr style="height: 50px">
					<td>문제 분류</td>
					<td class="announcement_title">내용</td>
					<td>시간</td>
					<td class="like_no">❤</td>
					<td>💬</td>
				</tr>
				<tr style="height: 50px">
					<td><a href="">JAVA</a></td>
					<td><a href="">JSP에서 ** 과 비슷하게 생긴 ..</a></td>
					<td>2024-06-24 11:39:45</td>
					<td>16</td>
					<td>16</td>
				</tr>
				<tr style="height: 50px">
					<td><a href="">HTML</a></td>
					<td><a href="">JSP에서 ** 과 비슷하게 생긴 ..</a></td>
					<td>2024-06-24 11:39:45</td>
					<td>2</td>
					<td>2</td>			
				</tr>
				<tr style="height: 50px">
					<td><a href="">JSP</a></td>
					<td><a href="">JSP에서 ** 과 비슷하게 생긴 ..</a></td>
					<td>2024-06-24 11:39:45</td>
					<td>3</td>
					<td>3</td>			
				</tr>
				<tr style="height: 50px">
					<td><a href="">CSS</a></td>
					<td><a href="">JSP에서 ** 과 비슷하게 생긴 ..</a></td>
					<td>2024-06-24 11:39:45</td>
					<td>4</td>
					<td>4</td>
				</tr>
				<tr style="height: 50px">
					<td><a href="">JSTL</a></td>
					<td><a href="">JSP에서 ** 과 비슷하게 생긴 ..</a></td>
					<td>2024-06-24 11:39:45</td>
					<td>5</td>
					<td>5</td>			
				</tr>
				<tr style="height: 50px">
					<td><a href="">EL</a></td>
					<td><a href="">JSP에서 ** 과 비슷하게 생긴 ..</a></td>
					<td>2024-06-24 11:39:45</td>
					<td>6</td>
					<td>6</td>			
				</tr>
				<tr style="height: 50px">
					<td><a href="">Javascript</a></td>
					<td><a href="">JSP에서 ** 과 비슷하게 생긴 ..</a></td>
					<td>2024-06-24 11:39:45</td>
					<td>71</td>
					<td>71</td>					
				</tr>
				<tr style="height: 50px">
					<td><a href="">Jquery</a></td>
					<td><a href="">JSP에서 ** 과 비슷하게 생긴 ..</a></td>
					<td>2024-06-24 11:39:45</td>
					<td>22</td>
					<td>22</td>				
				</tr>	
				<tr style="height: 50px">
					<td><a href="">Jquery</a></td>
					<td><a href="">JSP에서 ** 과 비슷하게 생긴 ..</a></td>
					<td>2024-06-24 11:39:45</td>
					<td>23</td>
					<td>23</td>				
				</tr>
				<tr style="height: 50px">
					<td><a href="">Jquery</a></td>
					<td><a href="">JSP에서 ** 과 비슷하게 생긴 ..</a></td>
					<td>2024-06-24 11:39:45</td>
					<td>23</td>
					<td>23</td>			
				</tr>
				<tr style="height: 50px">
					<td style="text-align:center;" colspan="99">
						◀ 1 2 3 4 5 6 7 8 9 ▶
					</td>
				</tr>																																												
			</table>
		</form>
	</div>		
	</div>
	<script>
		let myNote = document.getElementById("search_note");
		myNote.addEventListener("click",function(){
			let container = document.getElementById("container");
			//container.innerHTML = '';
			let text = "";
			text += '<table style="width: 90%; margin-left: 50px; margin-right: 50px;">'
			text += '	<tr style="height: 50px">'
			text += ' 		<td>문제 분류</td>'
			text +=	'		<td class="announcement_title">내용</td>'
			text += '		<td>시간</td>'
			text += '		<td class="like_no">❤</td>'
			text += '		<td>💬</td>'
			text += '	</tr>'
			text += '	<tr style="height: 50px">'
			text += '		<td><a href="">JAVA</a></td>'
			text += '		<td><a href="">내가 조회한 문제입니다 ..</a></td>'
			text += '		<td>2024-06-24 11:39:45</td>'
			text += '		<td>16</td>'
			text += '		<td>16</td>'
			text += '	</tr>'
			text += '	<tr style="height: 50px">'
			text += '		<td><a href="">HTML</a></td>'
			text += '		<td><a href="">내가 조회한 문제입니다 ..</a></td>'
			text += '		<td>2024-06-24 11:39:45</td>'
			text += '		<td>2</td>'
			text += '		<td>2</td>'
			text += '	</tr>'
			text += '	<tr style="height: 50px">'
			text += '		<td><a href="">JSP</a></td>'
			text += '		<td><a href="">JSP에서 ** 과 비슷하게 생긴 ..</a></td>'
			text += '		<td>2024-06-24 11:39:45</td>'
			text += '		<td>3</td>'
			text += '		<td>3</td>'
			text += '	</tr>'
			text += '	<tr style="height: 50px">'
			text += '		<td><a href="">CSS</a></td>'
			text += '		<td><a href="">JSP에서 ** 과 비슷하게 생긴 ..</a></td>'
			text += '		<td>2024-06-24 11:39:45</td>'
			text += '		<td>4</td>'
			text += '		<td>4</td>'
			text += '	</tr>'
			text += '	<tr style="height: 50px">'
			text += '		<td><a href="">JSTL</a></td>'
			text += '		<td><a href="">JSP에서 ** 과 비슷하게 생긴 ..</a></td>'
			text += '		<td>2024-06-24 11:39:45</td>'
			text += '		<td>5</td>'
			text += '		<td>5</td>'
			text += '	</tr>'
			text += '	<tr style="height: 50px">'
			text += '		<td><a href="">EL</a></td>'
			text += '		<td><a href="">JSP에서 ** 과 비슷하게 생긴 ..</a></td>'
			text += '		<td>2024-06-24 11:39:45</td>'
			text += '		<td>6</td>'
			text += '		<td>6</td>'
			text += '	</tr>'
			text += '	<tr style="height: 50px">'
			text += '		<td><a href="">JSTL</a></td>'
			text += '		<td><a href="">JSP에서 ** 과 비슷하게 생긴 ..</a></td>'
			text += '		<td>2024-06-24 11:39:45</td>'
			text += '		<td>5</td>'
			text += '		<td>5</td>'
			text += '	</tr>'
			text += '	<tr style="height: 50px">'
			text += '		<td><a href="">Javascript</a></td>'
			text += '		<td><a href="">JSP에서 ** 과 비슷하게 생긴 ..</a></td>'
			text += '		<td>2024-06-24 11:39:45</td>'
			text += '		<td>71</td>'
			text += '		<td>71</td>'
			text += '	</tr>'
			text += '	<tr style="height: 50px">'
			text += '		<td><a href="">Jquery</a></td>'
			text += '		<td><a href="">내가 조회한 문제입니다 ..</a></td>'
			text += '		<td>2024-06-24 11:39:45</td>'
			text += '		<td>23</td>'
			text += '		<td>23</td>'
			text += '	</tr>'
			text += '	<tr style="height: 50px">'
			text += '		<td><a href="">Jquery</a></td>'
			text += '		<td><a href="">내가 조회한 문제입니다 ..</a></td>'
			text += '		<td>2024-06-24 11:39:45</td>'
			text += '		<td>23</td>'
			text += '		<td>23</td>'
			text += '	</tr>'
			text += '	<tr style="height: 50px">'
			text += '		<td style="text-align:center;" colspan="99">'
			text += '				◀ 1 2 3 4 5 6 7 8 9 ▶'
			text += '		</td>'
			text += '	</tr>'																																												
			text += '</table>'
			container.innerHTML = text
		})
	</script>	
<jsp:include page="./include/tail.jsp"></jsp:include>	