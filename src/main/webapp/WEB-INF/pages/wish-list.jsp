<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Testing Popup Window</title>
<style>
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
<script type="text/javascript">

</script>

</head>
<body>
 <button type="button" onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">許願池</button>
 
  <div id="light" class="white_content">
  <button type="button" style="float:right" onclick="document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">Close</button>
  <h2>許願池</h2>
  <div style="text-align:justified;width:250px;">
  積點專區的商品無法滿足您的欲望嗎？
  別擔心，您可以在這裏告訴我們您想要的商品，
  TRIPLE-I會盡全力滿足您的願望！
  快許下願望吧！
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