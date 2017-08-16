<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/resources/css/panel.css"%>
<%@ include file="/resources/css/login.css"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Index</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery-ui.min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/login.js" />"
	type="text/javascript"></script>

<%@ include file="/resources/css/frame.css"%>
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
</head>
<body>

	<!-- 登入視窗 -->
	<div id="loginWindow" title="Login">
		<div class="container">

			<form class="form-signin">
				<h2 class="form-signin-heading">登入</h2>
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
				<button class="btn btn-lg btn-primary btn-block" type="submit">登入</button>
			</form>
		</div>
	</div>

	<!-- 登入視窗尾巴 -->
	<!-- 左半邊 -->

	<article>
		<div class="article">
			<a href="index.jsp"><button class="logo">TRIPLE-I
					這裡貼Logo</button></a>
			<div>
				<h2>最專業的保險理財</h2>
				<h2>讓你的財富開始起飛</h2>
				<p>TRIPLE-I為您準備了簡易的商品比較及公開透明的資訊。 讓你妳輕鬆篩選商品，並擁有專業的保險團隊提供相關知識，
					即時為您解決理賠問題或保險疑問。</p>
			</div>
			<div >
			<a href="#" class="startSelect">開始篩選儲蓄險</a>
			</div>
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
						<a href="gift/list">積點商品</a> <a href="gift/score">兌換記錄</a> <a
							href="gift/wait">點數審核</a> <a href="wish/list">許願池</a>
						<!-- Wish-->
					</div>
				</div>


				<div class="dropdown">
					<span class="dropbtn">文章專欄</span>
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
			<div  class="onlineQuestion">
				<p style="padding-top:10%;"><a href="question/view">缐上服務</a></p>
				<%-- <c:url value="askQuestions.jsp"/> --%>
			</div>
		</div>
	</nav>

	<!-- 右半邊結束 -->



</body>
</html>