<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<jsp:include page="./include/head.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
    	        // "내 문제" 클릭 시 처리
    	        $('#my_note').click(function(event) {
    	            event.preventDefault(); // 기본 동작을 막음
    	            $('#my_note').addClass('active');
    	            $('#searchNote').removeClass('active');
    	            $('#my_note_section').addClass('active');
    	            $('#search_note_section').removeClass('active');
    	        });

    	        // "조회한 문제" 클릭 시 처리
    	        $('#searchNote').click(function(event) {
    	            event.preventDefault(); // 기본 동작을 막음
    	            $('#searchNote').addClass('active');
    	            $('#my_note').removeClass('active');
    	            $('#search_note_section').addClass('active');
    	            $('#my_note_section').removeClass('active');
    	        });
    	    });
</script>
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
    a { color: white; }
    a:visited { color: white; }
    a:hover { color: white; }
    a:active{color:white;}
    a {text-decoration-line: none;} 
    .notelist {
        color: black;
        cursor: pointer;
    }
    .notelist.active {
        color: white; 
    }
    .notelist:hover {
        color: white; 
    }
    .search-bar {
        width: 565px;
        background: #333333;
        padding: 10px;
        display: block;
        border-radius: 17px;
        margin-left: 495px;
    }
    .search-bar input[type="text"] {
        width: 510px;
        height: 25px;
        font-size: 16px;
        background-color: #333333;
        border: none;
        outline: none;
        border-radius: 17px;
        color: white;
    }
    .search-bar button {
        align-items: center;
        border: none;
        background: #333333;
        border-radius: 17px;
        font-size: 16px;
    }
     .content-section {
        display: none;
    }
    .content-section.active {
        display: block;
    }
</style>
<div class="a">
    <div>
        <div class="notelist active" style="display: inline-block; font-size: 30px; font-weight: bolder; margin-left: 50px;" id="my_note">
            내 문제
        </div>
        <div class="notelist" style="display: inline-block; font-size: 30px; font-weight: bolder; margin-left: 50px;" id="searchNote">
            조회한 문제
        </div>
        <div align="right" style="display: inline-block;">
            <form class="search-bar">
                <input type="text" placeholder="Search" spellcheck="false">
                <button>🔍</button>
            </form>            
        </div>
    </div>
    <br><br>
    <div id="container">
        <!-- 내 문제 섹션 -->
        <div id="my_note_section" class="content-section active">
            <table style="width: 90%; margin-left: 50px; margin-right: 50px;">
                <tr style="height: 50px">
                    <td>문제 분류</td>
                    <td class="announcement_title">내용</td>
                    <td>시간</td>
                    <td class="like_no">❤</td>
                    <td>💬</td>
                </tr>
                <c:forEach var="myquestion" items="myquestion">
	                <tr style="height: 50px">
	                    <td><a href="questionlist.jsp">${ myquestion.category }</a></td>
	                    <td><a href="announcement_view.jsp">${ myquestion.note }</a></td>
	                    <td>${ myquestion.date }</td>
	                    <td>${ myquestion.likecount }</td>
	                    <td>${ myquestion.replycount }</td>
	                </tr>
	            </c:forEach>
                <tr style="height: 50px">
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
        </div>
        <!-- 조회한 문제 섹션 -->
        <div id="search_note_section" class="content-section">
            <table style="width: 90%; margin-left: 50px; margin-right: 50px;">
                <tr style="height: 50px">
                    <td>문제 분류</td>
                    <td class="announcement_title">내용</td>
                    <td>시간</td>
                    <td class="like_no">❤</td>
                    <td>💬</td>
                </tr>
                <c:forEach var="showquestion" items="showquestion">
	               <tr style="height: 50px">
	                   <td><a href="questionlist.jsp">${showquestion.category}</a></td>
	                   <td><a href="index.jsp">${showquestion.note}</a></td>
	                   <td>${showquestion.date}</td>
	                   <td>${showquestion.likecount}</td>
	                   <td>${showquestion.replycount}</td>
	               </tr>
	            </c:forEach>
                <tr style="height: 50px">
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
        </div>
    </div>
</div>
<jsp:include page="./include/tail.jsp"></jsp:include>


