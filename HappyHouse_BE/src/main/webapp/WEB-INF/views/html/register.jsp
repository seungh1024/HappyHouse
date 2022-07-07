<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>회원가입</title>
  <link rel="stylesheet" href="${root}/css/user.css">
  <link rel="stylesheet" href="${root}/css/main.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script type="text/javascript">
  	$(document).ready(function(){
  		// 회원가입
        $("#registerBtn").click(function () {
            if (!$("#userid").val()) {
                alert("아이디 입력!!!");
                return; 
            } else if (!$("#userpw").val()) {
                alert("비밀번호 입력!!!");
                return;
            } else if (!$("#pwcheck").val()) {
                alert("비밀번호 확인란 입력!!!");
                return;
            } else if ($("#userpw").val() != $("#pwcheck").val()){
                alert("비밀번호가 다릅니다!!!");
                return;    
            } else if(!$("#username").val()){
            	alert("이름을 입력해주세요!!!");
                return; 
            } else {
                $("#register-form").attr("action", "${root}/member").submit();
            }
        });
  	});
  </script>
  
</head>

<body>
  <%@ include file="/template/navbar.jsp" %> 
  
  <section>
    <div class="login-page">
      <div class="form">
        <form name="register-form" id="register-form" method="post" action="">
        <input type="hidden" name="act" id="act" value="register">
          <input id="userid" name="userid" type="text" placeholder="ID" />
          <input id="userpw" name="userpw" type="password" placeholder="Password" />
          <input id="pwcheck" name="pwcheck" type="password" placeholder="Password Check" />
          <input id="username" name="username" type="text" placeholder="username" />
          <button type="button" id="registerBtn">회원 등록</button>
        </form>
      </div>
    </div>
  </section>
</body>


<%@ include file="/template/footer.jsp" %>