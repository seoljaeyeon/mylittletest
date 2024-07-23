<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./include/head_login.jsp"></jsp:include>
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
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">JAVA</a></td>
                    <td><a href="announcement_view.jsp">내 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>16</td>
                    <td>16</td>
                </tr>
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">HTML</a></td>
                    <td><a href="announcement_view.jsp">내 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>2</td>
                    <td>2</td>            
                </tr>
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">JSP</a></td>
                    <td><a href="announcement_view.jsp">내 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>3</td>
                    <td>3</td>            
                </tr>
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">CSS</a></td>
                    <td><a href="announcement_view.jsp">내 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>4</td>
                    <td>4</td>
                </tr>
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">JSTL</a></td>
                    <td><a href="announcement_view.jsp">내 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>5</td>
                    <td>5</td>            
                </tr>
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">EL</a></td>
                    <td><a href="announcement_view.jsp">내 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>6</td>
                    <td>6</td>            
                </tr>
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">Javascript</a></td>
                    <td><a href="announcement_view.jsp">내 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>71</td>
                    <td>71</td>                    
                </tr>
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">Jquery</a></td>
                    <td><a href="announcement_view.jsp">내 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>22</td>
                    <td>22</td>                
                </tr>    
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">Jquery</a></td>
                    <td><a href="announcement_view.jsp">내 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>23</td>
                    <td>23</td>                
                </tr>
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">Jquery</a></td>
                    <td><a href="announcement_view.jsp">내 문제입니다 ..</a></td>
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
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">JAVA</a></td>
                    <td><a href="index.jsp">내가 조회한 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>16</td>
                    <td>16</td>
                </tr>
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">HTML</a></td>
                    <td><a href="index.jsp">내가 조회한 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>2</td>
                    <td>2</td>            
                </tr>
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">JSP</a></td>
                    <td><a href="index.jsp">내가 조회한 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>3</td>
                    <td>3</td>            
                </tr>
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">CSS</a></td>
                    <td><a href="index.jsp">내가 조회한 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>4</td>
                    <td>4</td>
                </tr>
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">JSTL</a></td>
                    <td><a href="index.jsp">내가 조회한 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>5</td>
                    <td>5</td>            
                </tr>
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">EL</a></td>
                    <td><a href="index.jsp">내가 조회한 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>6</td>
                    <td>6</td>            
                </tr>
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">Javascript</a></td>
                    <td><a href="index.jsp">내가 조회한 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>71</td>
                    <td>71</td>                    
                </tr>
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">Jquery</a></td>
                    <td><a href="index.jsp">내가 조회한 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>22</td>
                    <td>22</td>                
                </tr>    
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">Jquery</a></td>
                    <td><a href="index.jsp">내가 조회한 문제입니다 ..</a></td>
                    <td>2024-06-24 11:39:45</td>
                    <td>23</td>
                    <td>23</td>                
                </tr>
                <tr style="height: 50px">
                    <td><a href="questionlist.jsp">Jquery</a></td>
                    <td><a href="index.jsp">내가 조회한 문제입니다 ..</a></td>
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
        </div>
    </div>
</div>
<jsp:include page="./include/tail.jsp"></jsp:include>


