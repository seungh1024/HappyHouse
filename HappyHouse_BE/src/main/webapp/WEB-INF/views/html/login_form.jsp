<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>로그인</title>
  <link rel="stylesheet" href="${root}/css/main.css">
  <link rel="stylesheet" href="${root}/css/user.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<script>
  	$(document).ready(function(){
  		// 회원가입
        $("#loginBtn").click(function () {
            if (!$("#id").val()) {
                alert("아이디 입력!!!");
                return; 
            } else if (!$("#pw").val()) {
                alert("비밀번호 입력!!!");
                return;
            }else {
                $("#loginform").attr("action", "${root}/user/login").submit();
            }
        });
  		
  		$("#registerBtn").click(function(){
  			location.href = "${root}/user/register";
  		});
  		
  		$("#pwSearchBtn").click(function(){
  			location.href = "${root}/user/findpw";
  		});
  	});
</script>
  	
<body>
  <%@ include file="/WEB-INF/views/template/navbar.jsp" %>

  <section>
    <div class="login-page">
      <div class="form">
        <form name="loginform" id="loginform" method="post" action="<%=request.getContextPath()%>/user/login">
          <input id="id" name="id" type="text" placeholder="ID" />
          <input id="pw" name="pw" type="password" placeholder="Password" />
          <button id="loginBtn" type="button">로그인</button>
          <button id="registerBtn" type="button">회원가입</button>
          <button id="pwSearchBtn" type="button">비밀번호 찾기</button>
        </form>
      </div>
    </div>
  </section>
</body>
<%@ include file="/WEB-INF/views/template/footer.jsp" %>