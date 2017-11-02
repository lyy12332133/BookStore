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
            margin: 30px;
            width: 140px;
            height: 210px;
            text-align: center;
            float: left;
        }
        #find{
            font-size: 15px;
        }
        a{
            text-decoration: none;
        }
    </style>
</head>

<body>

<c:forEach var="book" items="${books}">
    <div class="icon">
        <a id="find" href="<c:url value='/AdminBookServlet?method=recoverLoad&bid=${book.bid}'/>"><em><strong>图书找回</strong></em></a>
        <br>
        <label>${book.bname}</label>
        <br>

        <img style="width: 100% ; height: 91%" src="<c:url value='/${book.image}'/>" border="0"/></a>

    </div>


</c:forEach>


</body>

</html>

