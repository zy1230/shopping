<%--
  Created by IntelliJ IDEA.
  User: ASUS-PC
  Date: 2020/1/31
  Time: 15:39
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
        .div2{
            margin: 5px auto;
            width: 200px;
            font-size: 25px;
        }
        a{
            font-size: 20px;
        }
        .div3{
            margin-top: 20px;
            margin-left: 50px;
            width: 500px;
        }
        .label1{
            display: inline-block;
            width: 60px;
            line-height: 30px;
        }
        input{
            width: 260px;
            height: 30px;
        }
        button{
            width: 50px;
            height: 25px;
            line-height: 25px;
        }
        .div4{
            margin-top: 10px;
            margin-left: 50px;
            width: 500px;
        }
        .label2{
            width: 115px;
            display: inline-block;
            line-height: 30px;
        }
    </style>
    <script src="jqu/jquery.min.js"></script>
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

    <div class="div2">管理员管理</div>
    <div>
        <a>1、添加管理员</a>
        <form action="adminster" method="post">
            <div class="div3">
                <div style="margin-top: 5px">
                    <label class="label1">登录名:</label>
                    <input type="text" name="username" required pattern=".*@$" placeholder="登录名结尾加上@">
                </div>
                <div style="margin-top:5px;margin-bottom: 10px">
                    <label class="label1">密码:</label>
                    <input type="text" name="password" required pattern="(?![0-9])[A-Za-z0-9]{6,20}$" placeholder="6-20字符，包含大小写字母及数字">
                </div>
                <button type="submit" style="margin-left: 70px;margin-bottom: 5px;width: 60px;height: 30px">提交</button>
                <button type="reset" style="margin-left: 20px;margin-bottom: 5px;width: 60px;height: 30px">清空</button>
            </div>
        </form>
        <%
            String a = (String) request.getAttribute("result");
            if(a!=null){
        %>
        <script>
            var input0 = $('input')[0];
            var input1 = $('input')[1];
            input0.val("");
            input1.val("");
        </script>
        <a style="display: inline-block;margin-left: 50px;margin-bottom: 10px;color: red">添加成功！</a>
        <%
            }
        %>
    </div>
    <div style="margin-bottom: 10px">
        <a>2、修改管理员密码</a>
        <form action="modify" method="post">
            <div class="div4">
                <div style="margin-top: 5px">
                    <label class="label2">请输入登录名：</label>
                    <input type="text" required="required" placeholder="请输入正确的登录名" name="username">
                </div>
                <div style="margin-top: 5px">
                    <label class="label2">新密码：</label>
                    <input id="input2" name="password" type="text" required pattern="(?![0-9])[A-Za-z0-9]{6,20}$" placeholder="6-20字符，包含大小写字母及数字">
                </div>
                <button type="submit" style="margin-left: 140px;margin-top: 10px;width: 60px;height: 30px">修改</button>
                <button type="reset" style="margin-left: 30px;margin-top: 10px;width: 60px;height: 30px">清空</button>
            </div>
        </form>
        <%
            String b = (String) request.getAttribute("result1");
            if(b!=null){
        %>
        <script>
            var input2 = $('input')[2];
            var input3 = $('input')[3];
            input2.val("");
            input3.val("");
        </script>
        <a style="display: inline-block;margin-left: 50px;margin-bottom: 10px;color: red">修改成功！</a>
        <%
            }
        %>
    </div>
    <div>
        <a>3、删除管理员</a>
        <form action="delete" method="post">
            <div style="margin-left: 50px;margin-top: 10px">
                <label>管理员登录名:</label>
                <input type="text" name="username" required="required" placeholder="请输入正确的登录名"><button type="submit" style="height: 30px;width: 60px;height: 30px">删除</button>
            </div>
        </form>
        <%
            String c = (String) request.getAttribute("result2");
            if(c!=null){
        %>
        <script>
            var input4 = $('input')[4];
            input4.val("");
        </script>
        <a style="display: inline-block;margin-left: 50px;margin-bottom: 10px;color: red">删除成功！</a>
        <%
            }
        %>
    </div>
<div style="margin-top: 10px">
    <a style="font-size: 20px">4、返回管理员页面</a>
    <div style="margin-left: 50px;margin-top: 10px">
        <form action="returnMan" method="post">
            <button type="submit" style="width: 60px;height: 30px;border-radius: 10px;">返回</button>
        </form>
    </div>
</div>
<div style="height: 200px">

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
