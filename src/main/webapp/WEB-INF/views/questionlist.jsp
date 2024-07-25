<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<jsp:include page="./include/head.jsp"></jsp:include>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	document.addEventListener("DOMContentLoaded", function() {
	
		  var correctRatioStr = '${category.correctRatio}';
	        console.log('Raw correctRatio value:', correctRatioStr);

	        // 문자열을 숫자로 변환
	        var correctRatio = parseFloat(correctRatioStr);
	        console.log('Parsed correctRatio value:', correctRatio);

	        // 올바른 값이 아닌 경우, 'N/A'로 처리
	        if (!isNaN(correctRatio)) {
	            var correctRatioDisplay = (correctRatio * 100).toFixed(2) + '%';
	            document.getElementById('correctRatioDisplay').textContent = correctRatioDisplay;
	        } else {
	            document.getElementById('correctRatioDisplay').textContent = 'N/A';
	        }
        
        // 동적으로 리스트가 추가될 경우에 대비하여 슬라이더 기능을 설정하는 함수
        function setupListSlider() {
            var listItems = document.querySelector('.list_items');
            if (!listItems) return; // 요소가 없으면 함수 종료

            var isMouseDown = false;
            var startX, scrollLeft;

            listItems.addEventListener('mousedown', function(e) {
                isMouseDown = true;
                startX = e.pageX - listItems.offsetLeft;
                scrollLeft = listItems.scrollLeft;
            });

            listItems.addEventListener('mouseleave', function() {
                isMouseDown = false;
            });

            listItems.addEventListener('mouseup', function() {
                isMouseDown = false;
            });

            listItems.addEventListener('mousemove', function(e) {
                if (!isMouseDown) return;
                e.preventDefault();
                var x = e.pageX - listItems.offsetLeft;
                var walk = (x - startX) * 1.2; // 스크롤 속도 조절
                listItems.scrollLeft = scrollLeft - walk;
            });
        }

        // 문서가 로드되면 슬라이더 기능 설정
        setupListSlider();
        
        // 북마크 버튼 애니메이션
        var bookmarks = document.querySelectorAll('.bookmark');

        bookmarks.forEach(function(bookmark) {
            bookmark.addEventListener('click', function() {
                bookmark.classList.toggle('liked');
            });
        });
        // 좋아요 버튼 애니메이션
        const likeBtns = document.querySelectorAll('.question_like');

        likeBtns.forEach(function(Btn) {
        	Btn.addEventListener('click', function() {
                this.classList.toggle('liked');
            });
        });
        // 화살표 슬라이더
        var swiper = new Swiper(".swiper-container", {
		      slidesPerView: 1,
		      spaceBetween: 0, // 슬라이드 간의 간격 설정
		      initialSlide: 0,
		      observer: true, // 변경된 슬라이드 감지
		      observeParents: true, // 변경된 슬라이드 감지
		      pagination: {
		        el: ".swiper-pagination",
		        clickable: true,
		      },
		      navigation: {
		        nextEl: ".swiper-button-next",
		        prevEl: ".swiper-button-prev",
		      },
		      breakpoints: {
		          640: {
		              slidesPerView: 1,
		              spaceBetween: 20
		          },
		          768: {
		              slidesPerView: 2,
		              spaceBetween: 40
		          },
		          1024: {
		              slidesPerView: 4,
		              spaceBetween: 50
		          }
		      }
		    });
        
        var goToFirstButton = document.querySelector('.goto');
        goToFirstButton.addEventListener('click', function () {
        	swiper.slideTo(0);  // 첫 번째 슬라이드로 이동
        });
    
	});
	function goBack() {
	    window.history.back();
	}
