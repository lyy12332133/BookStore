<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>登录</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
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
            margin: 50px;
            text-align: left;
            width: 600px;
            height: 300px;
        }

        span {
            font-size: 20px;
        }

        .sub {
            width: 280px;
            height: 40px;
            font-size: 25px;
            margin-top: 20px;
            background-color: cyan;
        }
        h1{
            color: dimgrey;
        }
        body{
            background-image: url("../../images/back.jpg");
        }


    </style>
</head>

<body>

<div id="div1">
    <p style=" font-size: 25px; color: red; font-weight: 900">${msg }</p>
    <h1>登录</h1>
    <form action="<c:url value='/UserServlet'/>" method="post" target="_parent">
        <input type="hidden" name="method" value="login">
        <span>用户名：</span><input type="text" name="username" value="${form.username}"/>
        <label>${maps.nameError}</label>
        <br>
        <br>
        <span>密　码：</span><input type="password" name="password"/>
        <label>${maps.pwdError}</label>
        <br>
        <input class="sub" type="submit" value="LOGIN"/>
    </form>
</div>


</body>
</html>
