<%@ page import="java.util.List" %>
<%@ page import="bean.Chat" %><%--
  Created by IntelliJ IDEA.
  User: ASUS-PC
  Date: 2020/1/31
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
            font-size: 20px;
        }
        td{
            width: 400px;
            font-size: 20px;
        }
        input{
            width: 330px;
            height: 35px;
            margin-top: 15px;
        }
        .button1{
            width: 60px;
            height: 35px;
        }
        .b1{
            margin-left: 160px;
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
<div style="margin: 10px auto;line-height: 30px;width: 120px;font-size: 25px">
    会话管理
</div>

<div>
    <form action="/getChat" method="post">
        <a style="font-size: 20px">1、点击展示会话：</a>
        <button type="submit" style="width: 60px;height: 25px;margin-top: 10px">展示</button>
    </form>
</div>
<%
    List<Chat> list = (List<Chat>) request.getAttribute("list");
    if(list==null){
%>
<div>
    <b>
        用户对话已全部回复，暂时没有会话显示
    </b>
</div>
<%
    }else {
%>
<div style="width: 800px;margin-top: 10px;margin-bottom: 10px">
    <b>
        最多显示10条数据，点击更新即可更新会话，回复新的会话
    </b>
</div>
<table border="1">
    <tr>
        <td><b class="b1">用户</b></td>
        <td><b class="b1">会话</b></td>
        <td><b class="b1">回复</b></td>
    </tr>
    <%
    Chat chat;
    for (int i = 0;i<list.size();i++) {
    %>
    <tr>
        <td>
            <%
                chat = list.get(i);
                session.setAttribute("user",chat.getUser());
                pageContext.setAttribute("chat",chat.getChat());
            %>
            ${user}
        </td>
        <td>
            ${chat}
        </td>
        <td>
             <form action="answer" method="post">
                 <input type="text" name="answer" /><input type="text" name="user" value="${user}" hidden><button type="submit" class="button1">回复</button>
             </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
<div style="margin-top: 10px">
    <form>
        <button type="submit" style="width:60px;height: 35px;background-color: cornflowerblue">更新</button>
    </form>
</div>


<%
    }
%>

<div style="margin-top: 15px">
    <a style="font-size: 20px">2、返回管理员页面：</a>
    <div style="margin-left: 180px;margin-top: 10px">
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
