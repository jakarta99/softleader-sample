<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Template</title>
<script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery-ui.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/login.js" />" type="text/javascript" ></script>
<script src="<c:url value="/resources/js/template.js"/>" type="text/javascript" ></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="resources/css/template.css">
</head>
<body>
	<!-- 左半邊 -->
	<article>
		<div class="article">
			<a href="index.jsp"><button class="logo">TRIPLE-I這裡貼Logo</button></a>
			<div  class="leftContent">
				<h2>這裏輸入Title</h2>
				<p>這裏輸入内容</p>
				</div>
				<div>
<!-- 				<a href="#">多的按鈕</a> -->
			</div>
		</div>
	</article>
	<!-- 左半邊結束 -->

	<!-- 右半邊 -->
	<nav>
		<div class="nav">
			<!-- 控制台 -->
			<div class="panel">
				<a href="#">各公司資訊</a>
				<!-- Insurer-->
				<a href="#">商品專區</a>
				<!-- Product-->
				<div class="dropdown">
					<!-- Gift -->
					<span class="dropbtn">積點專區</span>
					<!-- Gift-->
					<div class="dropcontent">
						<a href="#">積點商品</a> <a href="#">兌換記錄</a> <a
							href="#">點數審核</a> <a href="#">許願池</a>
						<!-- Wish-->
					</div>
				</div>
				<div class="dropdown">
					<!-- Article -->
					<span class="dropbtn">文章專欄</span>
					<div class="dropcontent">
						<a href="#">編輯精選</a> <a href="#">新聞專區</a> <a
							href="#">小資族必讀</a> <a
							href="#">理財觀念</a>
					</div>
				</div>
				<a href="#">討論區</a>
				<!-- Question-->
				<span id="login" class="loginCSS">登入</span>
				<!-- Login-->
			</div>
			<!-- 控制板結束 -->
			<div id="online" class="onlineQuestion">
				<p style="padding-top: 10%;">缐上服務</p>
			</div>
		</div>
	</nav>
	<!-- 右半邊結束 -->
	
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
					<label> <input type="checkbox" value="remember-me">記住我
					</label>
				</div>
				<button id="loginBtn" class="btn btn-lg btn-primary btn-block"
					type="submit">登入</button>
			</form>
		</div>
	</div>
	<!-- 登入視窗尾巴 -->
<!-- 線上客服 -->
<div id="leaveQuestion" style="border: blue solid 1px; width: 800px; height: 300px; border-radius: 5px; padding: 15px">
		<form method="POST" action="insert">
			<h3>請留下您的問題:</h3>
			<textarea style="border: blue solid 1px; width: 600px; height: 150px; border-radius: 5px"></textarea>
			<div>
				<label>請留下e-mail</label>
				<input type="text" placeholder="aaa@gmail.com" size="20"></input>
			</div>
			<div>
				<input type="submit" value="點我提交" />
			</div>
		</form>
	</div>
<!-- 線上客服尾巴 -->
</body>
</html>