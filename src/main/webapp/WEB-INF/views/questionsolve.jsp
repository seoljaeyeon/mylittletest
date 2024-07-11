<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<jsp:include page="./include/head_login.jsp"></jsp:include>
<script>
document.addEventListener("DOMContentLoaded", function() {
		//팝업요소를 가져온다
    var popup = document.getElementById("popup_report");

    //팝업 오픈버튼을 가져옴
    var popupOpenButton = document.getElementById("reportbtn");

    // 버튼에 클릭이벤트 추가
    popupOpenButton.addEventListener("click", function() {
        // 팝업 표시 여부를 전환
        popup.classList.toggle("show");
    });
    // 선택사항: 닫기 버튼 클릭 시 팝업을 닫는 기능 추가
    var popupCloseButton = document.getElementById("delete");
    popupCloseButton.addEventListener("click", function() {
        popup.classList.remove("show");
    });
    
    // 댓글 신고 팝업요소를 가져온다
    var popupReply = document.getElementById("popup_reply");

    // 댓글 신고 팝업 오픈버튼을 가져옴
    var popupOpenButtonsReply = document.querySelectorAll(".reply_date");

    // 모든 버튼에 클릭 이벤트 추가
    popupOpenButtonsReply.forEach(function(btn) {
        btn.addEventListener("click", function() {
            // 팝업 표시 여부를 전환
            popupReply.classList.toggle("show");
        });
    });

    // 선택사항: 닫기 버튼 클릭 시 팝업을 닫는 기능 추가
    var popupCloseButtonReply = document.getElementById("reply_delete");
    popupCloseButtonReply.addEventListener("click", function() {
        popupReply.classList.remove("show");
    });
    
	 // 북마크 버튼 애니메이션
    const likeBtns = document.querySelectorAll('.like_btn');

    likeBtns.forEach(function(btn) {
        btn.addEventListener('click', function() {
            this.classList.toggle('liked');
        });
    });
    // 공유하기 버튼 기능 추가
    const shareBtn = document.getElementById('sharebtn');
    shareBtn.addEventListener('click', function() {
        if (navigator.share) {
            navigator.share({
                title: document.title,
                text: '이 페이지를 공유합니다:',
                url: window.location.href
            }).then(() => {
                console.log('공유 성공');
            }).catch((error) => {
                console.error('공유 실패', error);
            });
        } else {
            // Web Share API를 지원하지 않는 브라우저에서는 다른 공유 방법을 제안할 수 있습니다.
            alert('이 브라우저는 공유 기능을 지원하지 않습니다.');
        }
    });
    // 팁박스보기
   document.getElementById("tip").addEventListener("click", function() {
		    this.style.opacity = "0"; // tip 클릭 시 opacity 0으로 변환
		    setTimeout(() => {
		        document.getElementById("showtip").style.opacity = "1"; // 0.5초 후 showtip이 나타나도록 변환
		    }, 500);
		});
    //정답박스보기
    document.getElementById("answer").addEventListener("click", function() {
	        this.classList.add("clicked"); // answer 클릭 시 opacity 0으로 변환
	        setTimeout(() => {
	            document.getElementById("showanswer").classList.add("clicked"); // 0.5초 후 showanswer가 나타나도록 변환
	        }, 500);
	    });
  });
