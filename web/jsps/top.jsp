<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>top</title>

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
		body {

			background-image: url("../images/top.jpg");
		}
		a {
			text-transform:none;
			text-decoration:none;
			font-size: 15pt;
			color: whitesmoke ;
		}
		a:hover {
			text-decoration:underline;
		}
		.right{
			float: right;
		}
		span{
			font-size: 15pt;
			color: whitesmoke;
		}
		label{
			font-size: 15pt;
			color: whitesmoke;
		}
		#div1 a{
			text-transform:none;
			text-decoration:none;
			font-size: 12pt;
			color: #161616;
		}

	</style>
	<embed  src="拔萝卜.mp3" loop="true" autostar="true" width="100" height="20" />
</head>

<body>
<h1 style="text-align: center;">小奉天儿 书店</h1>
<div style="font-size: 10pt;">
	<span class="right">累计销量:${sales}本 </span>
	<c:choose>
		<c:when test="${user.username!=null}">
			<label>您好：${user.username}</label>&nbsp;&nbsp;|&nbsp;&nbsp;
			<div id="div1">
			<a href="<c:url value='/CartServlet?method=findAll'/>" target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="<c:url value='/OrderServlet?method=myOrders'/>" target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="<c:url value='/jsps/usercenter/usercenter.jsp'/>" target="body">个人中心</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="<c:url value="/UserServlet?method=quit"/>" target="_parent">退出</a></div>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">登录</a> |&nbsp;
			<a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent">注册</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="<c:url value='/CartServlet?method=findAllNoLogin'/>" target="body">购物车</a>
		</c:otherwise>
	</c:choose>

</div>
</body>
</html>
