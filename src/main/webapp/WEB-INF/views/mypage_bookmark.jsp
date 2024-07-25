<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
    uri="http://www.springframework.org/security/tags"%>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<jsp:include page="./include/head.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
    	 const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
         const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
        
         $('a.category-link').on('click', function(event) {
             event.preventDefault();
             const categoryTitle = $(this).data('category');
             handleCategoryClick(categoryTitle);
         });
		
         function handleCategoryClick(categoryTitle) {
             const requestData = {
                 menuPath: 'allcategory',
                 categoryTitle: categoryTitle
             };

             fetch('/mylittletest/notelist', {
                 method: 'POST',
                 headers: {
                     'Content-Type': 'application/json',
                     [csrfHeader]: csrfToken
                 },
                 body: JSON.stringify(requestData)
             })
             .then(response => response.json())
             .then(data => {
                 if (data.status === 'success') {
                     window.location.href = data.url;
                 } else if (data.status === 'login_needed') {
                     window.location.href = data.url;
                 } else {
                     console.error('Failed to fetch note list:', data);
                 }
             })
             .catch(error => {
                 console.error('Error:', error);
             });
         }
});
</script>
<style>
.mypage_container {
	display: inline-flex;
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
	appearance: none;
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
	margin-top: 30px;
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

.bookmark_container {
	display: flex;
	width: 1000px;
	gap: 30px;
}

.bookmark_list {
	border: 1px solid black;
	margin: 20px;
	width: 950px;
	margin-top: 0px;
	border-radius: 20px;
}

.bookmark_header {
	display: flex;
	gap: 150px;
}

.bookmark_title {
	margin-left: 20px;
	font-size: 30px;
	font-weight: bold;
	margin-top: 20px;
}

.search_area {
	margin-top: 20px;
}

.search_items {
	height: 20px;
	width: 400px;
	background: #474747;
	border-radius: 20px;
	padding: 10px;
	border: 1px solid #cccccc;
}

.search_input {
	border: none;
	background: none;
	outline: none;
	float: left;
	padding: 0px;
	color: #white;
	font-size: 16px;
	width: 200px;
}

.search_button {
	color: #474747;
	float: right;
	width: 40px;
	height: 100%;
	border-radius: 50%;
	background: #474747;
	border: none;
	font-size: 16px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.bookmark_btn {
	display: flex;
	gap: 10px;
	height: 40px;
	margin-top: 35px;
	margin-left: 20px;
}

.all_btn, .check_all {
	appearance: none;
	background-color: #333333;
	color: #ffffff;
	border-radius: 10px;
	height: 50px;
	width: 130px;
	justify-content: center;
	align-items: center;
	font-size: 17px;
	text-align: center;
	display: flex;
	cursor: pointer;
	border: none;
	font-weight: bold;
}

.bookmark_main {
	margin: 30px;
	width: 900px;
}

.bookmark {
	display: flex;
	border-bottom: 1px solid #cccccc;
}

.bookmark_note {
	margin-top: 10px;
	display: flex;
	border-bottom: 1px solid #cccccc;
	height: 28px;
}

.change {
	cursor: pointer;
}

a{
	color:#ffffff;
	text-decoration:none;
}
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
	<div class="bookmark_container">
		<div class="bookmark_list">
			<div class="bookmark_header">
				<div class="bookmark_title">좋아요 & 북마크목록</div>
				<div class="search_area">
					<form class="search_items" method="post"
						action="/mylittletest/search">
						<sec:csrfInput />
						<input class="search_input" type="text" name="searchInput"
							placeholder="Search" spellcheck="false"> <input
							type="hidden" name="urlPath" id="urlPath">
						<button class="search_button" type="submit">🔍</button>
					</form>
					<script>
					    var urlPathInput = document.getElementById("urlPath");
						var currentUrl = window.location.pathname;
						urlPathInput.value = currentUrl;
					</script>
				</div>
			</div>
			<div class="bookmark_main">
				<div class="bookmark">
					<div class="sub"
						style="width: 150px; font-size: 20px; font-weight: bold;">유형
						분류</div>
					<div class="sub"
						style="width: 150px; font-size: 20px; font-weight: bold;">문제
						분류</div>
					<div class="sub"
						style="width: 350px; font-size: 20px; font-weight: bold;">내용</div>
					<div class="sub"
						style="width: 200px; font-size: 20px; font-weight: bold;">시간</div>
				</div>
				<c:forEach var="bookmark" items="${ list }">
					<div class="bookmark_note">
						<div class="sub" onclick="location.href='/mylittletest/bookmarkquestions'" style="width: 150px; cursor:pointer; font-size: 17px;">${ (bookmark.favoriteType == 2) ? '북마크' : '좋아요'  }</div>
						<div class="sub" style="width: 150px; font-size: 17px;"><a href="javascript:void(0);" class="category-link" data-category="${ bookmark.categoryTitle }">${ bookmark.categoryTitle}</a></div>
						<div class="sub"
							style="width: 350px; font-size: 17px; overflow: hidden"><a href="/mylittletest/bookmarkquestions/category/${bookmark.categoryTitle}/${bookmark.noteNo}">${ bookmark.bookmarkNote}</a></div>
						<div class="sub" style="width: 200px; font-size: 17px;">${ bookmark.createdAt}</div>
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
		</div>
	</div>
</div>


<jsp:include page="./include/tail.jsp"></jsp:include>