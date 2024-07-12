<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery-3.7.1.min.js"></script>
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
<h1>Join Us</h1>
<form action="${pageContext.request.contextPath}/member/insertPro" id="join" method="post">
<fieldset>
<legend>필수정보</legend>
<label>User ID</label>
<input type="text" name="id" class="id">
<input type="button" value="중복확인" class="dup"><br>
<div id="dupdiv"></div><br>
<label>Password</label>
<input type="password" name="pw" class="pw"><br>
<label>Password 확인</label>
<input type="password" class="pwcheck"><br>
<label>Name</label>
<input type="text" name="name" class="name"><br>
</fieldset>

<fieldset>
<legend>선택정보</legend>
<label>E-Mail</label>
<input type="email" name="email"><br>
<label>E-Mail 확인</label>
<input type="email" name="email2"><br>
<label>Address</label>
<input type="text" name="address"><br>
<label>Phone Number</label>
<input type="text" name="phone"><br>
<label>Mobile Phone Number</label>
<input type="text" name="mobile"><br>
</fieldset>
<div class="clear"></div>
<div id="buttons">
<input type="submit" value="가입하기" class="submit">
<input type="reset" value="취소하기" class="cancel">
</div>
</form>
</article>
<!-- 본문내용 -->
<!-- 본문들어가는 곳 -->

<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp" />
<!-- 푸터들어가는 곳 -->
<script type="text/javascript">
	$(function(){
		//대상.함수()
		$(".dup").click(function(){
			$.ajax({
				//속성 : 값, 속성 : 값,...
				url:'${pageContext.request.contextPath}/member/idCheck',
				data : {'id':$('.id').val()}, //data : {이름:값, 이름:값}
				success : function(result){
					if(result=="iddup"){
						result = "사용불가능한 아이디입니다.";
					}else{
						result = "사용가능한 아이디입니다.";
					}
					$('#dupdiv').html(result);
				}
			});
		});
	});
	
	$(function(){
		//대상.함수()
		$('#join').submit(function(){
			if($('.id').val()==null ||$('.id').val()==''){
				alert("아이디를 입력해주세요.");
				$('.id').focus();
				return false; //전송하지 못하게 함수를 호출한 곳으로 이동(되돌아가기)
			}
			if($('.pw').val()==null ||$('.pw').val()==''){
				alert("비밀번호를 입력해주세요.");
				$('.pw').focus();
				return false; 
			}
			if($('.pwcheck').val()==null ||$('.pwcheck').val()==''){
				alert("비밀번호 확인을 입력해주세요.");
				$('.pwcheck').focus();
				return false; 
			}
			if($('.name').val()==null ||$('.name').val()==''){
				alert("이름를 입력해주세요.");
				$('.name').focus();
				return false; 
			}
			if($('.pw').val !== $('.pwcheck')){
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
		})
	})	

</script>
</div>
</body>
</html>