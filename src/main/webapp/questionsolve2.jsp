<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./include/head_login.jsp"></jsp:include>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
<script>
	document.addEventListener("DOMContentLoaded", function() {
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
		swiper.slideNext();
	});
</script>
<style>
		a{
			text-decoration-line: none;
			color:#ffffff;
			font-weight:bold;
		}
		.container{
			display: inline-flex;
	    	width: 850px;
	    	flex-direction:column;
	    	height: calc(90vh - 8.8rem);
	    	margin-left:350px;
		}
	    .swiper-container{
	    	width:800px;
	    	height:680px;
	    	display:inline-flex;
	    	overflow:hidden;
	    }
	    .swiper-wrapper{
	    	margin-left:10px;
	    }
		.swiper-slide{
			margin-top: 5px;
			margin-left: 15px;
		    display: flex;
		    gap: 1.5rem;
		    flex-wrap: wrap;
		    height: 600px;
		    min-width: 800px; /* 추가: 최소 너비 설정 */
		}
		/* 화살표 위치 변경 */
		.swiper-button-next, .swiper-button-prev {
		    position: absolute;
			top: 50%;
			transform: translateY(-50%);
			z-index: 10;
			cursor: pointer;
			color: #cccccc;
			font-size: 20px;
			width: 36px;
			height: 17px;
			background-color: rgba(255, 255, 255, 0.3);
			text-align: center;
			line-height: 17px;
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
		    color: #cccccc; /* 화살표 색상 */
		}
		
		/* 화살표 배경색 및 크기 변경 */
		.swiper-button-next, .swiper-button-prev {
		    width: 36px; /* 화살표 크기 */
		    height: 17px; /* 화살표 크기 */
		}
		
		.question_box{
            width:45%;
			height:fit-content;
            
			}
			
		.question_item{
			margin-top:10px;
			width:350px;
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
            color: red;
            transform: scale(1.5);
        }
		
		.square{
			margin-top: 5px;
			background-position: center;
	    	background-size: cover;
	    	height:fit-content;
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
            font-size: 1rdem;
            color: #666;
            flex:1 1 45%;
            display:flex;
            width:fit-content;
		  }
		  .question_mini{
		  	width:100%;
		  	margin-top:0.5rem;
		  	display:inline-flex;
		  }
		  .question_mbox{
		  	display:flex;
		  	justify-content: flex-start;
		  	width:100%;
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
		  	width:100%;
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
		 

	</style>
	<div class="container">
			  <!-- 슬라이드 할 요소 -->
			 <div class="swiper-container"> 
			  <div class="swiper-wrapper">
				<div class="swiper-slide">
					     	<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				❤
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
				      					<div class="question_like">🤍 13</div>
				      					<div class="question_question">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn">🚨</div>
				      			</div>
				      			
				      		</div>	
				      		<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				❤
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
				      					<div class="question_like">🤍 13</div>
				      					<div class="question_question">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn" >🚨</div>
				      			</div>
				      		</div>
				      		<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				❤
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
				      					<div class="question_like">🤍 13</div>
				      					<div class="question_question">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn">🚨</div>
				      			</div>
				      		</div>
				      		<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				❤
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
				      					<div class="question_like">🤍 13</div>
				      					<div class="question_question">📚 21문제</div>
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
					      				❤
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
				      					<div class="question_like">🤍 13</div>
				      					<div class="question_question">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn">🚨</div>
				      			</div>
				      			
				      		</div>	
				      		<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				❤
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
				      					<div class="question_like">🤍 13</div>
				      					<div class="question_question">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn" >🚨</div>
				      			</div>
				      		</div>
				      		<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				❤
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
				      					<div class="question_like">🤍 13</div>
				      					<div class="question_question">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn">🚨</div>
				      			</div>
				      		</div>
				      		<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				❤
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
				      					<div class="question_like">🤍 13</div>
				      					<div class="question_question">📚 21문제</div>
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
					      				❤
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
				      					<div class="question_like">🤍 13</div>
				      					<div class="question_question">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn">🚨</div>
				      			</div>
				      			
				      		</div>	
				      		<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				❤
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
				      					<div class="question_like">🤍 13</div>
				      					<div class="question_question">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn" >🚨</div>
				      			</div>
				      		</div>
				      		<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				❤
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
				      					<div class="question_like">🤍 13</div>
				      					<div class="question_question">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn">🚨</div>
				      			</div>
				      		</div>
				      		<div class="question_box">
					     		<div class="question_item">
					      			<div class="bookmark">
					      				❤
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
				      					<div class="question_like">🤍 13</div>
				      					<div class="question_question">📚 21문제</div>
				      					<div class="question_person">🧑 12출제자</div>
				      				</div>	
				      					<div class="question_report" id="report_btn">🚨</div>
				      			</div>
				      		</div>
				      	 </div>
				      	<!--슬라이더끝  -->
			      	</div>
			      	 <!-- 네비게이션 버튼 -->
					<div class="swiper-button-next"></div><!-- 다음 버튼 (오른쪽에 있는 버튼) -->
					<div class="swiper-button-prev"></div><!-- 이전 버튼 -->
				
					<!-- 페이징 -->
					<div class="swiper-pagination"></div>
			      </div>
				</div>
<!-- 컨텐츠 영역  -->
<jsp:include page="./include/tail.jsp"></jsp:include>