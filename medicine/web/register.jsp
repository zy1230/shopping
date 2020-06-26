<%@ page import="java.util.List" %>
<%@ page import="bean.User" %><%--
  Created by IntelliJ IDEA.
  User: ASUS-PC
  Date: 2019/11/24
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        label{
            width: 80px;
            display: inline-block;
            line-height: 29px;
            color: black;
            border: 1px white solid;
            background-color: white;
        }
        .div1{
            width: 600px;
            height: 420px;
            margin: 10px auto;
            border: 1px white solid;
            background-image: url("/view/li5.jpg");
        }
        .div2{
            margin-bottom: 5px;
            margin-left: 120px;
        }
        input{
            width: 260px;
            height: 35px;
        }
        button{
            display: inline-block;
            width: 60px;
            height: 30px;
        }
        .div3 {
            width: 300px;
            margin: 10px 43%;
        }
    </style>
    <script src="jqu/jquery.min.js"></script>
</head>
<body>
         <div class="div3">
             <h3>快来注册吧！</h3>
         </div>
     <%
         User user = (User) request.getAttribute("user1");
         if(user!=null){
             request.setAttribute("user",user);
         }
     %>
     <div class="div1">
         <form action="register" method="post">
             <div class="div2"  style="margin-top: 100px">
                 <label>登录名：</label>
                 <input type="text" id = "username" name="username" value="${user.username}" required pattern="(?![0-9])[A-Za-z0-9]{6}$" placeholder="必须包含大小写字母、数字，6字符" />
                 <%
                     String no = (String) request.getAttribute("no");
                     if(no!=null){
                         request.setAttribute("no",no);
                 %>
                 <b style="color: red">${no}</b>
                 <%
                     }
                 %>
             </div>
             <div class="div2">
                 <label>密码：</label>
                 <input type="password" name="password" value="${user.upass}" required pattern="(?![0-9])[A-Za-z0-9]{6,20}$" placeholder="必须包含大小写字母、数字，6-20字符" />
             </div>
             <div class="div2">
                 <label>电话号码：</label>
                 <input type="text" required pattern="[0-9]{11}$" name="phone" placeholder="请填写正确的电话号码哦" value="${user.phone}"/>
                 <%
                     String no1 = (String) request.getAttribute("no1");
                     if(no1!=null){
                         request.setAttribute("no1",no1);
                 %>
                 <b style="color: red">${no1}</b>
                 <%
                     }
                 %>
             </div>
             <div class="div2">
                 <label>姓名：</label>
                 <input type="text" required="required" name="uname" placeholder="不能为空" value="${user.uname}"/>
             </div>

             <div class="div2"  style="margin-bottom: 10px">
                 <label>家庭住址：</label>
                 <input type="text" name="address" required="required" placeholder="不能为空" value="${user.address}"/>
             </div>

             <button type="submit" style="margin-left: 220px">注册</button>
             <button type="reset" style="margin-left: 20px">清空</button>
         </form>
     </div>
</body>
</html>
