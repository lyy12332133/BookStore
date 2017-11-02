<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>left</title>
    <base target="body"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">

		body{
			background-image: url("../images/left.png");
		}
		*{
			font-size:12pt;
			text-align: center;
		}
		div {
			background: rgba(121, 120, 127, 0.93);
			padding: 5px;
			margin-bottom: 10px;
		}
		a {
			text-decoration: none;
			color: cornsilk;
		}
	</style>

  </head>
  
  <body>
<div>
	<a href="<c:url value='/BookServlet?method=findAll'/>">全部分类</a>
</div>

<c:forEach var="category" items="${categorys}">
	<div>
		<a href="<c:url value='/BookServlet?method=findByCategory&cid=${category.cid}'/>">${category.cname}</a>
	</div>
</c:forEach>

  </body>
</html>
