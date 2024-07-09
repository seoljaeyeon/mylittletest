<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./include/head_login.jsp"></jsp:include>
<style>
	.a {	
	width: 100%;
	height: 100%;
	display: inline-block;
	}						
</style>

	<form id="writeFrm" name="writeFrm" action="announcement_writeok.jsp">	
		<div class="a">
		<div>&nbsp&nbsp<span style="font-size: 20px; font-weight: bolder;">공지사항</span>
		<br><br>
		<div>
		<input type="text" id="title" name="title" style="font-size: 35px; font-weight: bolder;  background-color: #474747; color: white; border-width: 0;" placeholder="제목을 입력해주세요.">
			<div align="right">
				<a href="announcement_list.jsp">
					<button type="button" style="height: 40px; width: 100px; border-radius: 10px; background-color: transparent; color: white;">돌아가기</button>
				</a>
			</div>
		</div>
		<br>
		<div style="border: none; outline: none; border-bottom: 1px solid white;"></div>
		<div style="margin-top: 30px; margin-bottom: 30px;" contenteditable="true" placeholder="내용을 입력해주세요.">
			본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다.<br> 
			본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다.<br>
			본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다.<br>
			본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다.<br> 
			본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다. 본문의 내용입니다.<br><br>
		<img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" alt="" draggable="false" style="width:400px; height: 300px;">  
		<img src="https://www.ddengle.com/files/attach/images/11334861/457/305/015/836501b8c2508005ec25765ef8268523.jpg" alt="" draggable="false" style="width:400px; height: 300px;"><br>
		감사합니다.
		</div>
		<div style="border: none; outline: none; border-bottom: 1px solid white;"></div>
		<br><br>
		<div><input type="file" id="file" name="file" style="height: 40px; width: 200px;"></div>
		<div align="right">
			<input type="datetime-local" id="reservation_time" name="reservation_time" required style="width: 200px; height: 25px; border-radius: 50px; text-align: center; margin-right: 50px; background-color: transparent; color: white;">
			<input type="submit" id="write_btn" name="write_btn" value="작성완료" style="height: 40px; width: 100px; border-radius: 10px; background-color: transparent; color: white;">
		</div>
		</div>
		</div>
	</form>	
<jsp:include page="./include/tail.jsp"></jsp:include>
<script>
function schedulePost(event) {
    event.preventDefault(); // 폼 제출을 막음

    const title = document.getElementById("title").value;
    const content = document.getElementById("content").value;
    const reservationTime = document.getElementById("reservationTime").value;
    const reservationDate = new Date(reservationTime);

    const currentTime = new Date();
    const delay = reservationDate - currentTime;

    if (delay > 0) {
        setTimeout(() => {
            // 실제 게시글 작성 로직
            // 예: 서버에 AJAX 요청 보내기
            alert(`게시글 작성: ${title}\n내용: ${content}`);
            // 여기에 서버로 게시글 데이터를 보내는 로직을 추가할 수 있습니다.
        }, delay);
        alert(`게시글이 ${reservationTime}에 작성될 예정입니다.`);
    } else {
        alert("예약 시간이 현재 시간보다 이전입니다. 다시 설정해 주세요.");
    }
}
</script>		