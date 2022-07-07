<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 정보 수정</title>
    <link rel="stylesheet" href="${root}/css/user.css">
    <link rel="stylesheet" href="${root}/css/main.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
<%@ include file="/WEB-INF/views/template/navbar.jsp" %> 
    
    <script>
    	$(document).ready(function(){
    		
    		$("#yesBtn").click(function () {
                if (!$("#pw").val()) {
                    alert("비밀번호 입력!!!");
                    return;
                } else if (!$("#pw").val()) {
                    alert("비밀번호 확인란 입력!!!");
                    return;
                } else if ($("#pw").val() != $("#checkpw").val()){
                    alert("비밀번호가 다릅니다!!!");
                    return;    
                } else if(!$("#name").val()){
                	alert("이름을 입력해주세요!!!");
                    return; 
                } else {
                    $("#modifyForm").attr("action", "${root}/user/modify").submit();
                }
            });
    		
    		$("#noBtn").click(function(){
    			location.href = "${root}/user/info";
    		});
    		
    		
    	});
    </script>
    
    <section>
        <article class="change-page">
            <div class="form">
                <form name="modifyForm" id="modifyForm" method="post" action="${root}/user/modify">
                    <input id="pw" name="pw" type="password" placeholder="Password" />
                    <input id="checkpw" name="checkpw" type="password" placeholder="Password Check" />
                    <input id="name" name="name" type="text" placeholder="UserName" />
                    <button id="yesBtn" type="button">수정 완료</button>
                    <button id="noBtn" type="button">수정 취소</button>
                </form>
            </div>
        </article>
    </section>
</body>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>