</script>
<style>
	.solve_container{
		display: inline-flex;
    	flex-direction:column;
    	margin-left: 120px;
   		width: fit-content;
        background-color: #474747;
        padding: 1.5rem 2rem 2rem 2rem;
        border-radius: 2rem;
        height:750px; 
        gap:10px;
        border: 1px solid black;
	}
	.solve_header{
		display:inline-flex;
		gap:10px;
		height:70px;
		width:1100px;
	}
	.solve_title{
		width:250px;
		font-size:35px;
		text-align:left;
		border-right: 1px solid #000000;
	}
	.solve_list{
		text-align:center;
		border-right: 1px solid #000000;
		width:130px;
	}
	.today_box{
		border-right: 1px solid #000000;
		width:140px;
	}
	.today_question{
		text-align:center;
		margin-top:5px;
	}
	.today_count{
		text-align:center;
		margin-top:10px;
	}
	.modify_btn{
		background-position: center;
    	background-size: cover;
    	-webkit-appearance: none;
		-moz-appearance: none;
		appearance: none;
		background-color: #333333;
		color: #ffffff;
		border-radius: 10px;
		height: 3rem;
		width: 120px;
		padding: auto;
		justify-content: center;
		align-items: center;
		font-size: 1rem;
		text-align:center;
		display:flex;
		cursor:pointer;
		border: none;
		margin-top:5px;
	}
	.like_btn,.reportbtn{
		font-size:30px;
		margin-left:10px;
		margin-top:5px;
		cursor:pointer;
		transition: transform 0.3s, color 0.3s;
	}
	 .like_btn.liked {
            color: red;
            transform: scale(1.5);
        }
	.solve_main{
		display:inline-flex;
		gap:15px;
		width:1100px;
		flex-wrap:wrap;
	}
	.question{
		height:200px;
		width:45%;
		margin-top:10px;
	}
	.question_note{
		border:1px soloid #696969;
		overflow:auto;
		height:165px;
	}
	.answer_box{
		height:200px;
		width:fit-content;
		margin-top:10px;
	}
	.question_sub{
		height:200px;
		margin-top:10px;
		width:1050px;
		display:inline-flex;
		gap:15px;
	}
	.tip_box{
		width: 495px;
	    height: 83px;
	    position: relative;
	    margin: 0 auto;
	}
	 .tip,.showtip {
        background-color: #333333;
	    color: #ffffff;
	    border-radius: 10px;
	    width: 495px;
	    height: 83px;
	    font-size: 25px;
	    text-align: center;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    cursor: pointer;
	    border: none;
	    z-index: 1;
	    transition: opacity 0.5s ease; /* opacity 속성에 대한 transition 효과 추가 */
	    position: absolute;
	    top: 0;
	    left: 0;
	    transition: opacity 0.5s ease;
    }
	.tip {
	    z-index: 1;
	}
	
	.showtip {
	    z-index: 0;
	    opacity: 0;
	}
	
	.tip.clicked {
	    opacity: 0;
	}
	
	.showtip.clicked {
	    opacity: 1;
	}
	.next_box{
		display:flex;
		width:495px;
		height:83px;
		margin-top:10px;
		gap:10px;
	}
	.next{
		-webkit-appearance: none;
		-moz-appearance: none;
		appearance: none;
		background-color: #333333;
		color: #ffffff;
		border-radius: 10px;
		height: 83px;
		width: 170px;
		padding: auto;
		justify-content: center;
		align-items: center;
		font-size: 25px;
		text-align:center;
		display:flex;
		cursor:pointer;
		border: none;
	}
	.mini_box{
		height: 58px;
		width: 170px;
		text-align:left;
		margin-top: 15px;
		font-size:20px;
	}
	.like{
		margin-bottom:10px;
	}
	.like.likes{
		margin-bottom:10px;
		color: red;
        transform: scale(1.5);
	}
	.share {
	    cursor: pointer;
	    transition: transform 0.3s, color 0.3s;
	    margin-top: 10px;
	}
	
	.share:hover {
	    color: #007bff;
	    transform: scale(1.2);
	}
	.check{
		display:flex;
		gap:10px;
		height:40px;
		margin-top:43px;
	}
	.success_btn{
		-webkit-appearance: none;
		-moz-appearance: none;
		appearance: none;
		background-color: #333333;
		color: #ffffff;
		border-radius: 10px;
		height: 40px;
		width: 80px;
		padding: auto;
		justify-content: center;
		align-items: center;
		font-size: 25px;
		text-align:center;
		display:flex;
		cursor:pointer;
		border: none;	
	}
	
	.answer-container {
    position: relative;
    width: 542px;
    height: 175px;
    margin: 0 auto;
	}
	
	.answer, .showanswer {
	    background-color: #333333;
	    color: #ffffff;
	    border-radius: 10px;
	    height: 175px;
	    width: 542px;
	    padding: auto;
	    justify-content: center;
	    align-items: center;
	    font-size: 25px;
	    text-align: center;
	    display: flex;
	    cursor: pointer;
	    border: none;
	    position: absolute;
	    top: 0;
	    left: 0;
	    transition: opacity 0.5s ease;
	}
	
	.answer {
	    z-index: 1;
	}
	
	.showanswer {
	    z-index: 0;
	    opacity: 0;
	}
	
	.answer.clicked {
	    opacity: 0;
	}
	
	.showanswer.clicked {
	    opacity: 1;
	}
	.reply_container{
		display: inline-flex;
    	flex-direction:column;
   		width: fit-content;
        background-color: #474747;
        padding: 1.5rem 2rem 2rem 2rem;
        border-radius: 2rem;
        height:200px; 
        gap:10px;
	}
	.reply_box{
		width:1000px;
		display:flex;
	}
	.replyinput{
		margin-top:10px;
		margin-left:10px;
	}
	.reply_input{	
		border: none;
		outline: none;
		font-size: 20px;
		position: relative;
		width: 800px;
		background-color: transparent;
		border-bottom: 3px solid #cccccc;
		color:#ffffff;
	}
	.replybtn{
		width:fit-content;
		margin-top:5px;
	}
	.reply_btn{
		box-shadow : 0.3rem 0.3rem 0.7rem #333333, -0.3rem -0.3rem 0.7rem #333333;
	    background-color: #333333;
	    color: #ffffff;
	    border-radius: 20px;
	    height: 2rem;
	    width: 80px;
	    padding: auto;
	    display:flex;
	    justify-content: center;
	    align-items: center;
	    font-family: 'Pretendard-Regular';
	    border: none;
	    font-size: 1rem;
	    cursor:pointer;
    }
   	.reply{
   		display:inline-flex;
   		overflow:auto;
    	width:1050px;
    	flex-direction:column;
   	}
    .reply_show{
    	display:flex;
    
    }
    .reply_profiles{
    	width:fit-content;
    }
    .replynote{
    	margin-top:10px;
		margin-left:10px;
		width: 800px;
		border-bottom: 1px solid #cccccc;
		color:#ffffff;
    }
    .replycheck{
    	display:flex;
		gap:10px;
		height:fit-content;
		margin-top:12px;
		margin-left:5px
    }
    .reply_modify_btn{
    	-webkit-appearance: none;
		-moz-appearance: none;
		appearance: none;
		background-color: #333333;
		color: #ffffff;
		border-radius: 10px;
		height: 30px;
		width: 70px;
		padding: auto;
		justify-content: center;
		align-items: center;
		font-size: 15px;
		text-align:center;
		display:flex;
		cursor:pointer;
		border: none;	
    }
    .reply_date{
    	float:right;
    	cursor:pointer;
    }
    /*************************** 팝업 스타일  **************************************/
    .popup_wrap {
		    display: none; 
		    position: fixed;
		    top: 0;
		    left: 0;
		    width: 100%;
		 	height: 100%;
		   	background-color: rgba(0, 0, 0, 0.5); 
		   	z-index: 1000; 
		    overflow: auto; 
		}
		.report_area {
			background-color: #ffffff;
			width: 300px;
			max-width: 40rem;
			position: absolute;
			top: 50%;
			left: 50%;
			transform: translate(-50%, -50%);
			padding: 1rem;
			border-radius: 1rem;
			box-shadow: 0 0 1rem rgba(0, 0, 0, 0.1);
		}
		.report_list{
			display:inline-flex;
			margin-bottom:0.5rem;
		}
		.report_note{
		 	display:inline-flex;
			margin-bottom:0.5rem;
		}
		

	.report_btn{
		-webkit-appearance: none;
		-moz-appearance: none;
		appearance: none;
		box-shadow: 0.3rem 0.3rem 0.7rem #cccccc, -0.3rem -0.3rem 0.7rem #dedede;
		background-color: #000000;
		color: #ffffff;
		border-radius: 1rem;
		height: 3rem;
		width: 100px;
		padding: auto;
		display: flex;
		justify-content: center;
		align-items: center;
		font-size: 1rem;
		text-align:center;
		margin-left:1rem;
		font-weight:bold;
	}		
	#reportnote{
		width:220px;
		resize:vertical;
		height:122px;
		font-size: 15px;
		border-radius:5px;
	    background-color:#ffffff;
	    color:#000000;
	}   
	.show{
		display:block;
	}
			    
