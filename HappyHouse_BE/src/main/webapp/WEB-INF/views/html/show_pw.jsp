<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

	<script type="text/javascript">
	$(document).ready(function(){ 
		var msg = '<c:out value="${sessionScope.userInfo.pw}"/>';
		alert("비밀번호: "+ msg);
		location.href='<%=request.getContextPath()%>/user/login';
		});
	</script>
</body>
</html>