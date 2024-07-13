<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<script src="${pageContext.request.contextPath}/resources/script/jquery-3.7.1.min.js"></script>
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<!-- include 경로 => jsp 문법 => 상대적인 웹 경로를 적용. 현 파일을 기준으로 상대적으로 경로를 표시 -->
<jsp:include page="../inc/top.jsp" />
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 본문메인이미지 -->
<div id="sub_img_member"></div>
<!-- 본문메인이미지 -->
<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href="#">Join us</a></li>
<li><a href="#">Privacy policy</a></li>
</ul>
</nav>
<!-- 왼쪽메뉴 -->
<!-- 본문내용 -->
<article>
<h1>List</h1>

<div id="table_search">
<input type="button" value="회원목록" class="btn">
</div>


<table id="notice">
<tr><th class="tno">ID</th>
<!--     <th class="ttitle">Password</th> -->
    <th class="twrite">Password</th>
    <th class="tdate">Name</th>
    <th class="tread">Date</th></tr>


</table>
</article>
<!-- 본문내용 -->
<!-- 본문들어가는 곳 -->

<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp" />
<!-- 푸터들어가는 곳 -->
</div>

<script type="text/javascript">
	$(function(){
		$('.btn').click(function(){
			$.ajax({
				url:"${pageContext.request.contextPath}/member/listjson",
				dataType : "json",
				success : function(result){
					//result 배열 → 반복
					$.each(result, function(index,item){
						//날짜 변경
						var date = new Date(item.date);
						var d = date.getFullYear()+"."+(date.getMonth()+1)+"."+date.getDate();
						//id="notice" → 한 줄 내용을 뒷부분에 추가 append()
						$('#notice').append('<tr><td>'+item.id+'</td><td>'+item.pw+'</td><td>'+item.name+'</td><td>'+item.date+'</td></tr>');
					})
				}
			})
		})
	})
</script>
</body>
</html>