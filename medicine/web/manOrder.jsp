<%@ page import="bean.Order" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ASUS-PC
  Date: 2020/1/31
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <title>Title</title>
    <style>
        body{
            background-color: #eee7d5;
        }
        .div1{
            margin: 100px auto;
            width: 200px;
        }
        p{
            font-size: 40px;
        }
        table{
            margin-top: 10px;
        }
        td{
            width: 200px;
        }
        .a1{
            margin-left: 25%;
        }
        .a2{
            margin-left: 30%;
        }
    </style>
</head>
<body>
<%
    String username = (String) session.getAttribute("username");
    if(username==null){
%>
<div class="div1">
    <p>请先登录</p>
</div>
<%
   }else if(username.contains("@")){
%>


<div style="width: 200px;margin: 10px auto">
    <a style="font-size: 25px">订单处理</a>
</div>
<div>
    <form action="manSeeOrder" method="post">
        <input type="text" name="username" hidden />
        <a style="font-size: 20px">1、查看所有未发货的订单:</a>
        <button type="submit" style="width: 60px;height: 30px;border-radius: 10px">查看</button>
    </form>
</div>
<%
     String no = (String) request.getAttribute("no");
     if(no!=null){
%>
          <div style="margin-top: 10px;margin-left: 50px">
              <a style="color: red;font-size: 20px">暂时没有订单</a>
          </div>
<%
}
%>
<table border="1">
    <tr>
        <td>
            <a class="a1">商品名称</a>
        </td>
        <td>
            <a class="a1">购买数量</a>
        </td>
        <td>
            <a class="a1">用户昵称</a>
        </td>
        <td>
            <a class="a1">用户本名</a>
        </td>
        <td>
            <a class="a1">用户地址</a>
        </td>
        <td>
            <a class="a1">用户电话</a>
        </td>
        <td>
            <a class="a1">支付时间</a>
        </td>
        <td>
            <a class="a1">是否发货</a>
        </td>
    </tr>
    <%
        List<Order> list = (List<Order>) request.getAttribute("list_order");
        if(list!=null&&list.size()>0){
            Order order;
            for(int i = 0;i<list.size();i++){
                order = list.get(i);
                request.setAttribute("order",order);
    %>
    <tr>
        <td>
            <a class="a2">${order.drug}</a>
        </td>
        <td>
            <a class="a2">${order.piece}</a>
        </td>
        <td>
            <a class="a2">${order.username}</a>
        </td>
        <td>
            <a class="a2">${order.uname}</a>
        </td>
        <td>
            <a>${order.address}</a>
        </td>
        <td>
            <a style="margin-left: 10%">${order.phone}</a>
        </td>
        <td>
            <a>${order.time}</a>
        </td>
        <td>
            <div style="margin-left: 30px;margin-top: 15px">
                <form action="manUpState" method="post">
                    <input type="text" name="id" value="${order.id}" hidden />
                    <input type="text" name = "state" value="${order.state}" hidden />
                    <button type="submit" style="width: 100px;height: 30px;border-radius: 10px">确认发货</button>
                </form>
            </div>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
<%
    String state = request.getParameter("state");
    if(state!=null){
%>
        <script>
            alert("成功")
        </script>
<%
    }
%>
<div style="margin-top: 20px;">
    <a style="font-size: 20px">2、返回管理页面：</a>
    <div style="margin-left: 50px;margin-top: 10px">
        <form action="returnMan" method="post">
            <button type="submit" style="width: 60px;height: 30px;border-radius: 10px;">返回</button>
        </form>
    </div>
</div>




<%
    }else{
%>
<div class="div3">
    <p>您不是管理员，无权查看</p>
</div>
<%
    }
%>
</body>
</html>
