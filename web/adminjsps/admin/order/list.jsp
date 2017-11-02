<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
    
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
	* {
		font-size: 11pt;
	}
	div {
		border: solid 2px rgb(78,78,78);
		width: 70px;
		height: 100px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	td,th{
		border: 0px;
	}
	h1{
		text-align: center;
		font-size: 20px;
		color: cornflowerblue;
	}
</style>
  </head>
  
  <body style="background: rgb(254,238,189);">
<c:choose>
	<c:when test="${empty orders}">
		<h1>目前还没有该项订单, 老大</h1>
	</c:when>
	<c:otherwise>
		<h1>我的订单</h1>
	</c:otherwise>
</c:choose>



<table border="1" width="100%" cellspacing="0" background="black">

	<c:forEach var="order" items="${orders}">
		<tr bgcolor="rgb(78,78,78)" bordercolor="rgb(78,78,78)" style="color: white;">
			<td colspan="6">
				订单编号：${order.oid}　成交时间：${order.ordertime}　金额：<font color="red"><b>${order.total}</b></font>　

				<c:if test="${order.state eq 1}">
					未付款
				</c:if>
				<c:if test="${order.state eq 2}">
					等待发货 &nbsp;&nbsp;&nbsp;
					<a href="<c:url value='/AdminOrderServlet?method=send&oid=${order.oid}'/>">发货</a>
				</c:if>
				<c:if test="${order.state eq 3}">
					等待收货
				</c:if>
				<c:if test="${order.state eq 4}">
					订单完成
				</c:if>
			</td>
		</tr>
		<c:forEach var="orderItem" items="${order.orderItemList}">
			<tr bordercolor="gray" align="center">
				<td width="15%">
					<div><img style="width: 100% ; height: 100%" src="<c:url value='${orderItem.book.image}'/>" height="75"/></div>
				</td>
				<td>书名：${orderItem.book.bname}</td>
				<td>单价：${orderItem.book.price}元</td>
				<td>作者：${orderItem.book.author}</td>
				<td>数量：${orderItem.count}</td>
				<td>小计：${orderItem.subtotal}元</td>
			</tr>
		</c:forEach>

	</c:forEach>
















</table>
  </body>
</html>
