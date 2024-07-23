<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<jsp:include page="./include/head.jsp"></jsp:include>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function() {
	// CSRF token 설정
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

    const solveAllBtn = document.querySelector(".solve_all");
    const categoryTitleElement = document.getElementById("categoryTitleName");

    solveAllBtn.addEventListener("click", function() {
        const noteType = 4; // category에 포함되는 notelist 페이지 코
        const categoryTitle = categoryTitleElement.innerText;

        fetch('/mylittletest/notelist/'+noteType, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify({
                noteType: noteType,
                categoryTitle: categoryTitle,
            })
        })
        .then(response => response.json())
        .then(data => {
            if (data.status === "success") {
                // 리다이렉트
                window.location.href = data.url;
            } else if (data.status === "login_needed") {
                window.location.href = data.url;
            } else {
                console.error("Failed to get notes.");
            }
        })
        .catch(error => {
            console.error("Error fetching notes:", error);
        });
    });
    
    const btnO = document.getElementById("btnO");
    const btnX = document.getElementById("btnX");
    const noteNo = document.getElementById("noteNo").value; // 숨겨진 필드에서 값 가져오기

    btnO.addEventListener("click", () => {
        btnO.classList.add("clicked");
        btnX.classList.remove("clicked2");
        sendAnswer(2);
    });

    btnX.addEventListener("click", () => {
        btnX.classList.add("clicked2");
        btnO.classList.remove("clicked");
        sendAnswer(1);
    });

    function sendAnswer(answerType) {
        $.ajax({
            type: "POST",
            url: "/mylittletest/answer",
            data: {
                noteNo: noteNo,
                answerType: answerType
            },
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function(response) {
                if (response.status === "success") {
                    console.log("Answer recorded successfully.");
                } else if (response.status === "login_needed") {
                    window.location.href = response.url;
                } else {
                    console.error("Failed to record answer.");
                }
            },
            error: function(e) {
                console.error("Error recording answer: ", e);
            }
        });
        
    }
    
    // 좋아요 
    document.getElementById("like").addEventListener("click", function() {
        sendFavorite();
    });

 
    function sendFavorite() {
        $.ajax({
            type: "POST",
            url: "/mylittletest/favorite",
            data: {
                noteNo: document.getElementById("noteNo").value,
                requestType: 0,
                targetType: 1
            },
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function(response) {
                if (response.status === "insert_success") {
                    console.log("Favorite recorded successfully.");
                } else if (response.status === "login_needed") {
                    window.location.href = response.url;
                } else {
                    console.error("Failed to record favorite.");
                }
            },
            error: function(e) {
                console.error("Error recording favorite: ", e);
            }
        });
    }
   

    // 북마크 버튼 애니메이션
    const bookmarkBtns = document.querySelectorAll('.bookmark_btn');

    bookmarkBtns.forEach(function(btn) {
        btn.addEventListener('click', function() {
            this.classList.toggle('bookmarked');
        });
    });

    // 좋아요 버튼 애니메이션
    const likeBtns = document.querySelectorAll('.like');

    likeBtns.forEach(function(Btn) {
        Btn.addEventListener('click', function() {
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

    // 정답박스보기
    document.getElementById("answer").addEventListener("click", function() {
        this.classList.add("clicked"); // answer 클릭 시 opacity 0으로 변환
        setTimeout(() => {
            document.getElementById("showanswer").classList.add("clicked"); // 0.5초 후 showanswer가 나타나도록 변환
        }, 500);
    });

    // 오디오 재생
    const audioPlayer = document.getElementById('audioPlayer');

    // 오디오 요소 클릭 이벤트 핸들러
    audioPlayer.addEventListener('click', function (event) {
        const rect = audioPlayer.getBoundingClientRect();
        const clickX = event.clientX - rect.left;
        const playerWidth = rect.width;
        const duration = audioPlayer.duration;
        const newTime = (clickX / playerWidth) * duration;

        // currentTime 설정 전 확인
        if (!isNaN(newTime) && newTime >= 0 && newTime <= duration) {
            audioPlayer.currentTime = newTime;
        }
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
	.modify_btn:hover{
		background-color:white;
		color:black;
	}
	.bookmark_btn,.reportbtn{
		font-size:30px;
		margin-left:10px;
		margin-top:5px;
		cursor:pointer;
		transition: transform 0.3s, color 0.3s;
	}
	 .bookmark_btn.bookmarked {
            color: yellow;
            transform: scale(1.5);
        }
        .bookmark_btn:hover{
        	color:yellow;
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
		background-color: #333333;
		color: #ffffff;
		border-radius: 10px;
		height: 83px;
		width: 170px;
		padding: auto;
		justify-content: center;
		align-items: center;
		font-size: 20px;
		text-align:center;
		display:flex;
		cursor:pointer;
		border: none;
	}
	.next:hover {
	    color: #007bff;
	    transform: scale(1.1);
	}
	
	.mini_box{
		height: 58px;
		width: 170px;
		text-align:left;
		margin-top: 15px;
		font-size:20px;
	}
	.like_box{
		display:flex;
	}
	.like{
		margin-bottom:10px;
		cursor:pointer;
	}
	.like.liked{
		 color: red;
         transform: scale(1.1);
	}
    .like:hover{
         color:red;
         transform: scale(1.1);
    }
	.share {
	    cursor: pointer;
	    transition: transform 0.3s, color 0.3s;
	}
	
	.share:hover {
	    color: #007bff;
	    transform: scale(1.2);
	}
	.check{
		display:flex;
		gap:10px;
		height:40px;		
	}
	.audioPlayer{
		width:250px;
		height:40px;
	}
	.success_btn{
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
		transition: background-color 0.3s ease, transform 0.3s ease;
	}
	.success_btn:hover{
		color: #007bff;
	    transform: scale(1.1);
	}
		.success_btn.clicked {
	    background-color: #2E64FE; 
	    transform: scale(1.1);
	}
	
	.success_btn.clicked2 {
	    background-color: #f44336; 
	    transform: scale(1.1);
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
</style>
<div class="solve_container">
	<div class="solve_header">
		<div class="solve_title">▷<span name="categoryTitleName" id="categoryTitleName">${questionVO.categoryVO.categoryTitle}</span></div>
		<div class="solve_list">
			<div class="solve_question" style="margin-top:5px;"><span>내 문제 풀기</span></div>
			<div class="solve_all" style="margin-top:15px;"><span style="font-size:12px;">문제 전체 보기</span></div>
		</div>
		<div class="today_box">
			<div class="today_question"><span>오늘 본 문제수 </span></div>
    <div class="today_count"><span style="font-size:20px;">${questionVO.todayNoteViewInCategory}</span></div>
		</div>
		<div class="modify_btn" onclick="location.href='/mylittletest/modify'">수정 </div>
		<div class="modify_btn" onclick="location.href='questiondelete.jsp'">비활성화</div>
		<div class="modify_btn">덜보기</div>
		<div class="bookmark_btn">★</div>
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
					<div class="next" onclick="location.href='/mylittletest/${menuName}/category/${questionVO.categoryVO.categoryTitle}'">▷다음문제</div>
					<div class="mini_box">
						<div class="like_box">
							<div class="like" id="like">❤</div>
							<div class="like_count" style="margin-left:10px; height:fit-content;"><span>${questionVO.favoriteCount }</span></div>
						</div>
						<div class="share" id="sharebtn">📤공유하기</div>
					</div>
					<div class="media">
						<audio id="audioPlayer" class="audioPlayer" controls="controls" loop="loop">
						  <source src="https://t1.daumcdn.net/cfile/tistory/9945CE425CE45B920A"  type="audio/mpeg"/>
						</audio>					    
						<div>
						    <!-- Your existing HTML content here... -->
						    <div class="check">
						        <div class="success_btn ${questionVO.answerType == 2 ? 'clicked' : ''}" id="btnO">O</div>
						        <div class="success_btn ${questionVO.answerType == 1 ? 'clicked2' : ''}" id="btnX">X</div>
						    </div>
						    <!-- More of your existing HTML content... -->
						</div>
					</div>
				</div>	
			</div>
			<div class="answer-container">
				<div class="answer" id="answer">🔒정답 & 해설보기</div><div class="showanswer" id="showanswer">${questionVO.noteVO.noteAnswer}</div>
			</div>
		</div>
	</div>
	<div class="reply_container">
		<c:if test="${ userVO != null }">
		<form id="replyFrm" name="replyFrm" method="post" action="/mylittletest/replyWrite">
	    	<sec:csrfInput/>
	    	<input type="hidden" name="noteNo" id="noteNo" value="${questionVO.noteVO.noteNo}">
	    	<input type="hidden" name="categoryTitle" id="categoryTitle" value="${questionVO.categoryVO.categoryTitle}">
	    	<input type="hidden" name="menuPath" id="menuPath" value="${menuName}">
			<div class="reply_box">
				<div class="reply_profile" style="font-size:30px; margin-top:5px;">😃</div>
				<div class="replyinput"><input type="text" class="reply_input" id="replyContent" name="replyContent" placeholder="댓글을 입력해주세요"></div>
				<div class="replybtn"><button type="submit" class="reply_btn">작성</button></div>
			</div>
		</form>
		</c:if>
		<div class="reply">
		<c:forEach var="reply" items="${ questionVO.replies }">
			<div class="reply_show">
				<div class="reply_profiles" style="font-size:30px;">${ reply.nickname}</div>
				<div class="replynote">
					${reply.replyContent}
					<div class="reply_date" id="reply_report"><span>${(reply.updatedAt == null) ? reply.createdAt : reply.updatedAt }</span></div>
				</div>
				<c:if test="${ userVO != null and userVO.userNo == reply.userNo }">
					<div class="replycheck">
						<div class="reply_modify_btn">수정</div>
						<div class="reply_modify_btn">삭제</div>
					</div>
				</c:if>
			</div>
		</c:forEach>
		</div>
	</div>
</div>

<jsp:include page="./include/tail.jsp"></jsp:include>