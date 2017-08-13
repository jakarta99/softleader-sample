<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert Insurer Success</title>
</head>
<body>
	<table border="2px" bordercolor="green">
		<thead background="yellow">Insert Insurer Success</thead>
		<tr bordercolor="black">
			<td>id</td>
			<td>name</td>
			<td>information</td>
			<td>logo</td>
			<td>complaintRatio</td>
			<td>bisRatio</td>
		</tr>
		<tr>
			<td>${insert.id}</td>
			<td>${insert.name}</td>
			<td>${insert.information}</td>
			<td>${insert.logo}</td>
			<td>${insert.complaintRatio}</td>
			<td>${insert.bisRatio}</td>
		</tr>
	</table>
</body>
</html>