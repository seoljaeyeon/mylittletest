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
$(document).ready(function() {
	// CSRF token 설정
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    
    const btnO = document.getElementById("btnO");
    const btnX = document.getElementById("btnX");
    const noteNo = document.getElementById("noteNo").value; // 숨겨진 필드에서 값 가져오기

    
    
    // 북마크 버튼 애니메이션
    const bookmarkBtns = document.querySelectorAll('.bookmark_btn');

    bookmarkBtns.forEach(function(btn) {
        btn.addEventListener('click', function() {
            this.classList.toggle('bookmarked');
        });
    });

    document.getElementById('modify_btn').addEventListener('click', function() {
        const noteNo = parseInt(${questionVO.noteVO.noteNo}, 10);
        const menuName = '${menuName}'; // menuName 인코딩

        const requestData = {
            noteNo: noteNo,
            menuName: menuName
        };

        fetch('/mylittletest/modify', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify(requestData)
        })
        .then(response => response.text())
        .then(url => {
            if (url) {
                window.location.href = url; // 응답 받은 URL로 이동
            } else {
                console.error('Invalid response from server');
            }
        })
        .catch(error => console.error('Error:', error));
    });
    
    // 5초마다 updateLikeCount 함수 호출
    setInterval(updateLikeCount, 5000);
    
    function updateLikeCount() {
        const likeCountSpan = document.querySelector('.like_count span');
        const noteNo = document.querySelector('.like').dataset.noteNo;
        fetch(`/mylittletest/getLikeCount?noteNo=${noteNo}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
        	if (data.success) {
    	        likeCountSpan.textContent = data.count;
        	} 
        })
        .catch(error => console.error('Error fetching like count:', error));
    }

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
    
    function deleteclick(event) {
        const button = event.target;
        const noteNo = button.getAttribute('data-note-no');

        fetch('/mylittletest/favorite', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                [csrfHeader]: csrfToken 
            },
            body: JSON.stringify({
                noteNo: parseInt(noteNo),
                requestType: -2, // 차단 요청
                targetType: 1   
            })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Server response:', data); 
            if (data.status === 'insert_success') {
                alert('차단 처리 성공');
                button.textContent = '비활성화됨';
                button.classList.add('disabled'); 
                button.disabled = true; 
               	window.location.href = "/mylittletest/index"
            } else if (data.status === 'insert_failed') {
                alert('차단 처리 실패');
            } else if (data.status === 'parameter_null') {
                alert('파라미터가 누락되었습니다');
            } else if (data.status === 'wrong_request') {
                alert('잘못된 요청입니다');
            } else if (data.status === 'login_needed') { 
                window.location.href = data.url;
            }
        })
        .catch(error => {
            console.error('Fetch error:', error); 
            alert('서버 오류가 발생했습니다');
        });
    }
    
    function showLessClick(event) {
        const button = event.target;
        const noteNo = button.getAttribute('data-note-no');

        fetch('/mylittletest/favorite', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify({
                noteNo: parseInt(noteNo, 10), // 문자열을 정수로 변환
                requestType: -1, // 덜보기 요청
                targetType: 1   // 문제
            })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Server response:', data);
            if (data.status === 'insert_success') {
                alert('덜보기 처리 성공');
                button.textContent = '덜보기됨'; // 버튼 텍스트 변경
                button.classList.add('disabled'); // 'disabled' 클래스 추가
                button.disabled = true; // 버튼 비활성화
            } else if (data.status === 'insert_failed') {
                alert('덜보기 처리 실패');
            } else if (data.status === 'parameter_null') {
                alert('파라미터가 누락되었습니다');
            } else if (data.status === 'wrong_request') {
                alert('잘못된 요청입니다');
            } else if (data.status === 'login_needed') {
                window.location.href = data.url;
            }
        })
        .catch(error => {
            console.error('Fetch error:', error);
            alert('서버 오류가 발생했습니다');
        });
    }

    const deleteButtons = document.querySelectorAll('.delete_btn');
    deleteButtons.forEach(button => {
        button.addEventListener('click', deleteclick);
    });
    
    const showLessButtons = document.querySelectorAll('.showless_btn');
    showLessButtons.forEach(button => {
        button.addEventListener('click', showLessClick);
    }); 
});
function goBack() {
    window.history.back();
}
</script>
<style>
.delete_btn.disabled {
    background-color: gray;
    cursor: not-allowed;
    opacity: 0.6;
}

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
	.modify_btn,.delete_btn,.showless_btn{
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
		
	.media{
		align-content:flex-end;
	}
	.answer-container {
    position: relative;
    width: 542px;
    height: 175px;
    margin: 0 auto;
	}
	.commentary-container {
    position: relative;
    width: 542px;
    height: 175px;
    margin: 0 auto;
	}
	
	.commentary, .showcommentary {
	    background-color: #333333;
	    color: #ffffff;
	    border-radius: 10px;
	    height: 175px;
	    width: 271px;
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
	.commentary {
	    z-index: 1;
	}
	
	.showcommentary {
	    z-index: 0;
	    opacity: 0;
	}
	
	.commentary.clicked {
	    opacity: 0;
	}
	
	.showcommentary.clicked {
	    opacity: 1;
	}
	
	.answer, .showanswer {
	    background-color: #333333;
	    color: #ffffff;
	    border-radius: 10px;
	    height: 175px;
	    width: 271px;
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
			<div class="solve_question" style="margin-top:5px; cursor:pointer;"onclick="location.href='/mylittletest/mytest'"><span>내 문제 풀기</span></div>
			<div class="solve_all" style="margin-top:15px; cursor:pointer;" onclick="location.href='/mylittletest/allcategory'"><span style="font-size:12px;">문제 전체 보기</span></div>
		</div>
		<div class="today_box">
			<div class="today_question"><span>오늘 본 문제수 </span></div>
    <div class="today_count"><span style="font-size:20px;">${questionVO.todayNoteViewInCategory}</span></div>
		</div>
		<div class="modify_btn" id="modify_btn">수정 </div>
		<div class="delete_btn" data-note-no="${questionVO.noteVO.noteNo}" >비활성화</div>
		<div class="showless_btn" data-note-no="${questionVO.noteVO.noteNo}">덜보기</div>
		<div class="modify_btn" onclick="goBack();" >돌아가기</div>
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
						    <div class="like ${questionVO.isFavorite? 'liked':'' }" id="like" data-note-no="${questionVO.noteVO.noteNo}" onclick="toggleLike(this)">❤</div>
						    <div class="like_count" style="margin-left:10px; height:fit-content;"><span>${questionVO.favoriteCount }</span></div>
						</div>
						<script>
						function toggleLike(btn) {
						    const noteNo = btn.dataset.noteNo; // noteNo를 버튼의 data-note-no 속성에서 가져옴
						    const likeCountSpan = btn.nextElementSibling.querySelector('span');
						    let requestType = btn.classList.contains('liked') ? 0 : 1;

						    fetch('/mylittletest/favorite', {
						        method: 'POST',
						        headers: {
						            'Content-Type': 'application/json',
						            [csrfHeader]: csrfToken
						        },
						        body: JSON.stringify({
						            noteNo: noteNo,
						            requestType: requestType,
						            targetType: 1
						        })
						    })
						    .then(response => response.json())
						    .then(data => {
						        if (data.status === 'insert_success') {
						            if (requestType === 1) {
						                likeCountSpan.textContent = parseInt(likeCountSpan.textContent) + 1;
						                btn.classList.add('liked');
						            } else {
						                likeCountSpan.textContent = parseInt(likeCountSpan.textContent) - 1;
						                btn.classList.remove('liked');
						            }
						        } else if (data.status === 'login_needed') {
						            window.location.href = data.url;
						        } else {
						            console.error('Request failed:', data);
						        }
						    })
						    .catch(error => {
						        console.error('Error:', error);
						    });
						}
						</script>
						<div class="share" id="sharebtn">📤공유하기</div>
					</div>
					<div class="media">
						<div>
						    <!-- Your existing HTML content here... -->
						    <div class="check">
					            <div class="success_btn ${questionVO.answerType == 2 ? 'clicked' : ''}" id="btnO" onclick="handleClick(2)">O</div>
					            <div class="success_btn ${questionVO.answerType == 1 ? 'clicked2' : ''}" id="btnX" onclick="handleClick(1)">X</div>
						    </div>
						    <!-- More of your existing HTML content... -->
						</div>
					</div>
				</div>	
			</div>
			<div class="answer-container">
				<div class="answer" id="answer">🔒정답보기</div><div class="showanswer" id="showanswer">${questionVO.noteVO.noteAnswer}</div>
			</div>
			<div class="commentary-container">
				<div class="commentary" id="commentary">🔒해설보기</div><div class="showcommentary" id="showcommentary">${questionVO.noteVO.noteCommentary}</div>
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
				<div class="replyinput">
					<input type="text" class="reply_input" id="replyContent" name="replyContent" placeholder="댓글을 입력해주세요">
				</div>
				<div class="replybtn">
					<button type="submit" class="reply_btn">작성</button>
				</div>
			</div>
		</form>
		</c:if>
		<div class="reply">
			<c:forEach var="reply" items="${ questionVO.replies }">
			    <c:choose>
			        <c:when test="${ userVO != null and userVO.userNo == reply.userNo }">
			            <div class="reply_show" id="reply_show_${reply.replyNo}">
			                <div class="reply_profiles" style="font-size:30px;">${ reply.nickname}</div>
			                <div class="replynote">
			                    ${reply.replyContent}
			                    <div class="reply_date" id="reply_report">
			                        <span>${(reply.updatedAt == null) ? reply.createdAt : reply.updatedAt }</span>
			                    </div>
			                </div>
			                <div class="replycheck">
			                    <div class="reply_modify_btn" onclick="toggleEditForm(${reply.replyNo})">수정</div>
			                    <div class="reply_modify_btn">삭제</div>
			                </div>
			            </div>
			            <form id="editReplyForm_${reply.replyNo}" class="editReplyForm" style="display:none;" method="post" action="/mylittletest/replyModify">
			                <input type="hidden" name="noteNo" value="${questionVO.noteVO.noteNo}">
			                <input type="hidden" name="replyNo" value="${reply.replyNo}">
		                	    	<input type="hidden" name="menuPath" id="menuPath" value="${menuName}">
                	    		    	<input type="hidden" name="categoryTitle" id="categoryTitle" value="${questionVO.categoryVO.categoryTitle}">
			                <sec:csrfInput />
			                <input type="text" class="reply_input" name="replyContent" value="${reply.replyContent}">
			                <div class="modifycheck"style="display:flex;">
			                    <button type="submit" class="reply_btn" style="font-size:0.5rem">수정 완료</button>
			                    <button type="button" class="reply_cancel_btn" onclick="toggleEditForm(${reply.replyNo})">취소</button>
			                </div>
			            </form>
			        </c:when>
			        <c:otherwise>
			            <div class="reply_show">
			                <div class="reply_profiles" style="font-size:30px;">${ reply.nickname}</div>
			                <div class="replynote">
			                    ${reply.replyContent}
			                    <div class="reply_date" id="reply_date">
			                        <span>${(reply.updatedAt == null) ? reply.createdAt : reply.updatedAt }</span>
			                    </div>
			                </div>
			                <div id="reply_report" onclick="reportReply(${reply.replyNo})">
			                		 🚨
			                </div>
			            </div>
			        </c:otherwise>
			    </c:choose>
			</c:forEach>
		</div>
		<script type="text/javascript">
		function toggleEditForm(replyNo) {
		    var replyShow = document.getElementById('reply_show_' + replyNo);
		    var editForm = document.getElementById('editReplyForm_' + replyNo);
		    if (replyShow.style.display === 'none') {
		        replyShow.style.display = 'block';
		        editForm.style.display = 'none';
		    } else {
		        replyShow.style.display = 'none';
		        editForm.style.display = 'block';
		    }
		}
		
		function reportReply(replyNo) {
		    const targetType = 3;
		    const requestType = -2;

		    const data = {
		        replyNo: replyNo,
		        requestType: requestType,
		        targetType: targetType
		    };

		    // Send the POST request
		    fetch('/mylittletest/replyBlock', {
		        method: 'POST',
		        headers: {
		            'Content-Type': 'application/json',
	                [csrfHeader]: csrfToken
		        },
		        body: JSON.stringify(data)
		    })
		    .then(response => response.json())
		    .then(data => {
		        if (data.status === 'insert_success') {
		            alert('댓글이 성공적으로 차단되었습니다.');
		            window.location.reload();
		        } else if (data.status === 'login_needed') {
		            alert('로그인이 필요합니다.');
		            window.location.href = data.url;
		        } else {
		            alert('요청 처리 중 오류가 발생했습니다.');
		        }
		    })
		    .catch(error => {
		        console.error('Error:', error);
		        alert('요청 처리 중 오류가 발생했습니다.');
		    });
		}
		
        function handleClick(answerType) {
            const noteNo = document.getElementById("noteNo").value; // 숨겨진 필드에서 값 가져오기
            const data = {
                    noteNo: parseInt(noteNo, 10),
                    answerType: parseInt(answerType, 10)
                };
        	
            fetch('/mylittletest/answer', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
	                [csrfHeader]: csrfToken
                },
                body: JSON.stringify(data)
            })
            .then(response => response.json())
            .then(data => {
                if (data.status === 'success') {
                    updateButtonStyles(answerType);
                } else if (data.status === 'login_needed') {
                    window.location.href = data.url;
                } else {
                    console.error('Failed to submit answer:', data);
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }

        function updateButtonStyles(answerType) {
            const btnO = document.getElementById('btnO');
            const btnX = document.getElementById('btnX');

            if (answerType === 2) {
                btnO.classList.add('clicked');
                btnX.classList.remove('clicked2');
            } else if (answerType === 1) {
                btnX.classList.add('clicked2');
                btnO.classList.remove('clicked');
            }
        }
		</script>
	</div>
</div>

<jsp:include page="./include/tail.jsp"></jsp:include>