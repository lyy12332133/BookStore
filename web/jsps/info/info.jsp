<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>编辑客户</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/jquery/jquery.datepick.css'/>">
    <script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>

    <script type="text/javascript">
        $(function() {
            $("#birthday").datepick({dateFormat:"yy-mm-dd"});
        });

    </script>
    <style>
        body{
            background-image: url("../../images/back.jpg");
            font-size: 20px;
        }
        .dd{
            width: 300px;
            height: 50px;
            font-size: 16px;
        }
        h1{
            color: cornflowerblue;
            text-align: center;
            font-size: 28px;
        }


    </style>
</head>

<body>
<h1 >编辑信息</h1>
<hr>
<form action="${pageContext.request.contextPath}/UserServlet" >
    <table border="0" align="center" width="40%" style="margin-left: 100px;">
        <tr>
            <td width="100px">昵称</td>
            <td width="40%">
                <input class="dd" type="text" name="name" value="${userInfo.name}" />
            </td>
        </tr>
        <tr>
            <td>性别</td>
            <td>
                <input type="radio" name="gender" value="男" id="male"<c:if test="${userInfo.gender=='男'}"> checked</c:if>/>
                <label for="male">男</label>

                <input type="radio" name="gender" value="女" id="female"<c:if test="${userInfo.gender=='女'}"> checked</c:if>/>
                <label for="female">女</label>
            </td>
        </tr>
        <tr>
            <td>生日</td>
            <td>
                <input class="dd" type="text" name="birthday" id="birthday" readonly="readonly" value="${userInfo.birthday}"/>
            </td>
        </tr>
        <tr>
            <td>手机</td>
            <td>
                <input class="dd" type="text" name="cellphone" value="${userInfo.cellphone}"/>
            </td>
        </tr>
        <tr>
            <td>收货地址</td>
            <td>
                <input class="dd" type="text" name="address" value="${userInfo.address}">
            </td>
        </tr>
        <tr>
            <td>
                <input style="width: 150px; height: 50px; font-size: 15px" type="reset" value="重置"/>
            </td>
            <td>
                <input type="hidden" name="method" value="addInfo">
                <input style="width: 300px; height: 50px; font-size: 15px" type="submit" value="修改资料"/>
            </td>
            <td>&nbsp;</td>
        </tr>
    </table>
</form>
</body>
</html>