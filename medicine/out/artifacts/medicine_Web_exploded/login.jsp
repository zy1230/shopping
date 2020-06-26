<%--
  Created by IntelliJ IDEA.
  User: ASUS-PC
  Date: 2019/11/24
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <style>
        label{
            width: 60px;
            height: 40px;
            display: inline-block;
            background-color: white;
        }
        .div1{
            width: 500px;
            height: 400px;
            margin: 100px auto;
            border: 1px #ededf7 solid;
            background-image: url("/view/3.jpeg");
        }
        .div3{
            margin-left: 100px;
            margin-top: 120px;
        }
        .div5{
            margin-left: 100px;
            margin-top: 15px;
        }
        input{
            width: 300px;
            height: 40px;
        }
        button{
            margin-top: 20px;
            width: 300px;
            height: 40px;
            background-color: #3f89ec;
            border: 1px #3f89ec solid;
            color: white;
            font-size: 18px;
        }
    </style>
</head>
<body>
    <%
        String username = (String) request.getAttribute("username");
        if(username!=null){
            request.setAttribute("username",username);
        }
    %>
    <div class="div1">
        <form action="login" method="post">
            <div class="div3">
                <input type="text" name="login" value="${username}" placeholder="登录名/手机号码" autocomplete="off"/>
            </div>
            <div class="div5">
                <input type="password" name="upass" placeholder="密码" autocomplete="off"/>
            </div>
            <button type="submit" style="margin-left: 100px;">登录</button>
        </form>
    </div>
    <%
        String userNo1 = (String) request.getAttribute("no1");
        if(userNo1!=null){
    %>
    <script>
        alert("请输入正确的密码");
    </script>
    <%
        }
    %>
</body>
</html>
