<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HAPPY HOUSE</title>
    <link rel="stylesheet" href="${root}/css/index.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=70f272e131e9047599ebf5ef696c1fe3"></script>
</head>

<body>
    <header class="header">
        <a href="/index.html" class="logo_img_click">
            <img src="img/logo.png" alt="logo" class="logo_img">
        </a>
        <nav class="header-nav">
        	<c:if test="${empty sessionScope.userInfo}"> 
	            <div id="header-before">
	                <div class="header_nav_menuitem">
	                    <a id="login" class="nav_link" href="${root}/user/login">로그인</a>
	                </div>
	                <div class="header_nav_menuitem">
	                    <a class="nav_link" href="${root}/user/register">회원가입</a>
	                </div>
	            </div>
             </c:if> 
           <c:if test="${not empty sessionScope.userInfo}">
	            <div id="header-after">
	                <div class="header_nav_menuitem">
	                    <a class="nav_link" id="userNickname" href="${root}/user/info"></a>  
	                    <a href="${root}/user/info">${sessionScope.userInfo.name} 님</a>
	                </div>
	                <div class="header_nav_menuitem">
	                    <a class="nav_link" href="${root}/user/info">회원정보</a>
	                </div>
	            </div>
            </c:if> 
        </nav>
    </header>

    <main>
        <section class="index_img_sec">
            <div class="background_box">
                <img src="img/house.png" class="background_img">
                <div class="black_box">
                    <div class="title_happy">
                        <img src="${root}/img/title2.png" alt="" class="title_happy_img">
                        <nav class="nav_menu_select">
                            <ul>
                                <a href="${root}/apt/info">
                                    <li>실거래가 조회</li>
                                </a>
                                <a href="${root}/notice?act=mvgongji">
                                    <li>공지사항</li>
                                </a>
                            </ul>
                        </nav>
                    </div>
                </div>
                <hr>
                <div class="map_title">
                    <h1 style="text-align: center;">서울시 지도 보기</h1>
                </div>
                <div id="map" style="margin: auto; width:80%;height:500px; margin-bottom: 100px;"></div>
            </div>
        </section>
    </main>
</body>



<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(37.566680, 126.9784147), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption); 
</script>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>