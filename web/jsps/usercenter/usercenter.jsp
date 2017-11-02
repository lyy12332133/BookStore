<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style>
        a {
            text-decoration: none;
            font-size: 20px;
        }

        div {
            width: 150px;
            background: rgba(118, 180, 237, 0.93);
            padding: 5px;
            margin-bottom: 10px;
        }
        body{
            background-image: url("../../images/back.jpg");
            text-align: center;
        }
        h1{
            color: cornflowerblue;
        }

    </style>
</head>
<body>

<div><a href="${pageContext.request.contextPath}/UserServlet?method=loadInfo">修改个人信息</a></div>
<div><a href="${pageContext.request.contextPath}/UserServlet?method=loadWallet">我的钱包</a></div>
<div><a href="../user/updateps.jsp">修改密码</a></div>
</body>
</html>