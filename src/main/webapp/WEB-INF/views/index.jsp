<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="./include/head.jsp"></jsp:include>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script>
	function formatPercentage(value) {
	    return value.toFixed(1);
	}
	
	function updateGoalAchivement() {
	    const todayGoalElement = document.getElementById('todayGoal');
	    const goalAchivementElements = document.querySelectorAll('#goalAchivement');
	    
	    goalAchivementElements.forEach(el => {
	        const percentage = parseFloat(el.getAttribute('data-value'));
	        if (!isNaN(percentage)) {
	            el.textContent = '('+ formatPercentage(percentage) + '%)';
	        }
	    });
	}
	
	document.addEventListener('DOMContentLoaded', (event) => {
	    updateGoalAchivement();
	});
	function increase() {
	    let numberInput = document.getElementById('Total');
	    numberInput.value = parseInt(numberInput.value) + 1;
	    document.querySelector('.arrow_btn:nth-child(1)').classList.add('active');
	    setTimeout(() => {
	        document.querySelector('.arrow_btn:nth-child(1)').classList.remove('active');
	    }, 500);
	}
	
	function decrease() {
	    let numberInput = document.getElementById('Total');
	    if (parseInt(numberInput.value) > 0) {
	        numberInput.value = parseInt(numberInput.value) - 1;
	        document.querySelector('.arrow_btn:nth-child(2)').classList.add('active');
	        setTimeout(() => {
	            document.querySelector('.arrow_btn:nth-child(2)').classList.remove('active');
	        }, 500);
	    }
	}
</script>
<style>
.main_container {
	display: inline-flex;
	flex-direction: column;
	margin-left: 120px;
	width: calc(100% - 14rem);
	background-color: #474747;
	padding: 1.5rem 2rem 2rem 2rem;
	border-radius: 2rem;
	height: 680px;
	gap: 10px;
}

.head_box {
	width: 100%;
	height: fit-content;
	display: inline-flex;
	min-width: 1106px;
}

.goal_box {
	border-right: 1px solid black;
	height: 140px;
	width: 368px;
	min-width: 368px;
}

.goal_title {
	font-size: 40px;
	text-align: center;
	height: fit-content;
}

.setting {
	margin-left: 50px;
	display: flex;
	margin-top: 1rem;
	justify-content: flex-end;
	margin-right: 2rem;
	cursor: pointer;
}

.count_setting {
	display: flex;
	margin-top: 1rem;
	justify-content: flex-end;
	margin-right: 2rem;
	cursor: pointer;
}

input[type="number"] {
	width: 50px;
	font-size: 28px;
	text-align: center;
	-moz-appearance: textfield; /* Firefox */
	background-color: #474747;
	border: none;
	outline: none;
	color: #ffffff;
	margin-top: 10px;
}

input[type="number"]::-webkit-outer-spin-button, input[type="number"]::-webkit-inner-spin-button
	{
	-webkit-appearance: none;
	margin: 0; /* Chrome, Safari, Edge, Opera */
}

.goal_btn {
	margin-left: 2rem;
	margin-right: 65px;
	background-position: center;
	background-size: cover;
	background-color: #333333;
	color: #ffffff;
	border-radius: 10px;
	height: 3rem;
	width: 120px;
	padding: auto;
	justify-content: center;
	align-items: center;
	font-size: 1rem;
	text-align: center;
	display: flex;
	cursor: pointer;
	border: none;
}

.goal_set {
	background-position: center;
	background-size: cover;
	float: right;
	font-size: 30px;
	min-width: 65px;
	width: 130px;
	display: flex;
}

.goal_achieve {
	float: right;
}

.goal_success {
	display: inline-flex;
	width: 200px;
	hight: 50px;
	gap: 0.2rem;
	flex-wrap: wrap;
	font-size: 14px;
}

.list_box {
	display: inline-flex;
	width: 1100px;
	justify-content: flex-end;
	margin-top: 30px;
	gap: 5px;
	cursor: pointer;
}

.list_set {
	display: flex;
	float: right;
	font-size: 20px;
}

.sub_box {
	display: inline-flex;
	width: 1100px;
	gap: 5rem;
	flex-wrap: wrap;
	font-size: 40px;
	margin-left: 30px;
	margin-top: 10px;
}

.sub_menu {
	border: 1px solid black;
	width: 45%;
	height: 8rem;
	text-align: center;
	justify-content: center;
	align-items: center;
	display: flex;
	background-color: #333333;
	border-radius: 10px;
	cursor: pointer;
}

.sub_menu:hover {
	background-color: #ffffff;
	color: #000000;
}

.arrow_btn {
	transition: transform 0.2s ease;
	/* transform 속성에 대해 0.2초간의 ease 애니메이션 적용 */
}

