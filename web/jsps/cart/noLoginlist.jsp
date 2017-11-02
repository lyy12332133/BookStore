<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>购物车列表</title>

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
            background-image: url("../../images/back.jpg");
        }

        * {
            font-size: 11pt;
        }

        div {
            margin: 20px;
            border: solid 2px gray;
            width: 106px;
            height: 150px;
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
        a{
            text-decoration: none;
        }

        tr,th,td{
            border: 0px;
        }
    </style>
    <script src="../../jquery/JQ.js"></script>
    <script>
        $(function () {
            // 单选, 全选
            var checkAll = document.getElementById("checkAll");
            var tbody = document.getElementById("tbody");
            var checkOne = tbody.getElementsByClassName("checkOne");
            checkAll.onclick = function () {
                for (var i = 0; i < checkOne.length; i++) {
                    checkOne[i].checked = this.checked;
                    getMoney();
                }
            };


            for (var i = 0; i < checkOne.length; i++) {
                checkOne[i].onclick = function () {
                    var bool = true;
                    for (var j = 0; j < checkOne.length; j++) {
                        if (checkOne[j].checked === false) {
                            bool = false;
                        }
                    }
                    checkAll.checked = bool;
                    getMoney();
                };

            }


            // 数量加
            $(".up").click(function () {
                var count = $(this).parent().prev().find("input").val();
                count = parseInt(count) + 1;
                $(this).parent().prev().find("input").val(count);
                getMoney();
            });


            // 数量减
            $(".down").click(function () {
                var count = $(this).parent().next().next().find("input").val();
                if (count == 1) {
                    count = parseInt(count);
                } else {
                    count = parseInt(count) - 1;
                }
                $(this).parent().next().next().find("input").val(count);
                getMoney();
            });

            // 计算总价
            var trs = document.getElementsByClassName("trs");
            function getMoney() {
                var count = 0;
                var price = 0;
                var sum = 0;
                for (var i = 0; i < trs.length; i++) {
                    if (trs[i].getElementsByTagName("td")[0].getElementsByTagName("input")[0].checked) {
                        price = parseFloat(trs[i].getElementsByTagName("td")[4].innerHTML);
                        count = parseFloat(trs[i].getElementsByTagName("input")[2].value);
                        sum = price * count + sum;

                    }
                }
                $("#sum").text(sum.toFixed(2));
                $("#inputSum").val(sum.toFixed(2));
            }


        })
    </script>
</head>
<body>
<h1>购物车 ${msg}</h1>
<form action="${pageContext.request.contextPath}/jsps/user/login.jsp" id="form">
    <table border="1" width="100%" cellspacing="0" style="text-align: center">
        <tr>
            <td colspan="8" align="right" style="font-size: 15pt; font-weight: 900">
                <a href="<c:url value="/CartServlet?method=clearNoLogin"/> ">清空购物车</a>
            </td>
        </tr>
        <tr>
            <th><input id="checkAll" type="checkbox">&nbsp;&nbsp;全选</th>
            <th>图片</th>
            <th>书名</th>
            <th>作者</th>
            <th>单价</th>
            <th>数量</th>
            <th>小计</th>
            <th>操作</th>
        </tr>

        <tbody id="tbody">
        <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
            <tr class="trs">
                <td><input class="checkOne" type="checkbox" value="${cartItem.value.book.bid}"></td>
                <td>
                    <div><img style="width: 100% ; height: 100%" src="<c:url value='${cartItem.value.book.image}'/>"/>
                    </div>
                </td>
                <td>${cartItem.value.book.bname}</td>
                <td>${cartItem.value.book.author}</td>
                <td>${cartItem.value.book.price}元</td>
                <td>
                    <span><button type="button" class="down">-</button></span>
                    <span><input type="hidden" name="bid" value="${cartItem.value.book.bid}"></span>
                    <span><input id="count" style="width: 30px" type="text"
                                 value="${cartItem.value.count}"/></span>
                    <span><button type="button" class="up">+</button></span>
                </td>
                <td>${(cartItem.value.count)*(cartItem.value.book.price)}元</td>
                <td><a href="<c:url value="/CartServlet?method=deleteNoLogin&bid=${cartItem.value.book.bid}"/> ">删除</a></td>
            </tr>
        </c:forEach>
        </tbody>
        <tr>
            <td colspan="8" align="right" style="font-size: 15pt; font-weight: 900">
                合计<span id="sum">0</span>元
            </td>
        </tr>
        <tr>
            <td colspan="8" align="right" style="font-size: 15pt; font-weight: 900">
                <a id="buy" href="javascript:document.getElementById('form').submit();"></a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
