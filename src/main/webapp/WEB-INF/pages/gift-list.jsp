<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Gift Names</title>
<style type="text/css">
td{
padding:5px;
border:black solid 1px;
}

th{
border:black solid 1px;
}

</style>
</head>
<body>
<table style="border-collapse:collapse">
<tr>
<th>ID</th>
<th>類別</th>
<th>點數商品名稱</th>
<th>品牌</th>
<th>圖1</th>
<th>圖2</th>
<th>圖3</th>
<th>點數兌換</th>
<th>已兌換次數</th>
<th>共可兌換數量</th>
</tr>
<c:forEach var="gift" items="${giftlist}">
<tr>
<td>${gift.id}</td>
<td>${gift.giftType}</td>
<td>${gift.name}</td>
<td>${gift.brand}</td>
<td>${gift.image1}</td>
<td>${gift.image2}</td>
<td>${gift.image3}</td>
<td>${gift.bonus}</td>
<td>${gift.exchangeCount}</td>
<td>${gift.exchangePersonMax}</td>
</tr>
</c:forEach>
</table>
</body>
</html>
