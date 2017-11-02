<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>注册</title>

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
            margin: 100px;
            text-align: left;
            width: 800px;
            height: 400px;
        }

        span {
            font-size: 20px;
        }

        #sub {
            width: 260px;
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

<p style="color: red; font-weight: 900">${msg }</p>
<div id="div1">
    <h1>注册</h1>
    <form action="${pageContext.request.contextPath}/UserServlet" method="post">

        <input type="hidden" name="method" value="regist"/>
        用户名：<input id="username" onblur="send()" type="text" name="username" value="${form.username}"/>
        <label>${maps.nameError}<span id="nameError"></span></label>
        <br/>
        密　码：<input type="password" name="password"/>
        <label>${maps.pwdError}</label>
        <br/>
        邮　箱：<input type="text" name="email" value="${form.email}"/>
        <label>${maps.emailError}</label>
        <br/>
        <input id="sub" type="submit" value="注册"/>
    </form>
</div>

<script>
    function createXMLHttpRequest() {
        try {
            return new XMLHttpRequest();//大多数浏览器
        } catch (e) {
            try {
                return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
            } catch (e) {
                try {
                    return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本
                } catch (e) {
                    alert("哥们儿，您用的是什么浏览器啊？");
                    throw e;
                }
            }
        }
    }

    function send() {
        var xmlHttp = createXMLHttpRequest();
        xmlHttp.open("POST","${pageContext.request.contextPath}/NameServlet",true);
        xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        var username = document.getElementById("username").value;
        xmlHttp.send("username="+username);
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
                if (xmlHttp.responseText == "true"){
                    document.getElementById("nameError").innerHTML = "用户名已被注册";
                }else {
                    document.getElementById("nameError").innerHTML = "";
                }
            }
        };
    }
</script>


</body>
</html>
