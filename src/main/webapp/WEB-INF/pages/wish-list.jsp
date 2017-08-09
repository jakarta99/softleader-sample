<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wish List</title>
<style>
th{
border:black solid 1px;
padding:5px;
}

td{
border:black solid 1px;
}
</style>
</head>
<body>
<table style="border-collapse:collapse">
<tr>
<th>ID</th>
<th>點數商品圖片</th>
<th>類別</th>
<th>品牌</th>
<th>許願池使用次數</th>
</tr>
<c:forEach var="wishes" items="${wishlist}">
<tr>
<td>${wishes.id}</td>
<td>${wishes.image1}</td>
<td>${wishes.wishType}</td>
<td>${wishes.brand}</td>
<td>${wishes.weekUseCount}</td>
</tr>
</c:forEach>
</table>
</body>
</html>