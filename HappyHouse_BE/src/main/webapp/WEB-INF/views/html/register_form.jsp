<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>
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
            if (!$("#id").val()) {
                alert("아이디 입력!!!");
                return; 
            } else if (!$("#pw").val()) {
                alert("비밀번호 입력!!!");
                return;
            } else if (!$("#pwcheck").val()) {
                alert("비밀번호 확인란 입력!!!");
                return;
            } else if ($("#pw").val() != $("#pwcheck").val()){
                alert("비밀번호가 다릅니다!!!");
                return;    
            } else if(!$("#name").val()){
            	alert("이름을 입력해주세요!!!");
                return; 
            } else {
                $("#register-form").attr("action", "${root}/user/register").submit();
            }
        });
  	});
  </script>
  
</head>

<body>
  <%@ include file="/WEB-INF/views/template/navbar.jsp" %> 
  
  <section>
    <div class="login-page">
      <div class="form">
        <form name="register-form" id="register-form" method="post" action="<%=request.getContextPath()%>/user/register">
          <input id="id" name="id" type="text" placeholder="ID" />
          <input id="pw" name="pw" type="password" placeholder="Password" />
          <input id="pwcheck" name="pwcheck" type="password" placeholder="Password Check" />
          <input id="name" name="name" type="text" placeholder="username" />
          <button type="button" id="registerBtn">회원 등록</button>
        </form>
      </div>
    </div>
  </section>
</body>


<%@ include file="/WEB-INF/views/template/footer.jsp" %>