<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <style>
        .right {
            float: right;
        }
        .left{
            float: left;
        }
    </style>
    <embed src="追梦赤子心.mp3" loop="true" autostar="true" width="0" height="0" />
</head>

<body style="background: lightseagreen;color: whitesmoke;">
<img class="right" src="../../images/xinxi.png">
<span class="left" style="font-size: 15pt;">管理员：<span style="font-size: 20pt">洋洋</span></span>

<h1 style="text-align: center; "> 小奉天儿 网络图书商城后台管理</h1>

<span  style="font-size: 15pt; color: crimson">累计销量:${sales}本 </span>

</body>
</html>