</script>
<style>
		.maincontainer{
			width: 1600px;
			background-color: #474747;
			border-radius: 2rem;
			height: 800px;
			min-height: 800px;
			min-width:800px;
		  	display: inline-flex;
		    align-items: center;
		    justify-content: center;
		    
		}
		.container{
			display: inline-flex;
	    	width: 850px;
	    	flex-direction:column;
	    	height: calc(90vh - 8.8rem);
	    	justify-content:center;
			align-items:center;
		}
		.list_container{
			display:flex;
			gap:150px;		
			width:fit-content;	
		}
	
		.search_box{
			margin-left: 32px;
		}
		.search_items {
			height: 20px;
			width: 400px;
			background: #333333;
			border-radius: 20px;
			padding: 10px;
		}
		.search_input{
			border:none;
			background:none;
			outline:none;
			float:left;
			padding:0px;
			color:#ffffff;
			font-size:16px;
			width:200px;
		}
		.search_button {
			color:#f9deec;
			float:right;
			width:40px;
			height:100%;
			border-radius: 50%;
			background: #333333;
			border:none;
			font-size:16px;
			display:flex;
			justify-content:center;
			align-items:center;
			
		}
		.order_box{
			margin-left:40px;
		}
		.order_dropdown,.order_question{
			display: inline-flex;
		    flex-direction: column;
		    position: relative;
		    margin-left:1rem;
		    margin-right:1rem;
		}
		.order_main{
			color: #ffffff;
		    display: inline-flex;
		    align-items: center;
		    justify-content: center;
		    width:7rem;
		    background-color: #333333;
		    padding: 0.5rem 0.5rem;
		    border-radius: 5px 5px 5px 5px;
		    cursor: pointer;
		}
		.list_order {
			display:none;
		    flex-direction: column;
		    width:100%;
		    overflow:hidden;
		    background-color: #333333;
		    border-radius: 5px 5px 5px 5px;
		    position: absolute;
		    top: 100%;
		    left: 0;
		    z-index: 10;
		    text-align:center;
		}
		.list_order.show {
        display:block;
	    }
	    .order_option {
	        color: #ffffff;
	        padding: 0.5rem;
	        cursor: pointer;
	        boder-bottom:1px dotted #ffffff;
	    }
	    .order_option:hover {
	        background-color: #555555;
	    }
		.list_items {
        height: fit-content;
        margin: 0;
        padding: 0;
        display: flex;
        align-items: center;
        overflow-x: auto;
        white-space: nowrap;
        overflow: hidden;
        padding-bottom: 20px;
        width:770px;
        list-style-type:none;
        }

	   .list_item {
	        display: inline-block;
	        width: calc(800px / 6);
	        min-width: 150px;
	        margin-right: 10px;
	        vertical-align: top;
	        white-space: normal;
	        text-align: center;
		    background-color: #333333;
		    color: #ffffff;
		    border-radius: 2rem;
		    padding: 0.75rem 2rem;
		    margin: 0.5rem 1rem 0.5rem 0;
		    border: none;
		    box-shadow: 0.2rem 0.2rem 0.4rem #696969, -0.2rem -0.2rem 0.4rem #696969;
	    }
	
	    .list {
	        box-sizing: border-box;
	        display: inline-flex;
	        align-items: center;
	        justify-content: center;
	        overflow: hidden;
	        white-space: nowrap;
	        height: 40px;
	        font-size: 15px;
	        border-radius: 2rem;
	        padding: 0.75rem 2rem;
	        margin: 0.5rem 1rem 0.5rem 0;
	        border: none;
	        box-shadow: 0.2rem 0.2rem 0.4rem #696969, -0.2rem -0.2rem 0.4rem #696969;
	        background-color: #333333;
	        position: relative;
	        cursor: grab;
	    }
		.list:hover{
			background-color:white;
			 color: black;
            transform: scale(1.0);
		}
		.list:clicked{
			background-color:white;
			 color: black;
            transform: scale(1.1);
		}
	    .list_items .list:active {
	        cursor: grabbing;
	    }
	    .swiper-container{
	    	width:800px;
	    	height:680px;
	    	display:inline-flex;
	    	overflow:hidden;
	    	align-items: center;
		    justify-content: center;
	    }
	    .swiper-wrapper{
	    	margin-left:10px;
	    }
		.swiper-slide{
		    display: flex;
		    gap: 0.5rem;
		    flex-wrap: wrap;
		    height: 600px;
		    align-items: center;
	        justify-content: center;
		}
		/* 화살표 위치 변경 */
		.swiper-button-next, .swiper-button-prev {
		    position: absolute;
		    top: 46%;
		    bottom:50%;
		    margin-top: -22px;
		    z-index: 10;
		    cursor: pointer;
		}

		/* 화살표 바깥쪽으로 배치 */
		.swiper-button-next {
		    right: 0px; /* 화살표를 오른쪽 바깥쪽으로 이동 */
		}
		
		.swiper-button-prev {
		    left: 0px; /* 화살표를 왼쪽 바깥쪽으로 이동 */
		}
		
		/* 화살표 색상 및 크기 변경 */
		.swiper-button-next::after, .swiper-button-prev::after {
		    font-size: 20px; /* 화살표 크기 */
		}
		
		/* 화살표 배경색 및 크기 변경 */
		.swiper-button-next, .swiper-button-prev {
		    width: 36px; /* 화살표 크기 */
		    height: 17px; /* 화살표 크기 */
		    color:black;
		}
		
		.goto,.back{
			color: #ffffff;
		    display: inline-flex;
		    align-items: center;
		    justify-content: center;
		    width:7rem;
		    background-color: #333333;
		    padding: 0.5rem 0.5rem;
		    border-radius: 5px 5px 5px 5px;
		    cursor: pointer;
		}
		
		.question_box{
            width:45%;
			height:fit-content;
			 align-items: center;
	        justify-content: center;
	        cursor:pointer;
            
			}
			
		.question_item{
			margin-top:10px;
			width:300px;
	     	height:200px;
	     	background-color:#333333;
	     	color:#ffffff;
            padding: 10px;
            border-radius: 10px;
            border: none;
			box-shadow: 0.2rem 0.2rem 0.4rem #696969,  -0.2rem -0.2rem 0.4rem #696969;
			position: relative;
			
			
			
		}
		
		.question_title{
			margin-right:auto;
			background-position: center;
	    	background-size: cover;
            color: #ffffff;
            font-size:25px;
            font-weight: bold;
            width:fit-content;
            position: absolute;
			top: 50%;
			left: 50%;
			transform: translate(-50%, -50%);
			cursor:pointer;
		}
		.bookmark{
			margin-top: 5px;
			background-position: center;
	    	background-size: cover;
	    	height:fit-content;
	    	margin-left:5px;
	    	float:right;
	    	cursor: pointer;
            font-size: 20px;
            transition: transform 0.3s, color 0.3s;
	   
		}
		 .bookmark.liked {
            color: yellow;
            transform: scale(1.5);
        }
		.count_item{
			margin-top: 20px;
			display: flex;
    		flex-wrap: wrap;
    		align-content:flex-end;
    		width:fit-content;
		}
		 .item {
		 	margin-right:auto;
            margin-top: 10px;
            font-size: 1rem;
            color: #666;
            flex:1 1 45%;
            display:flex;
            width:fit-content;
		  }
		  .question_mini{
		  	width:360px;
		  	margin-top:0.5rem;
		  	display:inline-flex;
		  }
		  .question_mbox{
		  	display:flex;
		  	justify-content: flex-start;
		  	width:310px;
		  }
		  .question_mtitle{
		  	margin-right: auto;
		   	background-position: center;
    		background-size: cover;
    		color:#ffffff;
    		cursor: pointer;
            font-size: 14px;
		  }
		  .question_answer{
		  	background-position: center;
    		background-size: cover;
    		float:right;
    		font-size:12px;
		  }
		  .question_count{
		  	width:310px;
		  	margin-top:0.5rem;
		  	display:inline-flex;
		  	gap:10px;
		  }
		  .question_report{
		  	float:right;
		  	cursor:pointer;
		  }
		  .count_box{
		 	 display:flex;
		 	 justify-content: flex-start;
			 width:100%;
			 gap:5px;
		  }
		  .question_like{
		  	cursor:pointer;
		  }
		  .question_like.liked{
			 color: red;
	         transform: scale(1.2);
		  }
		  .likebox{
		  	display:flex;
		  }
		 .question_question{
		 	cursor:pointer;
		 }
	</style>

	
	<!-- 컨텐츠 영역  -->
	<div class = maincontainer>
		<div class="container">
			<div class="search_box">
				<div class="list_container">
					<div class="search_area">
						<form class="search_items" method="post" action="/mylittletest/search">
					    	<sec:csrfInput/>
						    <input class="search_input" type="text" name="searchInput" placeholder="Search" spellcheck="false">
						    <input type="hidden" name="urlPath" id="urlPath">
						    <button class="search_button" type="submit">🔍</button>
						</form>
						<script>
						    var urlPathInput = document.getElementById("urlPath");
							var currentUrl = window.location.pathname;
							urlPathInput.value = currentUrl;
						</script>
	       			</div>
	       			<div class="back" onclick="goBack();" style="align-items:flex-end">돌아가기</div>
	       		</div>
		          <div class="list_shadow" style="width: 67%; max-width:67%; position:relative;">
			            <ul class="list_items">
			              <c:forEach  var="categorylists" items="${recent_categories}">
				                	<li class="list1">
				                    	<div class="list" onclick="location.href='/mylittletest/category/${categorylist.categoryNo}">${categorylists.categoryTitle}</div>
				                	</li>
			             </c:forEach>
			            </ul>
			      </div>
			  </div>
			  <!-- 슬라이드 할 요소 -->
				<div class="swiper-container">
				    <div class="swiper-wrapper">
				        <!-- 카테고리 리스트를 4개씩 나누어 슬라이드를 생성합니다. -->
				        <c:forEach items="${list}" var="categoryList" varStatus="outerStatus">
				            <c:if test="${outerStatus.index % 4 == 0}">
				                <div class="swiper-slide">
				            </c:if>
				            <!-- 카테고리 항목을 슬라이드에 추가합니다. -->
				            <c:forEach items="${categoryList}" var="category">
				                <div class="question_box">
				                    <div class="question_item">
				                        <div class="bookmark">★</div>
				                        <div class="question_title" onclick="location.href='/mylittletest/${ menuName }/category/${category.categoryTitle}'">${category.categoryTitle}</div>
				                    </div>
				                    <div class="question_mini">
				                        <div class="question_mbox">
				                            <div class="question_mtitle" onclick="location.href='/mylittletest/${ menuName }/category/${category.categoryTitle}'">${category.categoryTitle}</div>
				                            <div class="question_answer">나의 정답률 <span id="correctRatioDisplay">${category.correctRatio * 100}%</span></div>
				                        </div>
				                    </div>
				                    <div class="question_count">
				                        <div class="count_box">
				                            <div class="likebox">
				                                <div class="question_like" id="like" style="color:red;">❤</div>
				                                <div style="margin-left:10px;">${category.favoriteCount}</div>
				                            </div>
				                            <div class="question_question" onclick="location.href='/mylittletest/${ menuName }/category/${category.categoryTitle}'">📚 ${category.noteCount}문제</div>
				                            <div class="question_person">🧑 ${category.authorCount}출제자</div>
				                        </div>
				                    </div>
				                </div>
				            </c:forEach>
				            <c:if test="${(outerStatus.index + 1) % 4 == 0 || outerStatus.index == fn:length(list) - 1}">
				                </div>
				            </c:if>
				        </c:forEach>
				    </div>
				    <!-- 네비게이션 버튼 -->
				    <div class="swiper-button-next"></div><!-- 다음 버튼 (오른쪽에 있는 버튼) -->
				    <div class="swiper-button-prev"></div><!-- 이전 버튼 -->
				</div>
				<div style="gap:30px; display:flex;">
					<div class="goto">처음으로</div>
				</div>
		</div>
	</div>
<!-- 컨텐츠 영역  -->
<jsp:include page="./include/tail.jsp"></jsp:include>