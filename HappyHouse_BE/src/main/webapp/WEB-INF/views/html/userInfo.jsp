<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 정보</title>
    <link rel="stylesheet" href="${root}/css/user.css">
    <link rel="stylesheet" href="${root}/css/main.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
<%@ include file="/WEB-INF/views/template/navbar.jsp" %> 
    
	<script>
		$(document).ready(function(){
			$("#modifyBtn").click(function(){
				location.href = "${root}/user/modify";
			});
			
			$("#removeBtn").click(function(){
				location.href = "${root}/user/delete";	
			});
						
			$("#logoutBtn").click(function(){
				location.href = "${root}/user/logout";
			});
			
		});
	</script>    
    
    <section>
        <article class="user-info">
            <div class="form">
                <form name="info-form" class="info-form">
                    <div class="nickname">
                        <h2 id="user-nickname">${sessionScope.userInfo.name}</h2>
                        <h2>님 정보입니다.</h2>
                    </div>
                    <div class="user">
                        <h3>ID: </h3>
                        <h2>${sessionScope.userInfo.id}</h2>
                    </div>
                    <div class="user">
                        <h3>Password: </h3>
                        <h2>${sessionScope.userInfo.pw}</h2>
                    </div>
                    <button id="modifyBtn" type="button">정보 수정</button>
                    <button id="removeBtn" type="button">회원 탈퇴</button>
                    <button id="logoutBtn" id="logout" type="button">로그아웃</button>
                </form>
            </div>
        </article>
    </section>
</body>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>