</style>
<!-- 팝업영역  -->
<div class="popup_wrap" id="popup_report">
		<div class="report_area">
			<h1 class="report_title" style="color:black;">신고하기</h1>
			<div class="report_list">
				<span style="font-weight:bold; color:black;">신고분류</span>
				<div class=report_choice style="margin-left:8px;">
					<select id="reportlist" class="reportlist">
							<option value="1">욕설/반말/부적절한 언어</option>
							<option value="2">저작권 침해</option>
							<option value="3">도배성 게시글</option>
							<option value="4">광고성 게시물</option>
							<option value="5">회원 비방</option>
						</select>
				</div>
			</div>
			<div class="report_note">
				<span style="font-weight:bold; font-size:15px; color:black;">신고내용</span>
				<div class=report_box style="margin-left:0.8rem"><textarea id="reportnote"></textarea></div>
			</div>
			<div class="reportbtn" style="display:inline-flex; flex-direction:row; gap:2rem; ">
	            <div class="report_btn" id="report">신고</div>
	            <div class="report_btn" id="delete" style="background-color:#ffffff;color:black; ">취소</div>
	        </div>
		</div>
	</div>
	<!-- 댓글 신고 팝업 -->
	<div class="popup_wrap" id="popup_reply">
		<div class="report_area">
			<h1 class="report_title" style="color:black;">신고하기</h1>
			<div class="report_list">
				<span style="font-weight:bold; color:black;">신고분류</span>
				<div class=report_choice style="margin-left:8px;">
					<select id="reportlist" class="reportlist">
							<option value="1">욕설/반말/부적절한 언어</option>
							<option value="2">저작권 침해</option>
							<option value="3">도배성 게시글</option>
							<option value="4">광고성 게시물</option>
							<option value="5">회원 비방</option>
						</select>
				</div>
			</div>
			<div class="report_note">
				<span style="font-weight:bold; font-size:15px; color:black;">신고내용</span>
				<div class=report_box style="margin-left:0.8rem"><textarea id="reportnote"></textarea></div>
			</div>
			<div class="reportbtn" style="display:inline-flex; flex-direction:row; gap:2rem; ">
	            <div class="report_btn" id="report">신고</div>
	            <div class="report_btn" id="reply_delete" style="background-color:#ffffff;color:black; ">취소</div>
	        </div>
		</div>
	</div>
