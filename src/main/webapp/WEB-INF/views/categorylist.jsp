<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
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
	a:hover { color: white; }
	a:active { color: white; }	
	a {text-decoration-line: none;}								
</style>
	<div class="a">
	<div><div style="display: inline-block; font-size: 30px; font-weight: bolder; margin-left: 50px;">
		<a href="javascript:history.back();" style="border: dashed;">${ CategoryVO.categorytitle }</a>
	</div>	
	<div align="right" style="display: inline-block;">
		<form style="width: 565px; background: #333333; padding: 10px; display: block; border-radius: 17px; margin-left: 710px;">
			<input type="text" style="width: 510px; height: 25px; font-size:16px; background-color: #333333; border: none; outline: none; border-radius: 17px; color: white;" type="text" placeholder="Search" spellcheck="false">
			<button style="align-items: center; border: none; background: #333333; border-radius: 17px; font-size:16px;">🔍</button>
		</form>			
	</div>
	</div>
	<br><br>
	<div>	
		<form>	
			<table style="width: 90%; margin-left: 50px; margin-right: 50px;">
				<tr style="height: 50px;">
					<td>문제 분류</td>
					<td class="announcement_title">내용</td>
					<td>시간</td>
					<td class="like_no">❤</td>
					<td>💌</td>
				</tr>
				<c:forEach var="categorylist" items="categorylist">
					<tr style="height: 50px;">
						<td><a href="questionlist.jsp">${categorylist.title }</a></td>
						<td><a href="questionsolve.jsp">${categorylist.note }</a></td>
						<td>${categorylist.date }</td>
						<td>${categorylist.like }</td>
						<td><input type="checkbox" checked style="accent-color: green;"></td>
					</tr>
				</c:forEach>
				<tr style="height: 50px;">
					<td style="text-align:center;" colspan="99">
					 <c:if test="${startBK > 10 }">
						<a href="index.do?page=${startBK - 1}">◀</a>
					 </c:if>
					 <c:forEach var="page" begin="${startBK}" end="${endBK}">
						<c:if test="${ page == pageno }">
							<a style="color:red; font-weight:bold;" href="index.do?page=${ page }">${ page }</a>
						</c:if>
						<c:if test="${ page != pageno }">
							<a href="index.do?page=${ page }">${ page }</a>
						</c:if>			
					 </c:forEach>
					 <c:if test="${endBK <  maxpage }">
						<a href="index.do?page=${endBK + 1}">▶</a>
					 </c:if>
					</td>
				</tr>																																												
			</table>
		</form>
	</div>		
	</div>	
<jsp:include page="./include/tail.jsp"></jsp:include>	