<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>비밀번호 찾기</title>
<link rel="stylesheet" href="${root}/css/user.css">
<link rel="stylesheet" href="${root}/css/main.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>


<body>
	<%@ include file="/WEB-INF/views/template/navbar.jsp"%>
	<script>
	$(document).ready(
			function() {

				$("#findBtn").click(
						function() {
							if (!$("#id").val()) {
								alert("아이디 입력!!!");
								return;
							} else if (!$("#name").val()) {
								alert("이름 입력!!!");
								return;
							} else {
								$("#findPwForm").attr("action",
										"${root}/user/findpw").submit();
							}
						});

				$("#backBtn").click(function() {
					location.href = "${root}/user/login";
				});

			});
</script>

	<section>
		<article class="find-pw-page">
			<div class="form">
				<form name="findPwForm" id="findPwForm" method="post" action="${root}/user/findpw">
					<input id="id" name="id" type="text" placeholder="ID" /> <input
						id="name" name="name" type="text" placeholder="Name" />
					<button id="findBtn" type="button">비밀번호 찾기</button>
					<button id="backBtn" type="button">취소</button>
				</form>
			</div>
		</article>
	</section>
</body>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>
