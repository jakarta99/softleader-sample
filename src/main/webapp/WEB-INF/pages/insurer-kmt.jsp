<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Show Insurers</title>
</head>
<body>
	<table border="2px solid" bgcolor="yellow" background="gray">
		<tr>
			<td>id</td>
			<td>name</td>
			<td>information</td>
			<td>logo</td>
			<td>complaintRatio</td>
			<td>bisRatio</td>
		</tr>
			<tr>
				<td>${models.id}</td>
				<td>${models.name}</td>
				<td>${models.information}</td>
				<td>${models.logo}</td>
				<td>${models.complaintRatio}</td>
				<td>${models.bisRatio}</td>
			</tr>
	</table>
	<a href="list">回上一頁</a>
</body>
</html>