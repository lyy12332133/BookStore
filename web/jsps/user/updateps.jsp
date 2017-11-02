<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <style>
  label{
   color: red;
  }
  input{
   width: 200px;
   height: 35px;
   font-size: 20px;
  }
  #in1{
   margin-top: 10px;
   background-color: cornflowerblue;
  }
  body{
   background-image: url("../../images/back.jpg");
  }
  h1{
   color: cornflowerblue;
  }
 </style>
</head>
<body>
<h1>修改密码</h1>
<hr>
 <form action="${pageContext.request.contextPath}/UserServlet" method="post">
   原密码：<input type="password" name="oldpass"><span>${requestScope.error}</span>
  <label>${msg}</label>
  <br>
  新密码：<input type="password" name="newpass"><br>
  <input type="hidden" name="method" value="updatePWD">
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input id="in1" type="submit" value="提交">
 
 </form>
</body>
</html>