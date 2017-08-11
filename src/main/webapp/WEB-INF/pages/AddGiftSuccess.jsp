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
		<h2>New Gift Information</h2>
		<table>
			<tr>
				<td>Name :</td>
				<td>${newGift.name}</td>
			</tr>
			<tr>
				<td>Gift Type :</td>
				<td>${newGift.giftType}</td>
			</tr>
			<tr>
				<td>Brand :</td>
				<td>${newGift.brand}</td>
			</tr>
		</table>
	</div>
	<a href="<c:url value="/index.jsp"/>">Back</a>
</body>
</html>