<%--
  Created by IntelliJ IDEA.
  User: ASUS-PC
  Date: 2020/1/30
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="jqu/jquery.min.js"></script>
    <style>
        body{
            background-color: #eee7d5;
        }
        .div1{
            margin: 100px auto;
            width: 900px;
            height: 430px;
            border:1px black solid;
        }
        .div2{
            float: left;
            margin-top: 100px;
            margin-left: 50px;
            border-radius: 35px;
            width: 150px;
            height: 60px;
            border:1px black solid;
            line-height: 60px;
            text-align: center;
        }
        .div3{
            margin-top: 18px;
        }
        span{
            font-size: 20px;
        }
        .a1{
            font-size: 20px;
        }
        .a2{
            font-size: 20px;
            text-decoration: none;
            color: black;
        }
        .out{
            border:0;
            margin-left: 3%;
            outline: none;
            background-color: transparent;
            font-size: 18px;
            color: cornflowerblue;
            display: inline-block;
        }
    </style>
</head>
<body>
    <%
        String username = (String) session.getAttribute("username");
        String identity = (String) session.getAttribute("identity");
        if(username==null&&identity==null){
    %>
    <div>
        <p>请先登录</p>
    </div>
    <%
        }else if(username!=null&&identity!=null&&username.contains("@")&&identity.contains("管理员")){
            session.setAttribute("username",username);
    %>
    <div class="div3">
    <form action="getOut" method="post">
        <a class="a1">${username}</a>
        <span></span>
        <button type="submit" class="out">退出登录</button>
    </form>
    </div>
    <hr/>
    <script>
        $(document).ready(function (){
            var date = new Date();
            var date1 = date.getHours();
            var a = $('span')[0];
            if(date1<10){
                a.innerText = "早上好!";
            }else if(date1<18){
                a.innerText = "下午好!";
            }else if(date1<23){
                a.innerText = "晚上好!";
            }else{
                a.innerText = "夜深了，注意休息"
            }
        })
</script>
    <div class="div1">
        <div class="div2"><a href="${pageContext.request.contextPath}/manDrug.jsp" class="a2">药品管理</a></div>
        <div class="div2"><a href="${pageContext.request.contextPath}/manOrder.jsp" class="a2"> 订单处理</a></div>
        <div class="div2"><a href="${pageContext.request.contextPath}/manComment.jsp" class="a2">评价管理</a></div>
        <%
            if(identity.equals("超级管理员")){
        %>
        <div class="div2"><a href="${pageContext.request.contextPath}/manAdmin.jsp" class="a2">管理员管理</a></div>
        <%
            }
        %>
        <div class="div2"><a href="${pageContext.request.contextPath}/manChat.jsp" class="a2">会话管理</a></div>
        <div class="div2"><a href="${pageContext.request.contextPath}/manHealth.jsp" class="a2">健康知识管理</a></div>
    </div>
    <%
        }else {
    %>
    <div class="div3">
        <p>您不是管理员，无权查看</p>
    </div>
    <%
        }
    %>
</body>
</html>