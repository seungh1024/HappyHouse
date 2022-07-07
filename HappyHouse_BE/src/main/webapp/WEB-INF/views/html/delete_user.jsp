<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 탈퇴</title>
    <link rel="stylesheet" href="${root}/css/user.css">
    <link rel="stylesheet" href="${root}/css/main.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
<%@ include file="/WEB-INF/views/template/navbar.jsp" %> 
    
    <script>
		$(document).ready(function(){
			//console.log(sessionScope.userInfo.userPW)
			$("#yesBtn").click(function(){
				if(!$("#pw").val()){
					alert("패스워드를 입력해주세요!");
					return
				}else if("${sessionScope.userInfo.pw}" != $("#pw").val()){
					alert("패스워드를 다시 입력해주세요!");
					return
				}else{
					$("#removeForm").attr("action", "${root}/user/delete").submit();
				}
			});
			
			$("#noBtn").click(function(){
				location.href = "${root}/user/info";	
			});
			
		});
	</script>  
    
    <section>
        <article class="remove-page">
            <div class="form">
                <form name="removeForm" id="removeForm" method="post" action="">
                    <h2>HAPPY HOUSE 회원을 탈퇴하실 건가요?</h2>
                    <input id="pw" name="pw" type="password" placeholder="Password" />
                    <button id="yesBtn" type="button">예</button>
                    <button id="noBtn" type="button">아니오</button>
                </form>
            </div>
        </article>
    </section>
</body>
<footer>
    <ul>
        <li><a class="" href="#">MADE BY. KKK & KSK</a></li>
        <li><a class="" href="#">SSAFY 7th</a></li>
        <li><a class="" href="#">Call: 010-0000-0000</a></li>
        <li><a class="" href="#">Email: kkkksk@gmail.com</a></li>
        <li>&copy; KKKKSK Corp.</li>
    </ul>
</footer>

</html>