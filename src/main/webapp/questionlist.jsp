<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./include/head.jsp"></jsp:include>
<script>
	document.addEventListener("DOMContentLoaded", function() {
 		//팝업요소를 가져온다
        var popup = document.getElementById("popup_report");

        //팝업 오픈버튼을 가져옴
        var popupOpenButton = document.getElementById("report_btn");

        // 버튼에 클릭이벤트 추가
        popupOpenButton.addEventListener("click", function() {
            // 팝업 표시 여부를 전환
            popup.classList.toggle("show");
        });
        // 선택사항: 닫기 버튼 클릭 시 팝업을 닫는 기능 추가
        var popupCloseButton = document.getElementById("reportdelete");
        popupCloseButton.addEventListener("click", function() {
            popup.classList.remove("show");
        });
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
	    	width: 800px;
	    	flex-direction:column;
	    	height: calc(90vh - 8.8rem);
	    	margin-left: 30px;
		}
		.list_container{
			display:flex;
		}
		.search_items {
			height: 25px;
			width: 600px;
			background: white;
			border-radius: 20px;
			padding: 10px;
			border: 1px solid #cccccc;
		}
		.search_input{
			border:none;
			background:none;
			outline:none;
			float:left;
			padding:0px;
			color:#797979;
			font-size:16px;
			width:200px;
		}
		.search_button {
			color:#f9deec;
			float:right;
			width:40px;
			height:100%;
			border-radius: 50%;
			background: white;
			border:none;
			font-size:16px;
			display:flex;
			justify-content:center;
			align-items:center;
			
		}
		.order_dropdown{
			display: inline-flex;
		    flex-direction: column;
		    position: relative;
		    margin-left:1rem;
		    margin-right:2rem;
		}
		.order_main{
			color: #ffffff;
		    display: inline-flex;
		    align-items: center;
		    justify-content: center;
		    width:7rem;
		    background-color: #333333;
		    padding: 0.5rem 0.5rem;
		    border-radius: 1rem 1rem 1rem 1rem;
		    box-shadow: 0.2rem 0.2rem 0.4rem #cccccc;
		}
		.list_order {
			height:fit-content;
		    display:none;
		    flex-direction: column;
		    width:100%;
		    overflow:hidden;
		}
		.list_items {
		    height: 4.5rem;
		    margin:0;
		    padding:0;
		    display:inline-flex;
		    align-items: center;
		    width: 100%;
		    max-width: 100%;
		    white-space: nowrap;
		    text-overflow: ellipsis;
		    position: relative;
		    list-style: none;
		}
		.list {
		    box-sizing:border-box;
		    display: inline-flex;
		    align-items: center;
		    justify-content: center;
			overflow: hidden;
		    white-space: nowrap;
		    width: max-content;
			height: auto;
			font-size: 1.2rem;
			border-radius: 2rem;
			padding: 0.75rem 2rem;
			margin: 0.5rem 1rem 0.5rem 0;
		    border: none;
			box-shadow: 0.2rem 0.2rem 0.4rem #696969,  -0.2rem -0.2rem 0.4rem #696969;
			background-color:#333333;
			white-space: nowrap;
		    position:relative;
		}
		.question_items{
			margin-top: 10px;
			display:inline-flex;
			width:800px;
			gap: 3rem;
			flex-wrap:wrap
		}
		.question_box{
			width:45%;
			
		}
		.question_item{
			margin-top:10px;
			width:100%;
	     	height:250px;
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
            color: #333;
            font-size:25px;
            font-weight: bold;
            width:fit-content;
            position: absolute;
			top: 50%;
			left: 50%;
			transform: translate(-50%, -50%);
		}
		.bookmark{
			margin-top: 5px;
			background-position: center;
	    	background-size: cover;
	    	height:fit-content;
	    	margin-left:5px;
	    	float:right;
	   
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
            font-size: 1em;
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
		  }
		  .count_box{
		 	 display:flex;
		 	 justify-content: flex-start;
		  	width:100%;
		  	gap:5px;
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
		<div class="container">
			<div class="search_box">
				<div class="list_container">
					<div class="search_area">
	            		<form class="search_items">
	               		 	<input class="search_input" type="text" placeholder="Search" spellcheck="false">
	               		 	<button class="search_button"><img src="./img/search-icon.svg" style="display:flex; width:1rem; justify-content: center; align-items: center;"></button>
	            		</form>
	       			</div>
		       		<div class="order_dropdown">
		            	<div class="order_main">
		                	<img src="./img/sort.png" style="opacity: 0.5; width:20px; height:20px;">
		                	&nbsp;&nbsp;<span>정렬기준</span>
		            	</div>
						<div class="list_order">   
		                	<div class="order_option1">최신순</div>
		                	<div class="order_option2">인기순</div>
		                	<div class="order_option3">조회순</div>
		                	<div class="order_option4">댓글순</div>
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
			            </ul>
			      </div>
		      </div>
		      <div class="question_items">
		     	<div class="question_box">
		     		<div class="question_item">
		      			<div class="bookmark">
		      				<img src="./img/like.png" style="opacity: 0.5; width:30px; height:30px;display:block;">
		      			</div>
		      			<div class="question_title"><a href="">JAVA</a></div>
	      			</div>
	      			<div class="question_mini">
	      				<div class="question_mbox">
	      					<div class="question_mtitle"><a href="">JAVA</a></div>
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
		      				<img src="./img/like.png" style="opacity: 0.5; width:30px; height:30px;display:block;">
		      			</div>
		      			<div class="question_title"><a href="">HTML</a></div>
	      			</div>
	      			<div class="question_mini">
	      				<div class="question_mbox">
	      					<div class="question_mtitle"><a href="">HTML</a></div>
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
		      				<img src="./img/like.png" style="opacity: 0.5; width:30px; height:30px;display:block;">
		      			</div>
		      			<div class="question_title"><a href="">CSS</a></div>
	      			</div>
	      			<div class="question_mini">
	      				<div class="question_mbox">
	      					<div class="question_mtitle"><a href="">CSS</a></div>
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
		      				<img src="./img/like.png" style="opacity: 0.5; width:30px; height:30px;display:block;">
		      			</div>
		      			<div class="question_title"><a href="">JSP</a></div>
	      			</div>
	      			<div class="question_mini">
	      				<div class="question_mbox">
	      					<div class="question_mtitle"><a href="">JSP</a></div>
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
		  </div>
<!-- 컨텐츠 영역  -->

<jsp:include page="./include/tail.jsp"></jsp:include>