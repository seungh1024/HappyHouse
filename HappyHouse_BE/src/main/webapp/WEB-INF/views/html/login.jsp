<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/header.jsp" %>

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
            if (!$("#userid").val()) {
                alert("아이디 입력!!!");
                return; 
            } else if (!$("#userpw").val()) {
                alert("비밀번호 입력!!!");
                return;
            }else {
                $("#loginform").attr("action", "${root}/member").submit();
            }
        });
  		
  		$("#registerBtn").click(function(){
  			location.href = "${root}/member?act=mvregister";
  		});
  		
  		$("#pwSearchBtn").click(function(){
  			location.href = "${root}/member?act=findpw";
  		});
  	});
</script>
  	
<body>
  <%@ include file="/template/navbar.jsp" %> 

  <section>
    <div class="login-page">
      <div class="form">
        <form name="loginform" id="loginform" method="post" action="">
        <input type="hidden" name="act" value="login">
          <input id="userid" name="userid" type="text" placeholder="ID" />
          <input id="userpw" name="userpw" type="password" placeholder="Password" />
          <button id="loginBtn" type="button">로그인</button>
          <button id="registerBtn" type="button">회원가입</button>
          <button id="pwSearchBtn" type="button">비밀번호 찾기</button>
        </form>
      </div>
    </div>
  </section>
</body>
<%@ include file="/template/footer.jsp" %>