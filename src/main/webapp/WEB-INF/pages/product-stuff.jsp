<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<title>�ӫ~�M��</title>
</head>
<body>
	<a href="<c:url value="/index.jsp"/>"><img alt="" src="#">TRIPLE-I
		Logo HERE</a>

��:13-58�ثe�L�@��
	<form:form action="list" method="get" modelAttribute="product">
		<label class="required">�ʧO</label>
		<form:radiobutton path="gender" value="male" />�k
		<form:radiobutton path="gender" value="female" />�k

		<label class="required">���O</label>
		<form:select path="currency">
			<form:option value="NTD">�x��</form:option>
			<form:option value="JPY">���</form:option>
			<form:option value="USD">����</form:option>
		</form:select>
		
		 <label class="required">�s�ڤ覡</label>
		 <form:select path="depositmethod">
			<form:option value="�@��ú�M"/>
			<form:option value="����ú�M"/>
		</form:select> 
		
		<label class="required">�Q�v</label>
		<form:select path="interest">
			<form:option value="�ŧi�Q�v"/>
			<form:option value="�B�ʧQ�v"/>
		</form:select>
		
		<label class="required">���B(��/�~)</label>
		<form:select path="sum">
			<form:option value="~10,000"/>
			<form:option value="10,000~100,000"/>
			<form:option value="100,000~"/>
		</form:select> 
		
		<label class="required">ú�O�~��</label>
		<form:select path="period">
			<form:option value="3�~�H�U"/>
			<form:option value="3-10�~"/>
			<form:option value="10�~�H�W"/>
		</form:select>
		
		<label class="required">�i��^�ɶ�</label>
		<form:select path="back">
			<form:option value="3�~�H�U"/>
			<form:option value="3-10�~"/>
			<form:option value="10�~�H�W"/>
		</form:select>

		<!-- 	demo�� -->
		<label class="required">�п�ܫO�I���q</label>
		<form:select path="insurer">
			<form:option value="1">A�H��</form:option>
			<form:option value="2">B�H��</form:option>
			<form:option value="3">C�H��</form:option>
			<form:option value="4">D�H��</form:option>
			<form:option value="5">E�H��</form:option>
			<form:option value="6">F�H��</form:option>
			<form:option value="7">G�H��</form:option>
			<form:option value="8">H�H��</form:option>
			<form:option value="9">I�H��</form:option>
			<form:option value="10">J�H��</form:option>
			<form:option value="11">K�H��</form:option>
			<form:option value="12">L�H��</form:option>
			<form:option value="13">M�H��</form:option>
			<form:option value="14">N�H��</form:option>
			<form:option value="15">O�H��</form:option>
			<form:option value="16">P�H��</form:option>
			<form:option value="17">Q�H��</form:option>
			<form:option value="18">R�H��</form:option>
			<form:option value="19">S�H��</form:option>
			<form:option value="20">T�H��</form:option>
			<form:option value="21">U�H��</form:option>
			<form:option value="22">V�H��</form:option>
			<form:option value="23">W�H��</form:option>
			<form:option value="24">X�H��</form:option>
			<form:option value="25">Y�H��</form:option>
		</form:select>
		<!-- 	demo�� -->
		<input type="submit" value="�z��"/>
	</form:form>

	<c:forEach var="products" items="${models}">
		<form:label path="insurerdetal">${product.insurer_id}</form:label>
		<form:label path="productdetal">${product.id}</form:label>
		<form:select path="id">
			<form:option value="detal">�˵��ӫ~�ԲӸ�T</form:option>
			<form:option value="�ڦ�����" href="product/shop"></form:option>
		</form:select>
	</c:forEach>
	
	<a href="../insurer/list">�d�ݤ��q��T</a>

	<!-- <!-- �ȵL��� -->
	<!-- 	<table> -->
	<!-- 		<tr> -->
	<!-- 			<th>�O�I���q�W��</th> -->
	<!-- 			<th>�`ú���B</th> -->
	<!-- 			<th>��^���B</th> -->
	<!-- 			<th>�b���S</th> -->
	<!-- 			<th>IRR</th> -->
	<!-- 		</tr> -->
	<!-- 		<tr> -->
	<!-- 			<td>�x�W�I�W��</td> -->
	<!-- 			<td>�`ú���B(�Ʀr)</td> -->
	<!-- 			<td>��^���B(�Ʀr)</td> -->
	<!-- 			<td>�b���S(�Ʀr)</td> -->
	<!-- 			<td>IRR(�Ʀr)</td> -->
	<!-- 		</tr> -->
	<!-- 		<hr> -->
	<!-- 		<tr> -->
	<!-- 			<td>�O�B:</td> -->
	<!-- 			<td>ú�O�馩:</td> -->
	<!-- 			<td>���~�O�O:</td> -->
	<!-- 			<td>ú�O���O:</td> -->
	<!-- 			<td>�馩��~ú�O�O:</td> -->
	<!-- 			<td>�ŧi�Q�v:</td> -->
	<!-- 			<td>�馩�e�~ú�O�O:</td> -->
	<!-- 			<td>���~�O�O:</td> -->
	<!-- 			<td>ú�O�覡:</td> -->
	<!-- 		</tr> -->
	<!-- 	</table> -->
</body>
</html>