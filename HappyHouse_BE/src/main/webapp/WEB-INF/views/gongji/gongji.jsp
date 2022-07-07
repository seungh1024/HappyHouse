<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/template/header.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>공지사항</title>
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
<link rel="stylesheet" href="${root}/css/gongji.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
	<%@ include file="/template/navbar.jsp"%>

	<script>
		$(document).ready(function() {
			$("#post_btn").click(function() {
				location.href = "${root}/notice?act=mvregister";
			});

			$(".page-item").click(function() {
				$("#pg").val($(this).attr("data-pg"));
				$("#pageForm").attr("action", "${root}/notice").submit();
			});
		});
	</script>
	<form name="pageForm" id="pageForm">
		<input type="hidden" name="act" id="act" value="list" /> <input
			type="hidden" name="pg" id="pg" value="" />
	</form>

	<main class="main">
		<div class="container m-auto">
			<div class="gongji_title">
				<img src="${root}/img/gongji_title.png" style="width: 100%">
			</div>

			<table class="table table-hover border-1 post_list">
				<thead>
					<tr>
						<th>글 번호</th>
						<th>제목</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty noticeList}">
						<tr>
							<td colspan="2" style="text-align: center;">등록된 게시물이 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${not empty noticeList}">
						<c:forEach items="${noticeList}" var="n">
							<tr>
								<td><a
									href="${root}/notice?act=mvread&articleno=${n.articleno}">${n.articleno}</a></td>
								<td><a
									href="${root}/notice?act=mvread&articleno=${n.articleno}">${n.subject}</a></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<div class="d-flex justify-content-center">${navi.navigator}</div>

			<c:if test="${sessionScope.userInfo.userId eq 'manager'}">
				<button type="button" class="btn btn-outline-dark float-right"
					id="post_btn">등록</button>
			</c:if>
		</div>
	</main>
</body>
</html>