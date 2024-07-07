<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>center/fcontent</title>
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
<h1>File Content Notice</h1>
<table id="notice">
<tr><td>글번호</td><td>${boardDTO.num}</td></tr>
<tr><td>글쓴이</td><td>${boardDTO.name}</td></tr>
<tr><td>조회수</td><td>${boardDTO.readCount}</td></tr>
<tr><td>작성일</td><td>${boardDTO.date}</td></tr>
<tr><td>글제목</td><td>${boardDTO.subject}</td></tr>
<tr><td>첨부파일</td><td><a href="${pageContext.request.contextPath}/resources/upload/${boardDTO.file}" download>${boardDTO.file}</a><img src="${pageContext.request.contextPath}/resources/upload/${boardDTO.file}" width="100" height="100"></td></tr>
<tr><td>글내용</td><td>${boardDTO.content}</td></tr>
</table>

<div id="table_search">
<c:if test="${! empty sessionScope.id }">
	<c:if test="${sessionScope.id eq boardDTO.name }">
		<input type="button" value="글수정" class="btn" onclick="location.href='${pageContext.request.contextPath}/board/fupdate?num=${boardDTO.num}'">
		<input type="button" value="글삭제" class="btn" onclick="location.href='${pageContext.request.contextPath}/board/fdelete?num=${boardDTO.num}'">
	</c:if>
</c:if>

<input type="button" value="글목록" class="btn" onclick="location.href='${pageContext.request.contextPath}/board/flist'">

</div>

<div class="clear"></div>
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