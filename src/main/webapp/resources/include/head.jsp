<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
    <style>

        body {
            background-color: #292929;
            font-family: 'Inter', sans-serif;
            box-sizing: border-box;
            padding: 1rem;
            margin:0;
            color:#eeeeee;
        }

        .side_container {
            width:10rem;
            background-color: #474747;
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 2rem;
            padding: 1.5rem 2rem 2rem 2rem;
            border-radius: 2rem;
            height: calc(90vh - 8.8rem);
            min-height:calc(90vh - 8.8rem);
            max-height: calc(90vh - 8.8rem);
        }

        .side_button {
            width: 100%;
            background-color: #333333;
            color: #eeeeee;
            border-radius: 1rem;
            display:flex;
            align-items: center;
            justify-content: center;
            font-size: 1rem;
            min-height:4rem;
            height:3rem;
            font-weight: 600;
            box-shadow: 0.25rem 0.25rem 0.5rem 0rem rgba(0,0,0,0.2);
        }

        .logo {
            min-width: 10rem;
            width:10rem;
            border-radius: 1.5rem;
            height: 7rem;
            display: flex;
            justify-content: center;
            align-items: center;
            place-items: center;
            overflow: hidden;
            margin-right:2rem;
            
        }

        .titlebar {
            width: 100wh;
            height: 8rem;
            border-radius: 2rem;
            background-color: #474747;
            margin-bottom: 0.8rem;
            display:flex;
            align-items: center;
            padding:0.6rem 4rem 0.6rem 2rem;
            gap: 2rem;
            overflow: hidden;
        }

        .ad-container {
            width: calc(100%-15rem);
            height:100%;
            overflow:hidden;
            display: flex;
            gap:2rem;
            flex-direction:row;
            overflow: hidden;
            align-items: center;
        }

        .top-ads {
            min-width: 20rem;
            width:20rem;
            border-radius: 1.5rem;
            height: 6rem;
            display: flex;
            justify-content: center;
            align-items: center;
            place-items: center;
            overflow: hidden;
            box-shadow: 0.25rem 0.25rem 0.5rem 0rem rgba(0,0,0,0.2);
        }

        .ads_img {
            width: 100%;
        }

        .main-content{
            width: calc(100% - 14rem);
            background-color: #474747;
            padding: 1.5rem 2rem 2rem 2rem;
            border-radius: 2rem;
            height: calc(90vh - 8.8rem);
            min-height:calc(90vh - 8.8rem);
            max-height: calc(90vh - 8.8rem);
        }
    </style>
    <div class="titlebar">
        <div class="logo">
            <span style="font-size:5rem">🤓</span>
        </div>
        <div class="ad-container">
            <div class="top-ads">
                    <img src="https://res.heraldm.com/content/image/2022/06/17/20220617000554_0.jpg" class="ads_img" alt="">
            </div>
            <div class="top-ads">
                <img src="https://cdn.getnews.co.kr/news/photo/202103/522191_209313_3950.jpg" class="ads_img" alt="">
            </div>
            <div class="top-ads">
                <img src="https://ojsfile.ohmynews.com/PHT_IMG_FILE/2023/1129/IE003233784_PHT.jpg" class="ads_img" alt="">
            </div>
            <div class="top-ads">
                <img src="https://cdn.getnews.co.kr/news/photo/202103/522191_209313_3950.jpg" class="ads_img" alt="">
            </div>
            <div class="top-ads">
                <img src="https://res.heraldm.com/content/image/2022/06/17/20220617000554_0.jpg" class="ads_img" alt="">
            </div>
        </div>
    </div>
    <div style="display:flex;flex-direction: row; gap:0.8rem;">
        <aside class="side_container">
            <div class="side_button">
                로그인
            </div>
            <div class="side_button">
                나의 학습
            </div>        
            <div class="side_button">
                문제 둘러보기
            </div>        
            <div class="side_button">
                공지사항
            </div>        
            <div class="side_button manager_contact_button" >
                관리자 연락
            </div>
            <hr style="width:100%; opacity:0.6; margin-top:auto">
            <div class="side_button">
                웹사이트 운영 정책
            </div>
        </aside>
        <main class="main-content">