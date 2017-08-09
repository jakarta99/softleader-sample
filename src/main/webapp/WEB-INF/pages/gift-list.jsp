<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Gift Names</title>
<style type="text/css">
td{
padding:5px;
border:black solid 1px;
}

th{
border:black solid 1px;
}

.black_overlay {
  display: none;
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
   background-color: black; 
  z-index: 1001;
   -moz-opacity: 0.8; 
  opacity: .05; 
   filter: alpha(opacity=20); 
}
.white_content {
  display: none;
  position: absolute;
  top: 25%;
  left: 25%;
  width: 450px;
  height: 400px;
  padding: 16px;
  border: 2px solid	blue;
  background-color: white;
  box-shadow: 5px 5px 5px blue;
  z-index: 1002;
  overflow: auto;
}
</style>
<script>


</script>
</head>
<body>
<table style="border-collapse:collapse">
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
<c:forEach var="gift" items="${giftlist}">
<tr>
<td>${gift.id}</td>
<td>${gift.giftType}</td>
<td>${gift.name}</td>
<td>${gift.brand}</td>
<td>${gift.image1}</td>
<td>${gift.image2}</td>
<td>${gift.image3}</td>
<td>${gift.bonus}</td>
<td>${gift.exchangeCount}</td>
<td>${gift.exchangePersonMax}</td>
</tr>
</c:forEach>
</table>

<div>
<div><span style="font-family:Arial;color:blue;font-size:10px;">HOT</span><span style="">熱門兌換</span></div>
<div>
<c:forEach var="hot" items="${hotlist}">
<div>${hot.image1}</div>
<span>${hot.name}</span>
<span>${hot.bonus}</span>
</c:forEach>
</div>
<button type="button">查看更多</button>
</div>


<button type="button" onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">許願池</button>
 
  <div id="light" class="white_content">
  <button type="button" style="float:right" onclick="document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">Close</button>
  <div>
  <h2>許願池</h2>
  <div style="text-align:justified;width:250px;">
  積點專區的商品無法滿足您的欲望嗎？
  別擔心，您可以在這裏告訴我們您想要的商品，
  TRIPLE-I會盡全力滿足您的願望！
  快許下願望吧！
  </div>
  </div>
  <div>
  <h4>希望增加的兑换商品：</h4>
  <div style="width:140px;height:150px;border:blue solid 1px;float:left">
    <input type="file" class="file" />
  </div>
  <textarea name="descriptions" id="wishDescription" style="border:blue solid 1px;width:290px;height:146px;float:right;border-radius:5px">
  寫下您的願望~
  </textarea>
  </div>
   <button type="submit" style="float:right">許願</button>  
  </div>
  <div id="fade" class="black_overlay"></div>


</body>
</html>
