<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/template/header.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="${root}/css/user.css">
<link rel="stylesheet" href="${root}/css/main.css">
<title>게시물 작성</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
	<%@ include file="/template/navbar.jsp"%>

	<script>
		$(document).ready(
				function() {
					$("#regist_btn").click(
							function() {
								$("#register-form").attr("action",
										"${root}/notice?act=mvregister")
										.submit();
							});

					$("#back_btn").click(function() {
						location.href = "${root}/notice?act=mvgongji";
					});
				});
	</script>

	<main class="main">
		<div class="container m-auto">
			<div class="gongji_title">
				<img src="${root}/img/gongji_title.png" style="width: 100%">
			</div>
			<form name="register-form" id="register-form" method="post" action="">
				<table class="table border-1">
					<thead>
						<tr>
							<th>제목</th>
							<td><input type="text"
								class="form-control-plaintext bg-light" id="input_post_title"
								name="input_post_title" placeholder="제목을 입력하세요."></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="2"><textarea class="form-control" rows="15"
									id="input_post_content" name="input_post_content"></textarea></td>
						</tr>
					</tbody>
				</table>
				<div class="input-group justify-content-center">
					<button type="button" class="btn btn-outline-dark mr-3"
						id="regist_btn">등록</button>
					<button type="button" class="btn btn-outline-danger" id="back_btn">취소</button>
			</form>
		</div>
		</div>
	</main>

</body>
</html>