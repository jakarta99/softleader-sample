<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Gift Added</title>
</head>
<body>
	<div>
		<h2>新產品資訊</h2>
		<table>
			<tr>
				<td>新產品名稱:</td>
				<td>${newGift.name}</td>
			</tr>
			<tr>
				<td>產品類別:</td>
				<td>${newGift.giftType}</td>
			</tr>
			<tr>
				<td>品牌:</td>
				<td>${newGift.brand}</td>
			</tr>
			<tr>
				<td>兌換點數:</td>
				<td>${newGift.bonus}</td>
			</tr>
			<tr>
				<td>數量:</td>
				<td>${newGift.exchangeCount}</td>
			</tr>
			<tr>
				<td>限兌換數量:</td>
				<td>${newGift.exchangePersonMax}</td>
			</tr>
		</table>
	</div>
	<a href="<c:url value='/gift/list'/>">回上一頁</a>
</body>
</html>