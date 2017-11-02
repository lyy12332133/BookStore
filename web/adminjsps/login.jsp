<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>管理员登录页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	  <style>
		  label {
			  color: red;
		  }

		  input[type="text"], input[type="password"] {
			  width: 200px;
			  height: 40px;
			  color: darkgrey;
			  font-size: 25px;
		  }

		  #div1{
			  margin: 20px;
			  text-align: left;
			  width: 800px;
			  height: 400px;
		  }

		  span {
			  font-size: 20px;
		  }

		  #sub {
			  width: 290px;
			  height: 40px;
			  font-size: 25px;
			  margin-top: 20px;
			  background-color: cyan;

		  }
		  h1{
			  color: dimgrey;
		  }
	  </style>
  </head>
  
  <body>
<h1>管理员登录页面</h1>
<hr/>
  <p style="font-weight: 900; color: red">${msg }</p>
<div id="div1">
	<form action="<c:url value='/AdminCategoryServlet'/>" method="post">
		<input type="hidden" name="method" value="login">
		管理员账户：<input type="text" name="adminname" value="${name}"/><br/>
		密　　　码：<input type="password" name="password"/><br/>
		<input id="sub" type="submit" value="进入后台"/>
	</form>
</div>

  </body>
</html>
