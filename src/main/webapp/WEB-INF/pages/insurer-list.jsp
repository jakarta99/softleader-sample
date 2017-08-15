<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		<c:forEach var="Insurers" items="${models}">
			<form:form action="select" method="post" modelAttribute="insurer">
				<tr>
					<td><form:input type="submit" path="id" value="${Insurers.id}" /></td>
					<td>${Insurers.name}</td>
					<td>${Insurers.information}</td>
					<td>${Insurers.logo}</td>
					<td>${Insurers.complaintRatio}</td>
					<td>${Insurers.bisRatio}</td>
				</tr>
			</form:form>
		</c:forEach>
	</table>

<!-- 	<div> -->
<%-- 		<form:form method="post" action="insert" modelAttribute="insurer"> --%>
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					<td>name:</td> -->
<%-- 					<td><form:input path="name" type="text" /></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>information:</td> -->
<%-- 					<td><form:input path="information" type="text" /></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>logo:</td> -->
<%-- 					<td><form:input path="logo" type="file" /></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>complaintRatio:</td> -->
<%-- 					<td><form:input path="complaintRatio" type="text" /></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>bisRatio:</td> -->
<%-- 					<td><form:input path="bisRatio" type="text" /></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td><input type="submit" value="Insert" /></td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<%-- 		</form:form> --%>
<!-- 	</div> -->
	<div>
		<form:form action="update" method="post" modelAttribute="insurer">
			<table>
				<tr>
					<td>id:</td>
					<td><form:input path="id" type="text" /></td>
				</tr>
				<tr>
					<td>name:</td>
					<td><form:input path="name" type="text" /></td>
				</tr>
				<tr>
					<td>information:</td>
					<td><form:input path="information" type="text" /></td>
				</tr>
				<tr>
					<td>logo:</td>
					<td><form:input path="logo" type="file" /></td>
				</tr>
				<tr>
					<td>complaintRatio:</td>
					<td><form:input path="complaintRatio" type="text" /></td>
				</tr>
				<tr>
					<td>bisRatio:</td>
					<td><form:input path="bisRatio" type="text" /></td>
				</tr>
				<tr><td><input type="submit" value="Insert/Update"></td></tr>
			</table>
		</form:form>
	</div>
	<div>
		<form method="post" action="delete">
			<table>
				<tr>
					<td>ID:</td>
					<td><input id="id" name="id" type="text" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Delete" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>