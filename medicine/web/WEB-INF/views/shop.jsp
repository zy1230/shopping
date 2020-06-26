<%@ page import="bean.Shop" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ASUS-PC
  Date: 2020/2/11
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src = "/jqu/jquery.min.js"></script>
    <style>
        body{
            background-color: #eee7d5;
        }
        #a1{
            font-size: 20px;
        }
        .a1{
            margin-left: 110px;
        }
        table{
            margin-top: 10px;
            margin-left: 50px;
        }
        td{
            width: 300px;
            height: 50px;
            font-size: 18px;
        }
        button{
            width: 60px;
            height: 30px;
            font-size: 18px;
            margin-left: 80px;
        }
    </style>
</head>
<body>
<div class="div1">
    <%
        String username = (String) session.getAttribute("username");
        if(username==null){
    %>
    <a href="${pageContext.request.contextPath}/login.jsp" style="font-size: 20px">登录</a>
    <a href="${pageContext.request.contextPath}/register.jsp" style="font-size: 20px">注册</a>
    <%
        }
    %>
    <%
        if(username!=null){
            session.setAttribute("username",username);
    %>
    <div style="margin-top: 10px;margin-left: 70%">
        <form action="returnStart" method="post">
            <a id="a1">${username}，欢迎！！！</a><button type="submit" style="width: 60px;height: 30px;border-radius: 10px;margin-left: 0px">返回</button>
        </form>
    </div>
    <%
        }
    %>
</div>
<hr/>
<b style="font-size: 20px">心仪的商品:</b>
<table border="1">
    <tr>
        <td><a class="a1">商品名称</a></td>
        <td><a class="a1">购买数量</a></td>
        <td><a class="a1">总价格</a></td>
        <td><a style="margin-left: 130px">操作</a></td>
    </tr>
<%
    List<Shop> list = (List<Shop>) request.getAttribute("list1_shop");
    Shop shop;
    if(list!=null&&list.size()>0){
        for(int i = 0;i<list.size();i++){
              shop = list.get(i);
              request.setAttribute("shop_all",shop);
              double all_price = shop.getPiece()*shop.getPrice();
              request.setAttribute("all_price",all_price);
%>
    <tr>
        <td>
            <a style="margin-left: 120px">${shop_all.drug}</a>
        </td>
        <td>
            <a style="margin-left: 125px">${shop_all.piece}</a>
        </td>
        <td>
            <a style="margin-left: 110px">${all_price}元</a>
        </td>
        <td>
            <form action="money" method="post">
                <input type="text" name="username" value="${username}" hidden />
                <input type="text" name = "money" value="${all_price}" hidden />
                <input type="text" name="drugName" value="${shop_all.drug}" hidden />
                <input type="text" name="piece" value="${shop_all.piece}" hidden />
                <button type="submit" style="margin-top: 15px">结算</button>
            </form>
            <form action="shopDelete" method="post">
                <input type="text" name="username" value="${username}" hidden />
                <input type="text" name = "shopId" value="${shop_all.id}" hidden />
                <button type="submit">移除</button>
            </form>
        </td>
    </tr>
<%
        }
    }
%>
</table>
<%
    String result = (String) request.getAttribute("result");
    if(result!=null){
%>
        <script>
            alert("移除成功")
        </script>
<%
    }
%>
<div style="margin-top: 15px">
    <b style="font-size: 20px">点击查看订单：</b>
    <div style="margin-top: 10px">
        <form action="seeOrder" method="post">
            <input type="text" name = "username" value="${username}" hidden />
            <button type="submit" style="width: 110px;height: 30px;border-radius: 10px">查看订单</button>
        </form>
    </div>
</div>
</body>
</html>