.arrow_btn.active {
	transform: scale(1.3); /* 활성화 상태일 때 크기를 좀 더 크게 스케일링 */
	color: #000000;
}
</style>
<!-- 비회원은 안보이게함   -->
<div class="main_container">
	<div class="head_box">
		<div class="goal_box">
			<div class="goal_title">😀 오늘의 목표</div>
			<div class="count_setting" style="margin-top: 30px">
				<div class="goal_btn" onclick="saveSetting()">목표 설정</div>
								<div class="goal_set">
					<div
						style="width: 20px; height: 48px; display: flex; flex-direction: column; align-items: center; justify-content: center;">
						<span class="arrow_btn" style="font-size: 20px;"
							onclick="increase()">&#x25B2;</span> <span class="arrow_btn"
							style="font-size: 20px;" onclick="decrease()">&#x25BC;</span>
					</div>
					<input type="number" id="Total" name="Total" value="100" min="0">
					<span
						style="font-size: 13px; margin-top: 25px; height: fit-content;">(문제)</span>
				</div>
			</div>
		</div>
		<div class="goal_box">
			<div class="goal_title">🎯 오늘 달성률</div>
			<div class="setting">
				<div class="goal_achieve" style="font-size: 30px; margin-top: 20px; display:flex;">
					<div><span style="font-size: 30px;"> <!-- 오늘푼 문제수 -->
						${(GoalDetails[0].answerCount > 0) ?  GoalDetails[0].answerCount : "목표" }</span>
						/ <!-- 사용자가 설정한 목표 문제수 --> <span id="todayGoal" style="font-size:30px;">${(GoalDetails[0].goalCount > 0) ?  GoalDetails[0].goalCount : "설정" }</span>
					</div>
					<%-- 오늘푼 문제수 / 사용자가 설정한 목표 문제수 * 100을 계산한값 --%>
					<div id="goalAchivement" data-value="${(GoalDetails[0].goalCount > 0) ? GoalDetails[0].answerCount * 100 / GoalDetails[0].goalCount : 0}"></div>
				</div>
			</div>
		</div>
	    <div class="goal_box">
	        <div class="goal_title">🏆 최근 달성률</div>
	        <div class="setting">    
		            <div class="goal_success" ">
		                <c:forEach var="goal" items="${GoalDetails}" varStatus="status">
		                    <div class="success_count" style="display:inline-flex; gap:0.5rem; font-size:11px;">
		                        ${status.index }일전  
		                      <c:choose>
		                        <c:when test="${goal.goalCount > 0}">
		                             <div id="goalAchivement" data-value="${goal.answerCount * 100 / goal.goalCount}"></div>
		            			</c:when>
		                        <c:otherwise>
		                            <div>미설정</div>
		                        </c:otherwise>
		                    </c:choose>    
		                    </div>
		                </c:forEach>
		            </div>    
		            <div class="goal_set" style="margin-top:20px; font-size:25px;">
		                <!-- 평균 달성률 표시 -->
		                <c:set var="totalAnswerCount" value="0" />
		                <c:set var="totalGoalCount" value="0" />
		                <c:forEach var="goal" items="${GoalDetails}">
		                    <c:if test="${goal.goalCount > 0}">
		                        <c:set var="totalAnswerCount" value="${totalAnswerCount + goal.answerCount}" />
		                        <c:set var="totalGoalCount" value="${totalGoalCount + goal.goalCount}" />
		                    </c:if>
		                </c:forEach>
		                <c:set var="avgPercent" value="${totalGoalCount > 0 ? (totalAnswerCount * 100) / totalGoalCount : 0}" />
		              <div id="goalAchivement" style="font-size:25px;" data-value="${avgPercent}"></div>
		            </div>
				</div>
			</div>
		</div>
		<div class="list_box">
			<div class="list_set"
				onclick="location.href='/mylittletest/questionlist'">⏳문제 목록 관리</div>
			<div class="list_set" onclick="location.href=''">⏳오늘 본 문제 목록</div>
		</div>
		<div class="sub_box">
			<div class="sub_menu" onclick="location.href='/mylittletest/write'">🤓문제쓰기</div>
			<div class="sub_menu" onclick="location.href='/mylittletest/mytest'">📚내문제 풀기</div>
			<div class="sub_menu" onclick="location.href='/mylittletest/reviewmytest'">📘맞춘 문제 복습</div>
			<div class="sub_menu" onclick="location.href='/mylittletest/correctmytest'">📕틀린 문제 복습</div>
			<div class="sub_menu" onclick="location.href='/mylittletest/todayquestions'">📖오늘 본 문제 복습</div>
			<div class="sub_menu" onclick="location.href='/mylittletest/bookmarkquestions'">❤북마크 문제 복습</div>
		</div>
	</div>
<script>
    function saveSetting() {
        let numberInput = document.getElementById('Total').value;
		let todayGoal = document.getElementById('todayGoal');
        let csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        let csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        fetch('/mylittletest/goalsetting', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify({ target: numberInput })
        })
        .then(response => response.json())
        .then(data => {
            if (data.status === 'success') {
                document.querySelector('.goal_btn').innerText = "설정 완료";
                todayGoal.innerText = numberInput;
            } else if (data.status === 'exist') {
                alert(data.result);
            } else if (data.status === 'login_needed') {
                alert("로그인이 필요합니다.");
                window.location.href = data.url;
            } else {
                alert("목표 설정에 실패했습니다.");
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("목표 설정 중 오류가 발생했습니다.");
        });
    }
</script>
<jsp:include page="./include/tail.jsp"></jsp:include>