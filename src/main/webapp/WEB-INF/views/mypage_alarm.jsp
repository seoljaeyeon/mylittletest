<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<jsp:include page="./include/head.jsp"></jsp:include>
<script>
	document.addEventListener("DOMContentLoaded", function() {
});
</script>
<style>
.mypage_container {
	display: flex;
	width: fit-content;
	background-color: #474747;
	height: 750px;
	gap: 80px;
	justify-content: center;
	align-items: center;
	min-width:800px;
	margin-left:100px;
}

.profile-img {
	width: 130px;
	height: 130px;
	border-radius: 50%;
	margin-top: 10px;
	display: flex;
	justify-content: center;
	align-items: center;
	overflow: hidden;
}
.profile-img img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.profile_box {
	height: fit-content;
	width: 300px;
}

.picture {
	text-align: center;
	font-size: 20px;
	width: 298px;
	height: 150px;
}

.profile {
	text-align: center;
	border: 1px solid black;
	border-radius: 30px;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	gap:10px;
}

.change {
	color: #cccccc;
	font-size: 0.8rem;
	cursor: pointer;
}

.nickname, .email {
	margin-top: 20px;
}

.password {
	background-color: #333333;
	color: #ffffff;
	border-radius: 10px;
	height: 3rem;
	width: 150px;
	justify-content: center;
	align-items: center;
	font-size: 1rem;
	text-align: center;
	display: flex;
	cursor: pointer;
	border: none;
	margin-top: 20px;
}

.sub_menu {
	text-align: center;
	margin-top: 80px;
}

.bookmark_list_btn, .alarm_list_btn {
	background-color: #333333;
	color: #ffffff;
	height: 4rem;
	width: 300px;
	margin-top: 30px;
	cursor: pointer;
	justify-content: center;
	align-items: center;
	display: flex;
	border-radius: 20px;
}

.bookmark_list_btn:hover, .alarm_list_btn:hover {
	background-color: #ffffff;
	color: #000000;
}

.alarm_container {
	display: flex;
	width: 1000px;
	gap: 30px;
}

.alarm_list {
	border: 1px solid black;
	width: 950px;
	border-radius: 20px;
}

.alarm_title {
	margin-left: 20px;
	font-size: 30px;
	font-weight: bold;
	margin-top: 20px;
}

.alarm_main {
	margin: 20px;
	width: 900px;
}

.alarm {
	display: flex;
	border-bottom: 1px solid #cccccc;
}

.sub {
	height: 25px;
	margin-top: 10px;
	margin-bottom: 8px;
}

.change {
	cursor: pointer;
}
/* #profilePicture {
	justify-content: center;
    align-items: center;  
} */
</style>
<div class="mypage_container">
	<div class="profile_box">
		<input type="file" id="fileInput" style="display: none;" multiple>
		<div class="profile">
			<div class="profile-img" id="profilePicture">
				<img src="${profileURL}" alt="Profile Picture" />
			</div>
			<div class="change" id="picture_btn">변경하기</div>

			<script>
			    const profilePicture = document.getElementById("profilePicture");
			    const pictureBtn = document.getElementById("picture_btn");
			    const fileInput = document.getElementById("fileInput");
			
			    pictureBtn.addEventListener('click', () => {
			        fileInput.click();
			    });
			
			    fileInput.addEventListener('change', () => {
			        const file = fileInput.files[0];
			        if (file) {
			            const formData = new FormData();
			            formData.append('file', file);
			            formData.append('userNo', ${userVO.userNo}); // userNo를 여기에 추가
			
			            fetch('/mylittletest/file/upload', {
			                method: 'POST',
			                body: formData,
			                headers: {
			                    'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content') // CSRF 토큰 추가
			                }
			            })
			            .then(response => response.json())
			            .then(data => {
			                if (data != null) {
			                    alert("File uploaded successfully");
			                    profilePicture.querySelector('img').src = '/mylittletest/uploads/'+data[0].savedName; // 파일 업로드 후 이미지 경로 변경
			                } else {
			                    alert("File upload failed");
			                }
			            })
			            .catch(error => {
			                console.error('Error:', error);
			                alert("File upload error");
			            });
			        }
			    });
			</script>

			<div class="nickname">
				닉네임<br>
				${ userVO.nickname }
			</div>
			<div class="email">
				이메일<br>
				${ userVO.email }
			</div>
		</div>
		<div class="sub_menu">
			<div class="bookmark_list_btn"
				onclick="location.href='/mylittletest/mypage/bookmark'">좋아요 &
				북마크 목록</div>
			<div class="alarm_list_btn" style="margin-top: 50px;"
				onclick="location.href='/mylittletest/mypage/alarm'">알림 목록</div>
		</div>
	</div>
	<div class="alarm_container">
		<div class="alarm_list">
			<div class="alarm_title">알림 목록</div>
			<c:if test="${AlarmList == null }">
				<div>최근 알림이 없습니다.</div>
			</c:if>
			<c:if test="${AlarmList != null}">
				<div class="alarm_main">
					<div class="alarm">
						<div class="sub"
							style="width: 150px; font-size: 20px; font-weight: bold;">분류</div>
						<div class="sub"
							style="width: 450px; font-size: 20px; font-weight: bold;">알림
							내용</div>
						<div class="sub"
							style="width: 300px; font-size: 20px; font-weight: bold;">시간</div>
					</div>
					<c:forEach var="alarm" items="${ AlarmList }">
						<div class="alarm">
							<div class="sub" style="width: 150px; font-size: 18px;">
								<c:choose>
									<c:when test="${alarm.alarmType == 1}">
            							좋아요
        							</c:when>
									<c:otherwise>
            							댓글
        							</c:otherwise>
								</c:choose>
							</div>
							<div class="sub"
								style="width: 450px; overflow: hidden; font-size: 18px; cursor:pointer;" onclick="location.href='/mylittletest/mytest/category/${alarm.categoryTitle}/${alarm.noteNo}'">${alarm.nickname}님이 내 게시물에 ${alarm.alarmType == 1 ? '좋아요를 눌렀습니다.' : '댓글을 남겼습니다.' }</div>
							<div class="sub" style="width: 300px; font-size: 18px;">${alarm.createdAt }</div>
						</div>
					</c:forEach>
				</div>
				<div class="page" style="text-align: center;">
					<c:if test="${startBK > 10 }">
						<a href="index.do?page=${startBK - 1}">◀</a>
					</c:if>
					<c:forEach var="page" begin="${startBK}" end="${endBK}">
						<c:if test="${ page == pageno }">
							<a style="color: red; font-weight: bold;"
								href="index.do?page=${ page }">${ page }</a>
						</c:if>
						<c:if test="${ page != pageno }">
							<a href="index.do?page=${ page }">${ page }</a>
						</c:if>
					</c:forEach>
					<c:if test="${endBK <  maxpage }">
						<a href="index.do?page=${endBK + 1}">▶</a>
					</c:if>
				</div>
			</c:if>
		</div>
	</div>
</div>


<jsp:include page="./include/tail.jsp"></jsp:include>