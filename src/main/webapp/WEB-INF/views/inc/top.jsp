<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>

<c:if test="${empty sessionScope.id }">
<div id="login"><a href="${pageContext.request.contextPath}/member/login">로그인</a> | <a href="${pageContext.request.contextPath}/member/insert">회원가입</a></div>
</c:if>

<c:if test="${! empty sessionScope.id }">
<div id="login">${sessionScope.id }님 | <a href="${pageContext.request.contextPath}/member/logout">로그아웃</a> | <a href="${pageContext.request.contextPath}/member/update">정보수정</a>
<c:if test="${sessionScope.id eq 'admin' }">
| <a href="${pageContext.request.contextPath }/member/list">회원 리스트</a>
</c:if>
</div>
</c:if>

<div class="clear"></div>
<!-- 로고들어가는 곳 -->
<div id="logo"><img src="${pageContext.request.contextPath}/resources/images/logo.gif" width="265" height="62" alt="Fun Web"></div>
<!-- 로고들어가는 곳 -->
<nav id="top_menu">
<ul>
	<li><a href="${pageContext.request.contextPath }/member/main">HOME</a></li>
	<li><a href="../company/welcome">COMPANY</a></li>
	<li><a href="#">SOLUTIONS</a></li>
	<li><a href="${pageContext.request.contextPath }/board/list">CUSTOMER CENTER</a></li>
	<li><a href="#">CONTACT US</a></li>
</ul>
</nav>
</header>