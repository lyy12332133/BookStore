<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>图书分类</title>

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
            font-size: 12pt;
            background: rgb(254, 238, 189);
        }

        .icon {
            margin: 10px;
            border: solid 2px gray;
            width: 140px;
            height: 200px;
            text-align: center;

        }
        .div1{
            float: left;
            width: 160px;
            height: 290px;
            margin-left: 43px;
            margin-bottom: 10px;
        }
        span {
            color: cornflowerblue;
        }

        a {
            color: dimgrey;
            text-decoration: none;
        }
        .right{
            float: right;
        }
    </style>
</head>

<body>

<c:forEach var="book" items="${books}">
    <div class="div1">
        <p style="height: 20px">
        <a href="<c:url value='/AdminBookServlet?method=load&bid=${book.bid}'/>">${book.bname}</a>
        </p>

        <div class="icon">
            <a href="<c:url value='/AdminBookServlet?method=load&bid=${book.bid}'/>"><img style="width: 100% ; height: 100%" src="<c:url value='/${book.image}'/>" border="0"/></a>
        </div>
        <span class="right">销量:${book.sales}</span>
        <span>¥:${book.price}元</span>
    </div>

</c:forEach>



</body>

</html>

