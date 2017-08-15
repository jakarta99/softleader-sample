<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<title>商品專區</title>
</head>
<body>
	<a href="<c:url value="/index.jsp"/>"><img alt="" src="#">TRIPLE-I
		Logo HERE</a>

註:13-58目前無作用
	<form:form action="list" method="get" modelAttribute="product">
		<label class="required">性別</label>
		<form:radiobutton path="gender" value="male" />男
		<form:radiobutton path="gender" value="female" />女

		<label class="required">幣別</label>
		<form:select path="currency">
			<form:option value="NTD">台幣</form:option>
			<form:option value="JPY">日幣</form:option>
			<form:option value="USD">美金</form:option>
		</form:select>
		
		 <label class="required">存款方式</label>
		 <form:select path="depositmethod">
			<form:option value="一次繳清"/>
			<form:option value="分月繳清"/>
		</form:select> 
		
		<label class="required">利率</label>
		<form:select path="interest">
			<form:option value="宣告利率"/>
			<form:option value="浮動利率"/>
		</form:select>
		
		<label class="required">金額(元/年)</label>
		<form:select path="sum">
			<form:option value="~10,000"/>
			<form:option value="10,000~100,000"/>
			<form:option value="100,000~"/>
		</form:select> 
		
		<label class="required">繳費年限</label>
		<form:select path="period">
			<form:option value="3年以下"/>
			<form:option value="3-10年"/>
			<form:option value="10年以上"/>
		</form:select>
		
		<label class="required">可領回時間</label>
		<form:select path="back">
			<form:option value="3年以下"/>
			<form:option value="3-10年"/>
			<form:option value="10年以上"/>
		</form:select>

		<!-- 	demo用 -->
		<label class="required">請選擇保險公司</label>
		<form:select path="insurer">
			<form:option value="1">A人壽</form:option>
			<form:option value="2">B人壽</form:option>
			<form:option value="3">C人壽</form:option>
			<form:option value="4">D人壽</form:option>
			<form:option value="5">E人壽</form:option>
			<form:option value="6">F人壽</form:option>
			<form:option value="7">G人壽</form:option>
			<form:option value="8">H人壽</form:option>
			<form:option value="9">I人壽</form:option>
			<form:option value="10">J人壽</form:option>
			<form:option value="11">K人壽</form:option>
			<form:option value="12">L人壽</form:option>
			<form:option value="13">M人壽</form:option>
			<form:option value="14">N人壽</form:option>
			<form:option value="15">O人壽</form:option>
			<form:option value="16">P人壽</form:option>
			<form:option value="17">Q人壽</form:option>
			<form:option value="18">R人壽</form:option>
			<form:option value="19">S人壽</form:option>
			<form:option value="20">T人壽</form:option>
			<form:option value="21">U人壽</form:option>
			<form:option value="22">V人壽</form:option>
			<form:option value="23">W人壽</form:option>
			<form:option value="24">X人壽</form:option>
			<form:option value="25">Y人壽</form:option>
		</form:select>
		<!-- 	demo用 -->
		<input type="submit" value="篩選"/>
	</form:form>

	<c:forEach var="products" items="${models}">
		<form:label path="insurerdetal">${product.insurer_id}</form:label>
		<form:label path="productdetal">${product.id}</form:label>
		<form:select path="id">
			<form:option value="detal">檢視商品詳細資訊</form:option>
			<form:option value="我有興趣" href="product/shop"></form:option>
		</form:select>
	</c:forEach>
	
	<a href="../insurer/list">查看公司資訊</a>

	<!-- <!-- 暫無資料 -->
	<!-- 	<table> -->
	<!-- 		<tr> -->
	<!-- 			<th>保險公司名稱</th> -->
	<!-- 			<th>總繳金額</th> -->
	<!-- 			<th>領回金額</th> -->
	<!-- 			<th>淨報酬</th> -->
	<!-- 			<th>IRR</th> -->
	<!-- 		</tr> -->
	<!-- 		<tr> -->
	<!-- 			<td>儲蓄險名稱</td> -->
	<!-- 			<td>總繳金額(數字)</td> -->
	<!-- 			<td>領回金額(數字)</td> -->
	<!-- 			<td>淨報酬(數字)</td> -->
	<!-- 			<td>IRR(數字)</td> -->
	<!-- 		</tr> -->
	<!-- 		<hr> -->
	<!-- 		<tr> -->
	<!-- 			<td>保額:</td> -->
	<!-- 			<td>繳費折扣:</td> -->
	<!-- 			<td>緒年保費:</td> -->
	<!-- 			<td>繳費類別:</td> -->
	<!-- 			<td>折扣後年繳保費:</td> -->
	<!-- 			<td>宣告利率:</td> -->
	<!-- 			<td>折扣前年繳保費:</td> -->
	<!-- 			<td>首年保費:</td> -->
	<!-- 			<td>繳費方式:</td> -->
	<!-- 		</tr> -->
	<!-- 	</table> -->
</body>
</html>