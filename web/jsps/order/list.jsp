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
		border: solid 2px gray;
		width: 70px;
		height: 100px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	body{
		background-image: url("../../images/back.jpg");
	}
	a{
		text-decoration: none;
	}
    td,th{
        border: 0px;
    }
	span{
		color: cornsilk;
	}
	h1{
		text-align: center;
		font-size: 20px;
		color: cornflowerblue;
	}
</style>
  </head>
  <body>

<c:choose>
	<c:when test="${empty orders}">
		<h1>亲, 您目前还没有订单, 快去购物吧</h1>
	</c:when>
	<c:otherwise>
		<h1>我的订单</h1>
	</c:otherwise>
</c:choose>
<table border="1" width="100%" cellspacing="0" background="black">

	<c:forEach var="order" items="${orders}">
		<tr bgcolor="gray" bordercolor="gray">
			<td colspan="6">
				订单编号：<span>${order.oid}</span>　成交时间：<span>${order.ordertime}</span>　金额：<font color="red"><b>${order.total}</b></font>　

				<c:if test="${order.state eq 1}">
					<span>未付款</span>

					<a href="<c:url value='/OrderServlet?method=load&oid=${order.oid}'/>">付款</a>
					<a href="<c:url value='/OrderServlet?method=delete&oid=${order.oid}'/>">删除订单</a>
				</c:if>
				<c:if test="${order.state eq 2}">
					<span>等待发货</span>

				</c:if>
				<c:if test="${order.state eq 3}">
					<span>等待收货</span>

					<a href="<c:url value='/OrderServlet?method=confirm&oid=${order.oid}'/>">确认收货</a>
				</c:if>
				<c:if test="${order.state eq 4}">
					<span>订单完成</span>

					<a href="<c:url value='/OrderServlet?method=delete&oid=${order.oid}'/>">删除订单</a>
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
