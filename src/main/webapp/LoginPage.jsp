<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
    <%@include file="/resources/css/panel.css" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>LoginPage</title>

</head>
<body>

<nav>
<a href="index.jsp"><img alt="" src="">TRIPLE-I Logo HERE</a>
<h2>Welcome Back!</h2>

<a href="insurer/list">各公司資訊</a><!-- Insurer-->

<a href="product/filter">商品專區</a><!-- Product-->

<div class="dropdown">
<button class="dropbtn">積點專區</button><!-- Gift-->
<div class="dropcontent">
<a href="gift/list">積點商品</a>
<a href="gift/score">兌換記錄</a>
<a href="gift/wait">點數審核</a>
<a href="wish/list">許願池</a> <!-- Wish-->
</div></div>


<div class="dropdown">
<button class="dropbtn" >文章專欄</button>
<div class="dropcontent" >
<a href="article/list"style="width: 100px">編輯精選</a>
<a href="article/news"style="width: 100px">新聞專區</a>
<a href="article/goodread"style="width: 100px">小資族必讀</a>
<a href="article/investmenttips"style="width: 100px">理財觀念</a>
</div></div>

<a href="#">討論區</a> <!-- Question-->

<button id="logout">登出</button> <!-- Login-->

<%-- <c:url value="askQuestions.jsp"/> --%>
<a href="question/view">缐上服務</a>
<a href="#"><button id="#">開始篩選儲蓄險</button></a>
</nav>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>
$('#logout').click(function(){
	window.location.href = "index.jsp";
})
</script>
</body>
</html>