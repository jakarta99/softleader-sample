<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/resources/css/wish.css"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>點數商品區</title>
<%@ include file="/resources/css/frame.css"%>
<style type="text/css">
body{
background:none;
}

</style>
</head>
<body>
		<a href="<c:url value="/index.jsp"/>"><button class="logo">TRIPLE-I
					這裡貼Logo</button></a>
	<table style="border-collapse: collapse">
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
		<c:forEach var="show" items="${giftlist}">
			<tr>
				<td>${show.id}</td>
				<td>${show.giftType}</td>
				<td>${show.name}</td>
				<td>${show.brand}</td>
				<td><img src="" />${show.image1}</td>
				<td>${show.image2}</td>
				<td>${show.image3}</td>
				<td>${show.bonus}</td>
				<td>${show.exchangeCount}</td>
				<td>${show.exchangePersonMax}</td>
			</tr>
		</c:forEach>
	</table>

	<!-- 	<div> -->
	<!-- 	<div><span style="font-family:Arial;color:blue;font-size:10px;">HOT</span><span style="">熱門兌換</span></div> -->
	<!-- 	<div> -->
	<%-- 	<c:forEach var="hot" items="${hotlist}"> --%>
	<%-- 	<div>${hot.image1}</div> --%>
	<%-- 	<span>${hot.name}</span> --%>
	<%-- 	<span>${hot.bonus}</span> --%>
	<%-- 	</c:forEach> --%>
	<!-- 	</div> -->
	<!-- 	<button type="button">查看更多</button> -->
	<!-- 	</div> -->

	<div>
		<form:form method="POST" action="save" modelAttribute="gift">
			<table>
				<tr>
					<td>產品ID:</td>
					<td><form:input path="id" type="text" /></td>
				</tr>
				<tr>
					<td>產品名稱:</td>
					<td><form:input path="name" type="text" /></td>
				</tr>
				<tr>
					<td>產品類別:</td>
					<td><form:input path="giftType" type="text" /></td>
				</tr>
				<tr>
					<td>品牌:</td>
					<td><form:input path="brand" type="text" /></td>
				</tr>
				<tr>
					<td>兌換點數:</td>
					<td><form:input path="bonus" type="text" /></td>
				</tr>
				<tr>
					<td>數量:</td>
					<td><form:input path="exchangeCount" type="text" /></td>
				</tr>
				<tr>
					<td>限兌換數量:</td>
					<td><form:input path="exchangePersonMax" type="text" /></td>
				</tr>
				<tr>
					<td>圖1:</td>
					<td><form:input path="image1" type="file" /></td>
				</tr>
				<tr>
					<td>圖2:</td>
					<td><form:input path="image2" type="file" /></td>
				</tr>
				<tr>
					<td>圖3:</td>
					<td><form:input path="image3" type="file" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="新增/更新點數商品" /></td>
				</tr>
			</table>
		</form:form>
	</div>

	<div>
		<form:form method="POST" action="delete" modelAttribute="gift">
			<table>
				<tr>
					<td>產品ID:</td>
					<td><form:input path="id" type="text" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="刪除點數商品" /></td>
				</tr>
			</table>
		</form:form>
	</div>
	<!-- 以下是許願池的 -->
	<button type="button"
		onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">許願池</button>

	<div id="light" class="white_content">
		<button type="button" style="float: right"
			onclick="document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">Close</button>
		<div>
			<h2>許願池</h2>
			<div style="text-align: justified; width: 250px;">
				積點專區的商品無法滿足您的欲望嗎？ 別擔心，您可以在這裏告訴我們您想要的商品， TRIPLE-I會盡全力滿足您的願望！ 快許下願望吧！
			</div>
		</div>
		<div>
			<h4>希望增加的兑换商品：</h4>
			<div
				style="width: 140px; height: 150px; border: blue solid 1px; float: left">
				<!--   <form method="POST" action="uploadFile" enctype="multipart/form-data"> -->
				<!--     <input type="file" accept=".jpg" name="file" /> -->
				<!--     <input type="submit" value="Upload"> -->
				<!--     </form> -->
				<input type="file" name="upload">
			</div>
			<textarea name="descriptions" id="wishDescription"
				style="border: blue solid 1px; width: 290px; height: 146px; float: right; border-radius: 5px">
  寫下您的願望~
  </textarea>
		</div>
		<button type="submit" style="float: right">許願</button>
	</div>
	<div id="fade" class="black_overlay"></div>


</body>
</html>
