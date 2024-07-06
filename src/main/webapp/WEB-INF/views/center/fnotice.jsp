<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- => 날짜 모양 변경 태그 라이브러리 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>center/fnotice</title>
<link href="${pageContext.request.contextPath}/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/top.jsp" />
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->

<!-- 왼쪽메뉴 -->
<jsp:include page="../inc/left.jsp" />
<!-- 왼쪽메뉴 -->

<!-- 게시판 -->
<article>
<h1>File Notice [전체 글의 개수 ${pageDTO.count }]</h1>
<table id="notice">
<tr><th class="tno">No.</th>
    <th class="ttitle">Title</th>
    <th class="twrite">Writer</th>
    <th class="tdate">Date</th>
    <th class="tread">Read</th></tr>

<c:forEach var="boardDTO" items="${boardList}">
<tr onclick="location.href='${pageContext.request.contextPath}/board/fcontent?num=${boardDTO.num}'">
	<td>${boardDTO.num}</td>
	<td class="left">${boardDTO.subject}</td>
    <td>${boardDTO.name}</td>
    
    <td><fmt:formatDate value="${boardDTO.date}" pattern="yyyy-MM-dd"/></td>
    <td>${boardDTO.readcount}</td></tr>

</c:forEach>

</table>

<!-- 로그인을 하지 않으면 글쓰기 버튼이 보이지 않게 설정 -->
<c:if test="${!empty sessionScope.id }">
<div id="table_search">
<!-- location 자바스크립트 내장 객체 => 웹 브라우저 주소줄을 객체로 정의
     location 내장객체 멤버 변수 => href 변수 : 웹 브라우저 주소줄 내용을 저장하고 있는 변수
						  => href 변수 내용이 변경되어지면 웹 브라우저 주소도 변경 
						  location.href='${pageContext.request.contextPath}/board/write -->
<input type="button" value="글쓰기" class="btn" onclick="location.href='${pageContext.request.contextPath}/board/fwrite'">
</div>
</c:if>

<div id="table_search">
<input type="text" name="search" class="input_box">
<input type="button" value="search" class="btn">
</div>
<div class="clear"></div>
<div id="page_control">
<c:if test="${pageDTO.startPage > pageDTO.pageBlock }">
<a href="${pageContext.request.contextPath}/board/flist?pageNum=${pageDTO.startPage - pageDTO.pageBlock}">Prev</a>
</c:if>

<c:forEach var="i" begin="${pageDTO.startPage }" end="${pageDTO.endPage }" step="1">
<a href="${pageContext.request.contextPath}/board/flist?pageNum=${i}">${i}</a>
</c:forEach>

<c:if test="${pageDTO.endPage < pageDTO.pageCount}">
<a href="${pageContext.request.contextPath}/board/flist?pageNum=${pageDTO.startPage + pageDTO.pageBlock}">Next</a>
</c:if>

</div>
</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp" />
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>