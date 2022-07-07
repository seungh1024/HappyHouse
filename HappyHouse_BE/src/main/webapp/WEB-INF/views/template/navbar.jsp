<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>

<header class="header">
    <!-- 로고 -->
    <div class="profile">
      <a href="${root}/index.jsp"><img class="profile_img" src="${root}/img/logo.png" alt="logo" /></a>
    </div>

    <!-- 로그인 -->
    <nav class="header-nav">
    <c:if test="${empty sessionScope.userInfo}">
	    <ul id="header-before">
	      <li><a id="login" class="header_nav_menuitem" href="${root}/user/login">로그인</a></li>
	      <li><a class="header_nav_menuitem" href="${root}/user/register">회원가입</a></li>
	    </ul>
	</c:if>
	<c:if test="${not empty sessionScope.userInfo}">
	    <ul id="header-after">
	      <li>
	      	<a class="header_nav_menuitem" id="userNickname" href="${root}/user/info"></a>
	      	<a href="${root}/user/info">${sessionScope.userInfo.name}</a>
	      </li>
	      <li><a class="header_nav_menuitem" href="${root}/usesr/info">회원정보</a></li>
	    </ul>
	</c:if>
  	</nav>
    
    <!-- 헤더 메뉴 -->
    <nav class="header_main_menu">
      <ul>
        <li> <a class="header_nav_menuitem" href="${root}/html/dealInfo.jsp">실거래가 조회</a></li>
        <li> <a class="header_nav_menuitem" href="${root}/notice?act=mvgongji">공지사항</a></li>
      </ul>
    </nav>
 </header>