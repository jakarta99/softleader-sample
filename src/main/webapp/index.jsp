<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
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

<link href="resources/css/frame.css" rel="stylesheet">
<link href="resources/css/login.css" rel="stylesheet">
<link href="resources/css/panel.css" rel="stylesheet">
</head>
<body>
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
			<div>
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
			<div id="online" class="onlineQuestion">
				<p style="padding-top: 10%;">缐上服務</p>
				<%-- <c:url value="askQuestions.jsp"/> --%>
			</div>
		</div>
	</nav>
	<!-- 右半邊結束 -->
	<!-- 登入視窗 -->
	<div id="loginWindow" title="登入">
		<div class="container">
			<form id="loginForm" class="form-signin">
				<label for="inputEmail" class="sr-only">Email：</label> <br> <input
					type="email" id="inputEmail" class="form-control"
					placeholder="Email address" autofocus> <br /> <label
					for="inputPassword" class="sr-only">密碼：</label> <br> <input
					type="password" id="inputPassword" class="form-control"
					placeholder="Password">
				<div class="checkbox">
					<label> <input type="checkbox" value="remember-me">記住我
					</label>
				</div>
				<button id="loginBtn" class="btn btn-lg btn-primary btn-block"
					type="submit" style="float: left;">登入</button>
				<button id="registerBtn" class="btn btn-lg btn-primary btn-block"
					type="submit"
					style="float: right; width: 40%; height: 16%; margin: 0px; padding: 0px;">註冊帳號</button>
			</form>
		</div>
	</div>
	<!-- 登入視窗尾巴 -->
	<!-- 帳號註冊視窗 -->
	<div id="registerWindow" title="註冊帳號">
		<div class="container">
			<form id="registerForm" class="form-signin">
				<label for="inputEmail" class="sr-only">Email：</label> <br /> <input
					type="email" id="addEmail" class="form-control" placeholder="Email"
					required autofocus> <br /> <label for="inputPassword"
					class="sr-only">密碼：</label> <br /> <input type="password"
					id="addPassword" class="form-control" required placeholder="密碼">
				<br /> <label for="inputName" class="sr-only">姓名：</label> <br /> <input
					type="text" id="addName" class="form-control" required
					placeholder="姓名"> <br /> <label for="inputPhone"
					class="sr-only">手機：</label> <br /> <input type="text" id="addPhone"
					class="form-control" required placeholder="09XX-XXX-XXX"> <br />
				<label for="inputAddress" class="sr-only">地址：</label> <br /> <input
					type="text" id="addAddress" class="form-control" required
					placeholder="地址"> <br /> <label for="inputGender"
					class="sr-only">性別：</label> <br /> <input type="text"
					id="addGender" class="form-control" required
					placeholder="俊男-M ／ 美女-F"> <br />
				<button id="registerNow" class="btn btn-lg btn-primary btn-block"
					type="submit">註冊</button>
			</form>
		</div>
	</div>
	<!-- 帳號註冊視窗尾巴 -->
	<!-- 線上客服視窗 -->
	<div id="leaveQuestion"
		style="border: blue solid 1px; width: 800px; height: 300px; border-radius: 5px; padding: 15px">
		<form method="POST" action="insert">
			<h3>請留下您的問題:</h3>
			<textarea
				style="border: blue solid 1px; width: 600px; height: 150px; border-radius: 5px"></textarea>
			<div>
				<label>請留下e-mail</label> <input type="text"
					placeholder="aaa@gmail.com" size="20"></input>
			</div>
			<div>
				<input type="submit" value="點我提交" />
			</div>
		</form>
	</div>
	<!-- 線上客服視窗尾巴 -->

</body>
</html>