<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<title>AskQuestionSuccess</title>
</head>
<body>
	<nav>
		<a href="index.jsp"><img alt="" src="#">TRIPLE-I Logo HERE</a>
		<h2>Welcome Page</h2>

		<a href="insurer/list">各公司資訊</a>
		<!-- Insurer-->

		<a href="#">商品專區</a>
		<!-- Product-->

		<div class="dropdown">
			<button class="dropbtn">積點專區</button>
			<!-- Gift-->
			<div class="dropcontent">
				<a href="gift/list">積點商品</a> <a href="gift/score">兌換記錄</a> <a
					href="gift/wait">點數審核</a> <a href="wish/list">許願池</a>
				<!-- Wish-->
			</div>
		</div>

		<a href="article/list">文章專欄</a>
		<!-- Article-->

		<a href="#">討論區</a>
		<!-- Question-->

		<button href="#">登入</button>
		<!-- Login-->


		<a href="#">缐上服務</a>
		<button href="#">開始篩選儲蓄險</button>
	</nav>
	<div>
		<h2>感謝提問</h2>
	</div>
	
	<div>
	<table>
	<h3>Print Result</h3>
	<tr>
	<td>${paste.content}</td>
	</tr>
	<tr>
	<td>${paste.askerEmail}</td>
	</tr>
	<tr>
	<td>${paste.questionType}</td>
	</tr>
	<tr>
	<td>${paste.postTime}</td>
	</tr>
	</table>
	</div>
	
	<a href="<c:url value="/index.jsp"/>">Back</a>
</body>
</html>