<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		alert("비밀번호 찾기 실패! 아이디와 이름을 확인해주세요.");
		location.href='<%=request.getContextPath()%>/findpw';
	</script>
</body>
</html>