<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=BIG5"> -->
<title>Article-Page</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery-ui.min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/login.js" />"
	type="text/javascript"></script>
	
	<style>
.onlineQuestion {
	border: 1px black solid;
	border-radius: 50%;
	height: 70px;
	width: 70px;
	background: rgba(50, 50, 50, 0.6);
	color: white;
	position: fixed;
	bottom: 10%;
	right: 3%;
}
.onlineQuestion:hover{
pointer:cursor;
}

.startSelect {
	border: 1px rgb(109,140,231) solid;
	border-radius: 20px;
	color: blue;
	background: white;
	position:relative;
 	font-size: 15px;
	margin-top: 9%;
	color:rgb(109, 140, 231);
	padding:6px 5%;
	
}

.startSelect:hover {
	pointer: cursor;
}
</style>
<link href="../resources/css/ArticleFrame.css" rel="stylesheet">
<link href="../resources/css/ArticleLogin.css" rel="stylesheet">
<link href="../resources/css/ArticlePanel.css" rel="stylesheet">

</head>
<body>

	<!-- 登入視窗 -->
	<div id="loginWindow" title="登入">
		<div class="container">

			<form class="form-signin">
				<label for="inputEmail" class="sr-only">Email：</label> <input
					type="email" id="inputEmail" class="form-control"
					placeholder="Email address" required autofocus> <label
					for="inputPassword" class="sr-only">密碼：</label> <input
					type="password" id="inputPassword" class="form-control"
					placeholder="Password" required>
				<div class="checkbox">
					<label> <input type="checkbox" value="remember-me">
						記住我
					</label>
				</div>
				<button id="loginBtn" class="btn btn-lg btn-primary btn-block" type="submit">登入</button>
			</form>
		</div>
	</div>
	<!-- 登入視窗尾巴 -->
	<!-- 左半邊 -->

	<article>
		<div class="article">
			<a href="index.jsp"><button class="logo">TRIPLE-I
					這裡貼Logo</button></a>
		</div>
		<div class="articlea">
		<p style="font-size:30px "><b>文章專欄</b></p>
		<p>編輯精選</p>
		<p>新聞專區</p>
		<p>小資族必讀</p>
		<p>理財觀念</p>
		
		</div>
	</article>
	<!-- 左半邊結束 -->


	<!-- 右半邊 -->
	<nav>
		<div class="nav">
			<!-- 控制台 -->
			<div class="panel">
				<a href="insurer/list">各公司資訊</a>
				<!-- Insurer-->

				<a href="product/filter">商品專區</a>
				<!-- Product-->

				<div class="dropdown">
					<span class="dropbtn">積點專區</span>
					<!-- Gift-->
					<div class="dropcontent">
						<a href="gift/page">積點商品</a> <a href="gift/score">兌換記錄</a> <a
							href="gift/wait">點數審核</a> <a href="wish/list">許願池</a>
						<!-- Wish-->
					</div>
				</div>


				<div class="dropdown">
					<span class="dropbtn"><a href="article/page">文章專欄</a></span>
					<div class="dropcontent">
						<a href="article/list">編輯精選</a> <a href="article/news">新聞專區</a> <a
							href="article/goodread">小資族必讀</a> <a
							href="article/investmenttips">理財觀念</a>
					</div>
				</div>

				<a href="#">討論區</a>
				<!-- Question-->

				<span id="login" class="loginCSS">登入</span>
				<!-- Login-->
			</div>
			<!-- 控制板結束 -->
			
		</div>
	</nav>

	<!-- 右半邊結束 -->



</body>
</html>