<!-- 팝업영역 -->
<div class="solve_container">
	<div class="solve_header">
		<div class="solve_title"><span>▷${questionVO.categoryVO.categoryTitle}</span></div>
		<div class="solve_list">
			<div class="solve_question" style="margin-top:5px;"><span>내 문제 풀기</span></div>
			<div class="solve_all" style="margin-top:15px;"><span style="font-size:12px;">문제 전체 보기</span></div>
		</div>
		<div class="today_box">
			<div class="today_question"><span>오늘 본 문제수 </span></div>
    <div class="today_count"><span style="font-size:20px;">${fn:length(viewHistory)}</span></div>
		</div>
		<div class="modify_btn" onclick="location.href='modify.jsp'">수정 </div>
		<div class="modify_btn" onclick="location.href='questiondelete.jsp'">비활성화</div>
		<div class="modify_btn">덜보기</div>
		<div class="reportbtn" id="reportbtn">🚨</div>
		<div class="like_btn">❤</div>
	</div>
	<div class="solve_main">
		<div class="question">
			<div class="question_title"><span style="font-weight:bold; font-size:15px;">${questionVO.noteVO.noteTitle }</span></div>
			<div class="question_note" style="margin-top:10px; font-size:13px;">
				${questionVO.noteVO.noteContent}
			</div>
		</div>
		<div class="answer_box">
			<div class="question_title"><span style="font-weight:bold;">정답입력</span></div>
			<div class="answer_input" style="margin-top:10px;"><textarea style="background-color:#474747; color:#ffffff; width:536px; height:165px;">정답입력 해주세요</textarea></div>
		</div>
		<div class="question_sub">
			<div class="sub">
			<div class="tip_box">
				<div class="tip" id="tip">💡팁 보기</div><div class="showtip" id="showtip">${questionVO.noteVO.noteHint}</div>
			</div>
				<div class="next_box">
					<div class="next" onclick="location.href='questionsolve.jsp'">▷다음문제</div>
					<div class="mini_box">
						<div class="like">❤ ${questionVO.favoriteCount }</div>
						<div class="share" id="sharebtn">📤공유하기</div>
					</div>
					<div class="check">
						<div class="success_btn">O</div>
						<div class="success_btn">X</div>
					</div>
				</div>	
			</div>
			<div class="answer-container">
				<div class="answer" id="answer">🔒정답 & 해설보기</div><div class="showanswer" id="showanswer">${questionVO.noteVO.noteAnswer}</div>
			</div>
		</div>
	</div>
	<div class="reply_container">
		<form id="replyFrm" name="replyFrm" method="post" action="replyok.jsp">
			<div class="reply_box">
				<div class="reply_profile" style="font-size:30px; margin-top:5px;">😃</div>
				<div class="replyinput"><input type="text" class="reply_input" id="reply" name="reply" placeholder="댓글을 입력해주세요"></div>
				<div class="replybtn"><button type="button" class="reply_btn">작성</button></div>
			</div>
		</form>
		<div class="reply">
			<div class="reply_show">
				<div class="reply_profiles" style="font-size:30px;">😃</div>
				<div class="replynote">
					댓글입니다.댓글입니다.댓글입니다.댓글입니다.댓글입니다.댓글입니다.댓글입니다.댓글입니다.
					<div class="reply_date" id="reply_report">🚨신고 <span>2024-07-01</span></div>
				</div>
				<div class="replycheck">
					<div class="reply_modify_btn">수정</div>
					<div class="reply_modify_btn">삭제</div>
				</div>
			</div>
			<div class="reply_show">
				<div class="reply_profiles" style="font-size:30px;">😃</div>
				<div class="replynote">
					댓글입니다.댓글입니다.댓글입니다.댓글입니다.댓글입니다.댓글입니다.댓글입니다.댓글입니다.
					<div class="reply_date" id="reply_report">🚨신고 <span>2024-07-01</span></div>
				</div>
				<div class="replycheck">
					<div class="reply_modify_btn">수정</div>
					<div class="reply_modify_btn">삭제</div>
				</div>
			</div>
			<div class="reply_show">
				<div class="reply_profiles" style="font-size:30px;">😃</div>
				<div class="replynote">
					댓글입니다.댓글입니다.댓글입니다.댓글입니다.댓글입니다.댓글입니다.댓글입니다.댓글입니다.
					<div class="reply_date" id="reply_report">🚨신고 <span>2024-07-01</span></div>
				</div>
				<div class="replycheck">
					<div class="reply_modify_btn">수정</div>
					<div class="reply_modify_btn">삭제</div>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="./include/tail.jsp"></jsp:include>