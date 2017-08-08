<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Gift Names</title>
<style type="text/css">
td{
padding:2px;
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
<th>Category</th>
<th>Name</th>
<th>Brand</th>
<th>Image 1</th>
<th>Image 2</th>
<th>Image 3</th>
<th>Redeem Points</th>
<th>Redeemed Count</th>
<th>Available Sets</th>
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
