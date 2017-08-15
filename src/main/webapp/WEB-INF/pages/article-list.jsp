<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Show Articles</title>
</head>
<body>
	
	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>文章類型</th>
				<th>title</th>
				<th>content</th>
				<th>作者</th>
				<th>圖片</th>
				<th>發行日</th>
				<th>點擊數</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="articles" items="${models}">
				<tr>
					<td>${articles.id}</td>
					<td>${articles.articleType}</td>
					<td>${articles.title}</td>
					<td>${articles.content}</td>
					<td>${articles.author}</td>
					<td>${articles.bannerImage}</td>
					<td>${articles.publishTime}</td>
					<td>${articles.clickCount}</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
</body>
</html>