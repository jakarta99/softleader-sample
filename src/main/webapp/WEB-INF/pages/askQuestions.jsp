<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/resources/css/panel.css"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>AskQuestion</title>
</head>
<body>
	<nav>
		<a href="<c:url value="/index.jsp"/>"><img alt="" src="#">TRIPLE-I
		Logo HERE</a>
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
	<br>
	<br>
	<div
		style="border: blue solid 1px; width: 800px; height: 300px; border-radius: 5px; padding: 15px">
		<form:form method="POST" action="insert" modelAttribute="question">
			<h3>請留下您的問題:</h3>
			<form:textarea path="content"
				style="border: blue solid 1px; width: 600px; height: 150px; border-radius: 5px" />
			<div>
				<label>請留下e-mail</label>
				<form:input path="askerEmail" type="text" placeholder="aaa@gmail.com" size="20"></form:input>
			</div>
			<div>
				<input type="submit" value="點我提交" />
			</div>
		</form:form>
	</div>
	
	
</body>
</html>