<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>图书列表</title>

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
            background-image: url("../../images/back.jpg");
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
            height: 320px;
            margin-left: 43px;
            margin-bottom: 10px;
        }
        .div2{
            height: 30px;
            width: 100px;
            margin-left: 30px;
        }

        span {
            color: cornflowerblue;
        }

        a {
            color: dimgrey;
            text-decoration: none;
        }

        .right {
            float: right;
        }

        p {
            text-align: right;
        }
    </style>
</head>

<body>

<p><a href="<c:url value='/BookServlet?method=findByCid'/>">综合排序</a>
    <a href="<c:url value='/BookServlet?method=findByOrder&by=a'/>">销量排序</a>
    <a href="<c:url value='/BookServlet?method=findByOrder&by=c'/>">价格由低到高</a>
    <a href="<c:url value='/BookServlet?method=findByOrder&by=b'/>">价格由高到低</a></p>
<c:forEach var="book" items="${books}">
    <div class="div1">
        <a style="height: 20px" href="<c:url value='/BookServlet?method=load&bid=${book.bid}'/>">${book.bname}</a>
        <div class="icon">
            <a href="<c:url value='/BookServlet?method=load&bid=${book.bid}'/>"><img style="width: 100% ; height: 100%" src="<c:url value='/${book.image}'/>" border="0"/></a>
        </div>
        <div class="div2">
            <c:choose>
                <c:when test="${book.sales > 6}">
                    <img style="height: 30px ; width: 100px" src="../../images/爆款.jpg">
                </c:when>
                <c:otherwise>
                    <c:if test="${book.cid eq 'DF39D36ADA6B4464ABE618E5354DCC8A'}">
                        <img style="height: 30px ; width: 100px" src="../../images/推荐.jpg">
                    </c:if>
                </c:otherwise>
            </c:choose>
        </div>
        <span class="right">销量:${book.sales}</span>
        <span>¥:${book.price}元</span>
    </div>
</c:forEach>

</body>
</html>

