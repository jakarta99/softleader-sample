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
<c:forEach var="Insurers" items="${models}">
<tr>
<td>${Insurers.id}</td>
<td>${Insurers.name}</td>
<td>${Insurers.information}</td>
<td>${Insurers.logo}</td>
<td>${Insurers.complaintRatio}</td>
<td>${Insurers.bisRatio}</td>
</tr>
</c:forEach>
</body>
</html>