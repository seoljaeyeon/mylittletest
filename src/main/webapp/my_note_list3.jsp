<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./include/head_login.jsp"></jsp:include>
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
    <div>
        <div style="display: inline-block; font-size: 30px; font-weight: bolder; margin-left: 50px;" id="my_note">
            <a href="#">내 문제</a>
        </div>
        <div style="display: inline-block; font-size: 30px; font-weight: bolder; margin-left: 50px;" id="searchNote">
            <a href="#">조회한 문제</a>
        </div>
	<div align="right" style="display: inline-block;">
		<form style="width: 565px; background: #333333; padding: 10px; display: block; border-radius: 17px; margin-left: 495px;">
			<input type="text" style="width: 510px; height: 25px; font-size:16px; background-color: #333333; border: none; outline: none; border-radius: 17px; color: white;" type="text" placeholder="Search" spellcheck="false">
			<button style="align-items: center; border: none; background: #333333; border-radius: 17px; font-size:16px;">🔍</button>
		</form>			
	</div>
    </div>
    <br><br>
    <div id="container"></div>
</div>
<jsp:include page="./include/tail.jsp"></jsp:include>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
    	$.ajax({
            url: 'my_note_list2.jsp', // 렌더링할 JSP 페이지
            method: 'GET',
            success: function(data) {
                $('#container').html(data);
            },
            error: function() {
                alert('Failed to load content');
            }
        });
    	
        $('#my_note').click(function(event) {
            event.preventDefault(); // 기본 동작을 막음
            $.ajax({
                url: 'my_note_list2.jsp', // 렌더링할 JSP 페이지
                method: 'GET',
                success: function(data) {
                    $('#container').empty().html(data);
                },
                error: function() {
                    alert('Failed to load content');
                }
            });
        });
        
        $('#searchNote').click(function(event) {
            event.preventDefault(); // 기본 동작을 막음
            $.ajax({
                url: 'my_note_list4.jsp', // 렌더링할 JSP 페이지
                method: 'GET',
                success: function(data) {
                    $('#container').empty().html(data);
                },
                error: function() {
                    alert('Failed to load content');
                }
            });
        });
    });
</script>
