<%@ page import="java.util.List" %>
<%@ page import="bean.Comment" %><%--
  Created by IntelliJ IDEA.
  User: ASUS-PC
  Date: 2020/2/15
  Time: 10:15
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
        td{
            width: 200px;
        }
        table{
            margin-left: 80px;
        }
        .a1{
            margin-left: 60px;
        }
        .a2{
            font-size: 20px;
            margin-left: 50px;
        }
        span{
            font-size: 20px;
        }
    </style>
</head>
<body>
<%
    String username = (String) session.getAttribute("username");
    if(username==null){
%>
<div>
    <p>请先登录</p>
</div>
<%
}else if(username.contains("@")){
    session.setAttribute("username",username);
%>
<div class="div3">
    <a class="a2">${username}</a>
    <span></span>
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

<div>
    <form action="manComm" method="post">
        <a style="font-size: 20px">1、查看所有评价：</a>
        <button type="submit" style="width: 60px;height: 30px;border-radius: 10px">查看</button>
    </form>
</div>

<%
    String no_comm = (String) request.getAttribute("no");
    if(no_comm!=null){
%>
          <div>
              <a>暂时没有评价</a>
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
            <a class="a1">用户评价</a>
        </td>
        <td>
            <a class="a1">评论时间</a>
        </td>
        <td>
            <a class="a1">用户昵称</a>
        </td>
        <td>
            <a style="margin-left: 80px;">操作</a>
        </td>
    </tr>
    <%
        List<Comment> list = (List<Comment>) request.getAttribute("list_comm");
        if(list!=null&&list.size()>0){
            Comment comment;
            for(int i = 0;i<list.size();i++){
                comment = list.get(i);
                request.setAttribute("comm",comment);
    %>
                <tr>
                    <td>
                        <a>${comm.drug}</a>
                    </td>
                    <td>
                        <a>${comm.comment}</a>
                    </td>
                    <td>
                        <a>${comm.time}</a>
                    </td>
                    <td>
                        <a>${comm.username}</a>
                    </td>
                    <td>
                        <div style="margin-left: 60px;margin-top: 15px">
                            <form action="deleComm" method="post">
                                <input type="text" name = "comm_id" value="${comm.id}" hidden />
                                <button type="submit" style="width: 60px;height: 30px;border-radius: 10px">删除</button>
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
    String dele = (String) request.getAttribute("dele_comm");
    if(dele!=null){
 %>
       <script>
           alert("删除成功");
       </script>
<%
    }
%>

<div style="margin-top: 20px;">
    <a style="font-size: 20px">2、返回管理页面：</a>
    <div style="margin-left: 80px;margin-top: 10px">
        <form action="returnMan" method="post">
            <button type="submit" style="width: 60px;height: 30px;border-radius: 10px;">返回</button>
        </form>
    </div>
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
