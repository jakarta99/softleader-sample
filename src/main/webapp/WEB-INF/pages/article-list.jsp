<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Show Articles</title>
</head>
<body>
<c:forEach var="articles" items="${models}">
<tr>
<td>${articles.id} </td>
<td>${articles.articleType} </td>
<td>${articles.title} </td>
<td>${articles.content} </td>
<td>${articles.author} </td>
<td>${articles.bannerImage} </td>
<td>${articles.publishTime} </td>
<td>${articles.clickCount} </td>
</tr>
</c:forEach>
</body>
</html>