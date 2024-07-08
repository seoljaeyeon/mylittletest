<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Swiper 슬라이더</title>
    <!-- Swiper CSS -->
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
    <style>
        /* 기존 스타일 유지 */
        .question_items {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }

        .question_box {
            border: 1px solid #ccc;
            padding: 10px;
            width: 300px; /* 슬라이드 당 너비 조정 */
        }

        .question_item {
            display: flex;
            align-items: center;
        }

        .bookmark {
            font-size: 20px;
            margin-right: 5px;
        }

        .question_title {
            cursor: pointer;
            font-size: 18px;
            margin-bottom: 5px;
        }

        .question_mini {
            margin-top: 10px;
        }

        .question_mtitle {
            cursor: pointer;
            font-size: 14px;
            margin-bottom: 3px;
        }

        .count_box {
            display: flex;
            justify-content: space-between;
            margin-top: 5px;
        }

        .question_report {
            cursor: pointer;
            font-size: 20px;
            margin-top: 5px;
        }
    </style>
</head>
<body>

<!-- 슬라이드 할 요소 -->
<div class="swiper-container">
    <div class="swiper-wrapper">
        <!-- 첫번째 슬라이드 -->
        <div class="swiper-slide">
            <div class="question_items">
                <div class="question_box">
                    <div class="question_item">
                        <div class="bookmark">❤</div>
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
                        <div class="bookmark">❤</div>
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
                        <div class="question_report" id="report_btn">🚨</div>
                    </div>
                </div>
                <div class="question_box">
                    <div class="question_item">
                        <div class="bookmark">❤</div>
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
                        <div class="bookmark">❤</div>
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
        </div>
        <!-- 두번째 슬라이드 (추가 슬라이드의 예시) -->
        <div class="swiper-slide">
            <div class="question_items">
                <div class="question_box">
                    <div class="question_item">
                        <div class="bookmark">❤</div>
                        <div class="question_title" onclick="location.href='questionsolve.jsp'">Python</div>
                    </div>
                    <div class="question_mini">
                        <div class="question_mbox">
                            <div class="question_mtitle" onclick="location.href='questionsolve.jsp'">Python</div>
                            <div class="question_answer">나의 정답률 70%(70/100)</div>
                        </div>
                    </div>
                    <div class="question_count">
                        <div class="count_box">
                            <div class="question_like">🤍 15</div>
                            <div class="question_question">📚 25문제</div>
                            <div class="question_person">🧑 10출제자</div>
                        </div>
                        <div class="question_report" id="report_btn">🚨</div>
                    </div>
                </div>
                <!-- 추가적으로 원하는 만큼 슬라이드 아이템을 추가할 수 있습니다 -->
            </div>
        </div>
    </div>
    <!-- Add Pagination -->
    <div class="swiper-pagination"></div>
    <!-- Add Navigation -->
    <div class="swiper-button-next"></div>
    <div class="swiper-button-prev"></div>
</div>

<!-- Swiper JS -->
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

<!-- Initialize Swiper -->
<script>
    var swiper = new Swiper('.swiper-container', {
        slidesPerView: 1,
        spaceBetween: 10,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
    });
</script>

</body>
</html>
