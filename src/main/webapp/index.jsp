<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/css/panel.css" %>
<html>
<head>
<title>Index</title>
</head>
<body>
<nav>
<a href="index.jsp"><img alt="" src="#">TRIPLE-I Logo HERE</a>
<h2>Welcome Page</h2>

<a href="insurer/list">各公司資訊</a><!-- Insurer-->

<a href="#">商品專區</a><!-- Product-->

<div class="dropdown">
<button class="dropbtn">積點專區</button><!-- Gift-->
<div class="dropcontent">
<a href="gift/list">積點商品</a>
<a href="gift/score">兌換記錄</a>
<a href="gift/wait">點數審核</a>
<a href="wish/list">許願池</a> <!-- Wish-->
</div></div>

<a href="article/list">文章專欄</a> <!-- Article-->

<a href="#">討論區</a> <!-- Question-->

<button href="#">登入</button> <!-- Login-->


<a href="#">綫上服務</a>
<button href="#">開始篩選儲蓄險</button>
</nav>
</body>
</html>