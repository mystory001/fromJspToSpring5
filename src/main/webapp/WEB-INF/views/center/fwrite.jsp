<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>center/fwrite</title>
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
<!-- 
파일 첨부 시 form 태그에 enctype 속성을 이용. (enctype="multipart/form-data : 모든 문자를 인코딩하지 않음을 명시, form 요소가 파일이나 이미지를 서버로 전송할때 사용)
enctype의 method는 post인 경우에만 사용 가능함.

이외의 enctype속성으로는 application/x-www-form-urlencoded(모든 문자들은 서버로 보내기 전 인코딩됨을 명시. 기본값), text/plain(공백 문자는 + 기호로 변환하지만, 나머지 문자는 모두 인코딩되지 않음을 명시)
-->

<article>
<h1>File Write Notice</h1>
<form action="${pageContext.request.contextPath}/board/fwritePro" method="post" enctype="multipart/form-data">
<table id="notice">
<!-- 글쓴이 : 로그인 값 -->
<tr><td>글쓴이</td><td><input type="text" name="name" value="${sessionScope.id}" readonly></td></tr>
<!-- input name, value에 값을 넣어줘야 서버로 넘어감 -->
<tr><td>글제목</td><td><input type="text" name="subject" required></td></tr>
<tr><td>첨부파일</td><td><input type="file" name="file" required></td></tr>
<tr><td>글내용</td><td><textarea name="content" rows="10" cols="20"></textarea></td></tr>


</table>

<div id="table_search">
<!-- location 자바스크립트 내장 객체 => 웹 브라우저 주소줄을 객체로 정의
     location 내장객체 멤버 변수 => href 변수 : 웹 브라우저 주소줄 내용을 저장하고 있는 변수
						  => href 변수 내용이 변경되어지면 웹 브라우저 주소도 변경 
						  location.href='${pageContext.request.contextPath}/board/write -->
<input type="submit" value="글쓰기" class="btn">
<input type="button" value="글목록" class="btn" onclick="location.href='${pageContext.request.contextPath}/board/flist'">
</div>
</form>

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