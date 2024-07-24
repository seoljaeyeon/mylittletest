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

        document.getElementById('sortSelect').addEventListener('change', function() {
            const sort = document.getElementById('sortSelect').value;

            const menuPath = "${menuPath}";
            const page = parseInt("${page}", 10);
            const limit = parseInt("${limit}", 10);
            const categoryTitle = "${categoryTitle}";
            const searchType = parseInt("${searchType}", 10);

            const requestData = {
                menuPath: menuPath,
                sort: sort,
                page: page,
                limit: limit,
                categoryTitle: categoryTitle,
                searchType: searchType
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
        });

        document.getElementById('searchForm').addEventListener('submit', function(event) {
            event.preventDefault();

            const searchInput = document.getElementById('searchInput').value;
            const sort = document.getElementById('sortSelect').value;

            const menuPath = "${menuPath}";
            const page = parseInt("${page}", 10);
            const limit = parseInt("${limit}", 10);
            const categoryTitle = "${categoryTitle}";
            const searchType = parseInt("${searchType}", 10);

            const requestData = {
                menuPath: menuPath,
                sort: sort,
                page: page,
                limit: limit,
                categoryTitle: categoryTitle,
                searchInput: searchInput,
                searchType: searchType
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
        });

        $('#mytest, #reviewmytest, #correctmytest, #allcategory, #todayquestions, #bookmarkquestions').on('click', function() {
            handleMenuClick(this.id);
        });

        $('a.category-link').on('click', function(event) {
            event.preventDefault();
            const categoryTitle = $(this).data('category');
            handleCategoryClick(categoryTitle);
        });

        function handleMenuClick(menuPath) {
            const requestData = {
                menuPath: menuPath
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

a {
	color: white;
}

a:visited {
	color: white;
}

a:hover {
	color: white;
}

a:active {
	color: white;
}

a {
	text-decoration-line: none;
}

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
		<div class="notelist ${menuPath == 'mytest' ? 'active' : ''}"
			style="display: inline-block; font-size: 15px; font-weight: bolder; margin-left: 20px;"
			id="mytest">내 문제</div>
		<div class="notelist ${menuPath == 'reviewmytest' ? 'active' : ''}"
			style="display: inline-block; font-size: 15px; font-weight: bolder; margin-left: 20px;"
			id="reviewmytest">맞춘 문제</div>
		<div class="notelist ${menuPath == 'correctmytest' ? 'active' : ''}"
			style="display: inline-block; font-size: 15px; font-weight: bolder; margin-left: 20px;"
			id="correctmytest">오답 문제</div>
		<div class="notelist ${menuPath == 'allcategory' ? 'active' : ''}"
			style="display: inline-block; font-size: 15px; font-weight: bolder; margin-left: 20px;"
			id="allcategory">카테고리 별 문제</div>
		<div class="notelist ${menuPath == 'todayquestions' ? 'active' : ''}"
			style="display: inline-block; font-size: 15px; font-weight: bolder; margin-left: 20px;"
			id="todayquestions">오늘 조회한 문제</div>
		<div
			class="notelist ${menuPath == 'bookmarkquestions' ? 'active' : ''}"
			style="display: inline-block; font-size: 15px; font-weight: bolder; margin-left: 20px;"
			id="bookmarkquestions">북마크 & 좋아요 문제</div>
		<select id="sortSelect">
			<option value="createdAt"
				<c:out value="${sort == 'createdAt' ? 'selected' : ''}"/>>최신순</option>
			<option value="reply"
				<c:out value="${sort == 'reply' ? 'selected' : ''}"/>>댓글순</option>
			<option value="favorite"
				<c:out value="${sort == 'favorite' ? 'selected' : ''}"/>>좋아요순</option>
		</select>
		<div align="right" style="display: inline-block;">
			<form class="search-bar" id="searchForm">
				<sec:csrfInput />
				<input type="text" placeholder="Search" spellcheck="false"
					id="searchInput">
				<button>🔍</button>
			</form>
		</div>
	</div>
	<br>
	<br>
	<div id="container">
		<div id="my_note_section" class="content-section active">
			<table style="width: 90%; margin-left: 50px; margin-right: 50px;">
				<tr style="height: 50px">
					<td>문제 분류</td>
					<td class="announcement_title">내용</td>
					<td>시간</td>
					<td class="like_no">❤</td>
					<td>💬</td>
				</tr>
				<c:forEach var="myquestion" items="${result}">
					<tr style="height: 50px">
                        <td><a href="javascript:void(0);" class="category-link" data-category="${ myquestion.categoryTitle }">${ myquestion.categoryTitle }</a></td>
						<td><a href="/mylittletest/${menuPath}/category/${myquestion.categoryTitle}/${myquestion.noteNo}">${ myquestion.noteTitle }</a></td>
						<td>${ myquestion.noteCreatedAt }</td>
						<td>${ myquestion.favorite_count }</td>
						<td>${ myquestion.reply_count }</td>
					</tr>
				</c:forEach>
				<tr style="height: 50px">
					<td style="text-align: center;" colspan="99"><c:if
							test="${startBK > 10 }">
							<a href="index.do?page=${startBK - 1}">◀</a>
						</c:if> <c:forEach var="page" begin="${startBK}" end="${endBK}">
							<c:if test="${ page == pageno }">
								<a style="color: red; font-weight: bold;"
									href="index.do?page=${ page }">${ page }</a>
							</c:if>
							<c:if test="${ page != pageno }">
								<a href="index.do?page=${ page }">${ page }</a>
							</c:if>
						</c:forEach> <c:if test="${endBK <  maxpage }">
							<a href="index.do?page=${endBK + 1}">▶</a>
						</c:if></td>
				</tr>
			</table>
		</div>
	</div>
</div>
<jsp:include page="./include/tail.jsp"></jsp:include>


