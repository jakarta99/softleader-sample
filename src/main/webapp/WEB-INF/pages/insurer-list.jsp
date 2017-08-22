<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%-- <%@ include file="/resources/css/panel.css"%> --%>
<%-- <%@ include file="/resources/css/login.css"%> --%>
<%-- <%@ include file="/resources/css/frame.css"%> --%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Index</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"
	type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$('.insurer img').click(function() {
			$.get("InsurerController", {
				"id" : $(this).attr("id")
			}, function(data) {
				alert(data);
			})
		})

	})
</script>
<script src="<c:url value="/resources/js/jquery-ui.min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/login.js" />"
	type="text/javascript"></script>
<link href="../resources/css/frame.css" rel="stylesheet">
<link href="../resources/css/login.css" rel="stylesheet">
<link href="../resources/css/panel.css" rel="stylesheet">
<style>
.startSelect {
	border: 1px rgb(109, 140, 231) solid;
	border-radius: 20px;
	color: blue;
	background: white;
	position: relative;
	font-size: 15px;
	margin-top: 9%;
	color: rgb(109, 140, 231);
	padding: 6px 5%;
}

.startSelect:hover {
	pointer: cursor;
}

article {
	height: 100%;
	width: 30%;
	float: left;
	background-color: rgb(109, 140, 231);
}

nav {
	height: 100%;
	width: 70%;
	float: right;
	background: white;
}

.nav {
	overflow: auto;
}

.panel {
	margin-right: 50px;
	padding: 7px;
	color: black;
}

.panel a {
	font-family: "微軟正黑體";
	font-size: 20px;
	color: black;
	padding-right: 50px;
	font-weight: bold;
	width: auto;
}

.dropdown {
	width: 80px;
	font-family: "微軟正黑體";
	font-size: 20px;
	padding-right: 50px;
	font-weight: bold;
}

.insurer {
	color: white;
}

.loginCSS {
	border-radius: 20px;
	background-color: rgba(255, 255, 255, 0.7);
	border: black solid 1px;
	padding: 0px 14px;
	font-family: "微軟正黑體";
	font-weight: bold;
	font-size: 20px;
}

.dropcontent a {
	width: 80px;
	color: black;
	text-decoration: none;
	display: block;
	border: none;
	padding: 5px;
	font-size: 16px;
}

.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .05;
	filter: alpha(opacity = 20);
}

.white_content {
	display: none;
	position: absolute;
	top: 25%;
	left: 25%;
	width: 450px;
	height: 400px;
	padding: 16px;
	border: 2px solid blue;
	background-color: white;
	box-shadow: 5px 5px 5px blue;
	z-index: 1002;
	overflow: auto;
}
</style>
<script type="text/javascript">
	
