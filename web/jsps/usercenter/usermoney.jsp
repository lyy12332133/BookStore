<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <style>
        label{
            color: red;
        }
        input{
            width: 100px;
        }
        span{
            color: red;
        }
        body{
            background-image: url("../../images/back.jpg");
            text-align: center;
        }
        a{
            text-decoration: none;
            font-size: 20px;
        }
        h1{
            text-align: center;
            color: cornflowerblue;
        }
    </style>
</head>
<body>
<h1>我的钱包</h1>
<hr>
<h2>您的余额:<span> ${money} </span>元</h2>

<br>
<br>
<br>

<form id="form" action="${pageContext.request.contextPath}/UserServlet">
    <input type="text" name="money">
    <label>${msg}</label>
    <input type="hidden" name="method" value="recharge">
    <br>
    <a href="javascript:document.getElementById('form').submit();">充值</a>
</form>

</body>
</html>