<%@ page import="bean.User" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS-PC
  Date: 2020/2/12
  Time: 22:50
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
             margin-left: 900px;
             font-size: 20px;
        }
        .a1{
            font-size: 22px;
        }
        .div1{
            margin-left: 70px;
            font-size: 20px;
            margin-top: 10px;
        }
        input{
            width: 400px;
            height: 30px;
        }
        button{
            width: 60px;
            height: 30px;
        }
        .label1{
            font-size: 19px;
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
    <form action="returnStart" method="post">
        <a id="a1">${username}，欢迎！！！</a><button type="submit" style="width: 60px;height: 30px;border-radius: 10px">返回</button>
    </form>
    <%
        }
    %>
</div>
<hr/>
<div>
    <a class="a1">结算：<label class="label1">（可以修改地址，不修改则默认地址）</label></a>
</div>
    <%
        String drugName = (String) request.getAttribute("drug");
        int piece = (int) request.getAttribute("piece");
        double money = (double) request.getAttribute("money");
        User user = (User) request.getAttribute("user");
        if(drugName!=null&&piece!=0&&money!=0.0&&user!=null){
            request.setAttribute("drug",drugName);
            request.setAttribute("piece",piece);
            request.setAttribute("money",money);
            request.setAttribute("user",user);
     %>
        <div class="div1">
            <p><label>1、商品名称：</label><a>${drug}</a></p>
            <p><label>2、购买数量：</label><a>${piece}</a></p>
            <p><label>3、需支付：</label><a>${money}元</a></p>
               <label>4、地址：</label>
               <input id = "input1" type="text" name="address" placeholder="${user.address}"/><button type="submit" id = "button1">修改</button>
        </div>
        <div class="div1">
            <a>5、支付方式：<span style="color: red">（支付时请备注昵称：${username}）</span></a>
            <div style="width:600px;margin-left:30px;margin-top: 20px;">
                <form action="order" method="post">
                    <input type="text" name="time" hidden id = "date"/>
                    <input type="text" name = "orderAddress" id = "input2" hidden />
                    <input type="text" name = "username" value="${username}" hidden />
                    <input type="text" name="orderDrug" value="${drug}" hidden />
                    <input type="text" name = "orderPiece" value="${piece}" hidden />
                    <a>如已支付请点击：</a>
                    <button type="submit" style="width: 80px;height: 32px;border-radius: 10px">已支付</button>
                </form>
                <script>
                    $(document).ready(function () {
                        var date = new Date();
                        var year = date.getFullYear();
                        var month = date.getMonth()+1;
                        var day = date.getDate();
                        var hour = date.getHours();
                        var minute = date.getMinutes();
                        var second = date.getSeconds();
                        var input = $('#date');
                        input.val(year+"年"+month+"月"+day+"号"+hour+"时"+minute+"分"+second+"秒");
                    });
                </script>
                <script>
                    var button1 = $('#button1');
                    var input1 = $('#input1');
                    var input2 = $('#input2');
                    button1.click(function () {
                         input2.val(input1.val());
                         alert("已修改，支付完未保证准确发货请点击已支付");
                    })
                </script>

            </div>
            <a style="display:inline-block;margin-top: 10px;margin-left: 30px">微信支付：</a>
            <br/>
            <img src="/view/money/wei.jpg" style="width: 300px;height: 250px;margin-left: 100px;margin-top: 10px"/>
            <br/>
            <a style="margin-left: 30px">支付宝支付：</a>
            <br/>
            <img src="/view/money/zhi.jpg" style="width: 300px;height: 250px;margin-left: 100px;margin-top: 10px"/>
        </div>
    <%
        }
    %>
</body>
</html>
