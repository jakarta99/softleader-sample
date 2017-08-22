<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>LoginPage</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery-ui.min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/login.js" />"
	type="text/javascript"></script>
<link href="resources/css/frame.css" rel="stylesheet">
<link href="resources/css/login.css" rel="stylesheet">
<link href="resources/css/panel.css" rel="stylesheet">
</head>
<body>
	<!-- 左半邊 -->
	<article>
		<div class="article">
			<a href="index.jsp"><button class="logo">TRIPLE-I 這裡貼Logo</button></a>
			<div  class="leftContent">
				<h2>~歡迎～</h2>
				<p>／這裏輸入内容／</p>
				</div>
				<div>
<!-- <a href="#">多的按鈕</a> -->
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
				<a href="index.jsp" id="logout">登出</a>
				<!-- Login-->
			</div>
			<!-- 控制板結束 -->
			<div id="online" class="onlineQuestion">
				<p style="padding-top: 10%;">缐上服務</p>
			</div>
		</div>
	</nav>
	<!-- 右半邊結束 -->
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