</script>

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
				<button id="loginBtn" class="btn btn-lg btn-primary btn-block"
					type="submit">登入</button>
			</form>
		</div>
	</div>
	<!-- 登入視窗尾巴 -->
	<!-- 左半邊 -->

	<article>
		<div class="article">
			<a href="../index.jsp"><button class="logo">
					<img src="../image/icon.JPG" /> 這裡貼Logo
				</button></a>
			<div class="insurer">
				<h2>各公司資訊</h2>
				<p style="font-size: 15px">各公司資訊</p>
				<p>個別項目查詢</p>
			</div>
			<div>
				<img src="#">這裡貼圖片
			</div>
		</div>
	</article>
	<!-- 左半邊結束 -->


	<!-- 右半邊 -->
	<nav>
		<div class="nav">
			<!-- 控制台 -->
			<div class="panel">
				<a href="list">各公司資訊</a>
				<!-- Insurer-->

				<a href="../product/filter">商品專區</a>
				<!-- Product-->

				<div class="dropdown">
					<span class="dropbtn">積點專區</span>
					<!-- Gift-->
					<div class="dropcontent">
						<a href="../gift/page">積點商品</a> <a href="../gift/score">兌換記錄</a> <a
							href="../gift/wait">點數審核</a> <a href="../wish/list">許願池</a>
						<!-- Wish-->
					</div>
				</div>


				<div class="dropdown">
					<span class="dropbtn">文章專欄</span>
					<div class="dropcontent">
						<a href="../article/list">編輯精選</a> <a href="../article/news">新聞專區</a>
						<a href="../article/goodread">小資族必讀</a> <a
							href="../article/investmenttips">理財觀念</a>
					</div>
				</div>

				<a href="#">討論區</a>
				<!-- Question-->

				<span id="login" class="loginCSS">登入</span>
				<!-- Login-->
			</div>
			<div style="width: 600px; margin-left: 50px">
				<table border="2px solid" bgcolor="yellow" background="gray">
					<tr>
						<td>id</td>
						<td>name</td>
						<td>information</td>
						<td>logo</td>
						<td>complaintRatio</td>
						<td>bisRatio</td>
					</tr>
					<c:forEach var="Insurers" items="${models}">
						<form:form action="select" method="post" modelAttribute="insurer">
							<tr>
								<td><form:input type="submit" path="id"
										value="${Insurers.id}" /></td>
								<td>${Insurers.name}</td>
								<td>${Insurers.information}</td>
								<td>${Insurers.logo}</td>
								<td>${Insurers.complaintRatio}</td>
								<td>${Insurers.bisRatio}</td>
							</tr>
						</form:form>
					</c:forEach>
				</table>


				<div>
					<form:form action="update" method="post" modelAttribute="insurer">
						<table>
							<tr>
								<td>id:</td>
								<td><form:input path="id" type="text" /></td>
							</tr>
							<tr>
								<td>name:</td>
								<td><form:input path="name" type="text" /></td>
							</tr>
							<tr>
								<td>information:</td>
								<td><form:input path="information" type="text" /></td>
							</tr>
							<tr>
								<td>logo:</td>
								<td><form:input path="logo" type="file" /></td>
							</tr>
							<tr>
								<td>complaintRatio:</td>
								<td><form:input path="complaintRatio" type="text" /></td>
							</tr>
							<tr>
								<td>bisRatio:</td>
								<td><form:input path="bisRatio" type="text" /></td>
							</tr>
							<tr>
								<td><input type="submit" value="Insert/Update"></td>
							</tr>
						</table>
					</form:form>
				</div>
				<div>
					<form method="post" action="delete">
						<table>
							<tr>
								<td>ID:</td>
								<td><input id="id" name="id" type="text" /></td>
							</tr>
							<tr>
								<td><input type="submit" value="Delete" /></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div>
				<button type="button"
					onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">許願池</button>

				<div id="light" class="white_content">
					<button type="button" style="float: right"
						onclick="document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">Close</button>
				</div>
				<div id="fade" class="black_overlay"></div>
			</div>
			<form action="select" method="get">
				<table class="insurer">
					<tr>
						<td><img id="1"
							src="http://static-us.fever38.com/hotdeals/sponsor_logo/2014042115375484077_200X200.jpg"></td>
						<td><img id="2"
							src="https://media.licdn.com/mpr/mpr/shrink_200_200/AAEAAQAAAAAAAAcsAAAAJDllYzkzOTg1LTg4YmYtNDM2Zi1iOTE4LTAzM2E1ODAzYTQxMw.png"></td>
						<td><img id="3"
							src="https://obs.line-scdn.net/0m006759cb725142fc2d3963bc3a10d55f6a06361bae7a/preview"></td>
						<td><img id="4"
							src="https://media.licdn.com/mpr/mpr/shrink_200_200/AAEAAQAAAAAAAAPpAAAAJGJjZjdkMmI4LTdmYWItNGUwZC05OTEzLTgwYzJmYWZlN2QyNw.png"></td>
					</tr>
					<tr>
						<td><img id="5"
							src="http://dwimages.digwowimg.com/resize.php?imgurl=http%3A%2F%2Fgraph.facebook.com%2F120653918021517%2Fpicture%3Ftype%3Dlarge&type=cubic_200" /></td>
						<td><img id="6"
							src="https://yt3.ggpht.com/-5n-nMFmPyyo/AAAAAAAAAAI/AAAAAAAAAAA/Hjz_RuzQPjs/s200-c-k-no-mo-rj-c0xffffff/photo.jpg" /></td>
						<td><img id="7"
							src="https://media.licdn.com/mpr/mpr/shrink_200_200/AAEAAQAAAAAAAAPBAAAAJGUyNmM3OTY5LTQyYjktNGU3Yi1iZTg0LWFlYjlkMmZmMjgxYw.png" /></td>
						<td><img id="8"
							src="https://yt3.ggpht.com/-OjEoQEAMKlY/AAAAAAAAAAI/AAAAAAAAAAA/bUDKU6EFM4E/s200-c-k-no-mo-rj-c0xffffff/photo.jpg" /></td>
					</tr>
					<tr>
						<td><img id="9"
							src="https://my83.com.tw/images/company_logo/18.png"></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</form>

		</div>
	</nav>

	<!-- 右半邊結束 -->



</body>
</html>