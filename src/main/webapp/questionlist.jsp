<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./include/head.jsp"></jsp:include>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
<script>
	document.addEventListener("DOMContentLoaded", function() {
        
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
		      centeredSlides: true,
		      loop: true, // 무한 루프 설정
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
		    });
        
        
        var goToFirstButton = document.querySelector('.goto');
        goToFirstButton.addEventListener('click', function () {
        	swiper.slideTo(0);  // 첫 번째 슬라이드로 이동
        });
        
		swiper.slideNext();
		
	
    
	});
	
</script>
<style>
	.maincontainer{
			width: calc(100% - 14rem);
			background-color: #474747;
			padding: 1.5rem 2rem 2rem 2rem;
			border-radius: 2rem;
			height: 800px;
			min-height:800px;
			max-height: calc(90vh - 8.8rem);
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
		    display: inline-flex;
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
		
		.goto{
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
			}
			
		.question_item{
			margin-top:10px;
			width:340px;
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
		  	width:360px;
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
		  	width:360px;
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
		  /* 팝업 창 스타일  */
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
			padding: 2rem;
			border-radius: 1rem;
			box-shadow: 0 0 1rem rgba(0, 0, 0, 0.1);
		}
		.report_title{
			color:black;
		}
		.report_list{
			display:inline-flex;
			margin-bottom:0.5rem;
		}
		.report_note{
		 	display:inline-flex;
			margin-bottom:0.5rem;
		}
		
	
		.report_btn,.delete_btn{
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
			cursor:pointer;
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
			display: block;
		} 	
	</style>
	<!--팝업 영역  -->
		<div class="popup_wrap" id="popup_report">
			<div class="report_area">
				<h1 class="report_title">신고하기</h1>
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
		            <div class="report_btn" id="reportok">신고</div>
		            <div class="delete_btn" id="reportdelete" style="background-color:#ffffff;color:black; ">취소</div>
		        </div>
			</div>
		</div>
		<!--팝업 영역  -->
	
	<!-- 컨텐츠 영역  -->
	<div class = maincontainer>
		<div class="container">
			<div class="search_box">
				<div class="list_container">
					<div class="search_area">
	            		<form class="search_items">
	               		 	<input class="search_input" type="text" placeholder="Search" spellcheck="false">
	               		 	<button class="search_button">🔍</button>
	            		</form>
	       			</div>
	       			<div class="order_box">
			       		<div class="order_question">
			            	<div class="order_main">
			                	<span style="font-weight:bold" id="questionDisplay">모든 문제</span>
			            	</div>
							<div class="list_order">   
			                	<div class="order_option">모든문제</div>
			                	<div class="order_option">내문제</div>
			                	<div class="order_option">다른유저문제</div>
			            	</div>
			            </div>
			            <div class="order_dropdown">
			            	<div class="order_main">
			                	<span style="font-weight:bold" id="orderDisplay">정렬기준</span>
			            	</div>
							<div class="list_order">   
			                	<div class="order_option">최신순</div>
			                	<div class="order_option">좋아요순</div>
			                	<div class="order_option">조회순</div>
			                	<div class="order_option">댓글순</div>
			            	</div>
			         	</div>
		         	</div>
		          </div>
		          <div class="list_shadow" style="width: 67%; max-width:67%; position:relative;">
			            <ul class="list_items">
			                <li class="list1">
			                    <div class="list">JAVA</div>
			                </li>
			                <li class="list2">
			                    <div class="list">HTML</div>
			                </li>
			              	<li class="list3">
		                        <div class="list">CSS</div>
		                    </li>
		                    <li class="list4">
		                        <div class="list">Javascript</div>
		                    </li>
			                <li class="list5">
		                        <div class="list">Spring</div>
		                    </li>
		                    <li class="list6">
		                        <div class="list">JSP</div>
		                    </li>
		                    <li class="list7">
		                        <div class="list">EL</div>
		                    </li>
		                    <li class="list8">
		                        <div class="list">AWS</div>
		                    </li>
		                    <li class="list9">
		                        <div class="list">CLOUD</div>
		                    </li>
			            </ul>
			      </div>
			  </div>
			  <!-- 슬라이드 할 요소 -->
			 <div class="swiper-container"> 
			  <div class="swiper-wrapper">
				<div class="swiper-slide">
					     	<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				★
					      			</div>
					      			<div class="question_title" onclick="location.href='questionsolve.jsp'">JAVA</div>
				      			</div>
				      			<div class="question_mini">
				      				<div class="question_mbox">
				      					<div class="question_mtitle" onclick="location.href='questionsolve.jsp'">JAVA</div>
				      					<div class="question_answer">나의 정답률 60%(60/100)</div>
				      				</div>
				      			</div>
				      			<div class="question_count">
				      				<div class="count_box">
				      					<div class="likebox"><div class="question_like">❤</div><div style="margin-left:10px;">13</div></div>
				      					<div class="question_question" onclick="location.href='all_note_list.jsp'">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn">🚨</div>
				      			</div>
				      			
				      		</div>	
				      		<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				★
					      			</div>
					      			<div class="question_title" onclick="location.href='questionsolve.jsp'">HTML</div>
				      			</div>
				      			<div class="question_mini">
				      				<div class="question_mbox">
				      					<div class="question_mtitle" onclick="location.href='questionsolve.jsp'">HTML</div>
				      					<div class="question_answer">나의 정답률 60%(60/100)</div>
				      				</div>
				      			</div>
				      			<div class="question_count">
				      				<div class="count_box">
				      					<div class="likebox"><div class="question_like">❤</div><div style="margin-left:10px;">13</div></div>
				      					<div class="question_question" onclick="location.href='all_note_list.jsp'">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn" >🚨</div>
				      			</div>
				      		</div>
				      		<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				★
					      			</div>
					      			<div class="question_title" onclick="location.href='questionsolve.jsp'">CSS</div>
				      			</div>
				      			<div class="question_mini">
				      				<div class="question_mbox">
				      					<div class="question_mtitle" onclick="location.href='questionsolve.jsp'">CSS</div>
				      					<div class="question_answer">나의 정답률 60%(60/100)</div>
				      				</div>
				      			</div>
				      			<div class="question_count">
				      				<div class="count_box">
				      					<div class="likebox"><div class="question_like">❤</div><div style="margin-left:10px;">13</div></div>
				      					<div class="question_question" onclick="location.href='all_note_list.jsp'">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn">🚨</div>
				      			</div>
				      		</div>
				      		<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				★
					      			</div>
					      			<div class="question_title" onclick="location.href='questionsolve.jsp'">JSP</div>
				      			</div>
				      			<div class="question_mini">
				      				<div class="question_mbox">
				      					<div class="question_mtitle" onclick="location.href='questionsolve.jsp'">JSP</div>
				      					<div class="question_answer">나의 정답률 60%(60/100)</div>
				      				</div>
				      			</div>
				      			<div class="question_count">
				      				<div class="count_box">
				      					<div class="likebox"><div class="question_like">❤</div><div style="margin-left:10px;">13</div></div>
				      					<div class="question_question" onclick="location.href='all_note_list.jsp'">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn">🚨</div>
				      			</div>
				      		</div>
				      	 </div>
				      	 <div class="swiper-slide">
					     	<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				★
					      			</div>
					      			<div class="question_title" onclick="location.href='questionsolve.jsp'">JAVA</div>
				      			</div>
				      			<div class="question_mini">
				      				<div class="question_mbox">
				      					<div class="question_mtitle" onclick="location.href='questionsolve.jsp'">JAVA</div>
				      					<div class="question_answer">나의 정답률 60%(60/100)</div>
				      				</div>
				      			</div>
				      			<div class="question_count">
				      				<div class="count_box">
				      					<div class="likebox"><div class="question_like">❤</div><div style="margin-left:10px;">13</div></div>
				      					<div class="question_question" onclick="location.href='all_note_list.jsp'">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn">🚨</div>
				      			</div>
				      			
				      		</div>	
				      		<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				★
					      			</div>
					      			<div class="question_title" onclick="location.href='questionsolve.jsp'">HTML</div>
				      			</div>
				      			<div class="question_mini">
				      				<div class="question_mbox">
				      					<div class="question_mtitle" onclick="location.href='questionsolve.jsp'">HTML</div>
				      					<div class="question_answer">나의 정답률 60%(60/100)</div>
				      				</div>
				      			</div>
				      			<div class="question_count">
				      				<div class="count_box">
				      					<div class="likebox"><div class="question_like">❤</div><div style="margin-left:10px;">13</div></div>
				      					<div class="question_question" onclick="location.href='all_note_list.jsp'">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn" >🚨</div>
				      			</div>
				      		</div>
				      		<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				★
					      			</div>
					      			<div class="question_title" onclick="location.href='questionsolve.jsp'">CSS</div>
				      			</div>
				      			<div class="question_mini">
				      				<div class="question_mbox">
				      					<div class="question_mtitle" onclick="location.href='questionsolve.jsp'">CSS</div>
				      					<div class="question_answer">나의 정답률 60%(60/100)</div>
				      				</div>
				      			</div>
				      			<div class="question_count">
				      				<div class="count_box">
				      					<div class="likebox"><div class="question_like">❤</div><div style="margin-left:10px;">13</div></div>
				      					<div class="question_question" onclick="location.href='all_note_list.jsp'">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn">🚨</div>
				      			</div>
				      		</div>
				      		<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				★
					      			</div>
					      			<div class="question_title" onclick="location.href='questionsolve.jsp'">JSP</div>
				      			</div>
				      			<div class="question_mini">
				      				<div class="question_mbox">
				      					<div class="question_mtitle" onclick="location.href='questionsolve.jsp'">JSP</div>
				      					<div class="question_answer">나의 정답률 60%(60/100)</div>
				      				</div>
				      			</div>
				      			<div class="question_count">
				      				<div class="count_box">
				      					<div class="likebox"><div class="question_like">❤</div><div style="margin-left:10px;">13</div></div>
				      					<div class="question_question" onclick="location.href='all_note_list.jsp'">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn">🚨</div>
				      			</div>
				      		</div>
				      	 </div>
				      	<!--슬라이더 추가  -->
			      	</div>
			      	 <!-- 네비게이션 버튼 -->
					<div class="swiper-button-next"></div><!-- 다음 버튼 (오른쪽에 있는 버튼) -->
					<div class="swiper-button-prev"></div><!-- 이전 버튼 -->
					
					
			      </div>
			     <div class="goto">처음으로</div>
			</div>
			</div>	
<!-- 컨텐츠 영역  -->
<jsp:include page="./include/tail.jsp"></jsp:include>