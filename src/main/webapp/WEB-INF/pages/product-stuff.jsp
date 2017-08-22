<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>商品專區</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/i18n/jquery-ui-i18n.min.js"></script>
<script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery-ui.min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/login.js" />"
	type="text/javascript"></script>
<script>
	$(function() {
		$.datepicker.setDefaults($.datepicker.regional['zh-TW']);
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "yy-mm-dd"
		});

		$('.optionzone').hide();
		$('.filterbutton').click(function() {
			$('.optionzone').show(1000, function() {
			})
		})
	});
</script>
<link href="../resources/css/login.css" rel="stylesheet">
<link href="../resources/css/general.css" rel="stylesheet">
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
	margin-left: 320px;
}

.startSelect:hover {
	pointer: cursor;
}

.optionzone {
	width: 1000px;
	height: 100px;
	margin-top: 100px;
}
</style>
</head>
<body>
	<!-- from index -->
	<nav>
		<div class="nav">
			<div class="panel">
				<a href="insurer/list">各公司資訊</a> <a href="product/filter">商品專區</a>
				<div class="dropdown">
					<span class="dropbtn">積點專區</span>
					<div class="dropcontent">
						<a href="gift/page">積點商品</a> <a href="gift/score">兌換記錄</a> <a
							href="gift/wait">點數審核</a> <a href="wish/list">許願池</a>
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
				<a href="#">討論區</a> <span id="login" class="loginCSS">登入</span>
			</div>

		</div>
		<div class="optionzone">
			<label class="required">存款方式</label> <select name="depositmethod">
				<option>一次繳清</option>
				<option>分月繳清</option>
			</select> <label class="required">利率</label> <select name="interest">
				<option>宣告利率</option>
				<option>浮動利率</option>
			</select> <label class="required">金額(元/年)</label> <select name="sum">
				<option>~10,000</option>
				<option>10,000~100,000</option>
				<option>100,000~</option>
			</select><br>
			 <label class="required">繳費年限</label> <select name="period">
				<option>3年以下</option>
				<option>3-10年</option>
				<option>10年以上</option>
			</select> <label class="required">可領回時間</label> <select name="back">
				<option>3年以下</option>
				<option>3-10年</option>
				<option>10年以上</option>
			</select><br><a href="#" class="startSelect">篩選</a>
		</div>

	</nav>

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
					<label><input type="checkbox" value="remember-me">記住我</label>
				</div>
				<button id="loginBtn" class="btn btn-lg btn-primary btn-block"
					type="submit">登入</button>
			</form>
		</div>
	</div>
	<article>
		<div class="article">
			<a href="../index.jsp"><button class="logo">TRIPLE-I
					這裡貼Logo</button></a>
			<div>
				<form action="#" method="post">
					<label>性別</label> <input type="radio" name="gender" value="male"
						required />男 <input type="radio" name="gender" value="female" />女
					<p>
						生日<br> <input type="text" id="datepicker">
					</p>

					<label>幣別</label><br> <select name="currency" class="option">
						<option value="NTD">台幣</option>
						<option value="JPY">日幣</option>
						<option value="USD">美金</option>
					</select>
				</form>
				<div>
					<input class="filterbutton" type="submit" value="開始篩選" /> <input
						type="reset" value="重新選擇" />
				</div>
			</div>
		</div>
	</article>
</body>
</html>