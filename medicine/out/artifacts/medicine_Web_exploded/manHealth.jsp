<%@ page import="java.util.List" %>
<%@ page import="java.rmi.registry.LocateRegistry" %>
<%@ page import="bean.Health" %><%--
  Created by IntelliJ IDEA.
  User: ASUS-PC
  Date: 2020/2/5
  Time: 13:54
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
            width: 200px;
            margin: 5px auto;
            font-size: 25px;
        }
        .td1{
            width: 750px;
            font-size: 20px;
        }
        .td2{
            width: 200px;
            font-size: 20px;
        }
        .b1{
            margin-left: 260px;
        }
        .b2{
            margin-left: 50px;
        }
        .b3{
            margin-left: 70px;
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
<div class="div1">
    健康知识管理
</div>
<div>
    <a style="font-size: 22px">
        1、添加健康知识
    </a>
    <div>
        <form action="insert_health" method="post">
            <div>
                <a style="font-size:18px;display: inline-block;margin-left: 50px;margin-top: 10px">
                    健康知识：
                </a><input type="text" name="health" style="width:300px;height: 30px" placeholder="不能为空" required="required">
            </div>
            <div style="margin-bottom: 10px;margin-top: 5px">
                <a style="font-size:18px;display: inline-block;margin-left: 50px;">
                    是否展示：
                </a><input type="text" name="show" style="width: 300px;height: 30px" placeholder="请填写是与否" required="required">
            </div>
            <button type="submit" style="height: 30px;width: 55px;border-radius: 10px;margin-left: 150px">提交</button>
            <button type="reset" style="height: 30px;width: 60px;border-radius: 10px">清空</button>
        </form>
    </div>
    <%
        String insert_heal1 = (String) request.getAttribute("insert_heal1");
        if(insert_heal1!=null){
    %>
          <a style="margin-left: 50px;color: red;font-size: 20px">
              已添加
          </a>
    <%
        }
    %>
    <%
        String no_show = (String) request.getAttribute("no_show");
        if(no_show!=null){
    %>
           <script>
               alert("请正确填写是或否");
           </script>
    <%
        }
    %>
</div>
<div>
    <a style="font-size: 22px">
        2、管理健康知识
    </a>
    <form action="allHealth" method="post">
        <a style="margin-top:10px;display: inline-block;margin-left: 50px;font-size: 18px">
            点击查看所有健康知识:
        </a>
        <button type="submit" style="width: 60px;height: 30px;border-radius: 10px">查看</button>
    </form>
    <div style="margin-left: 50px;margin-bottom: 10px">
        <a style="color: red;font-size: 18px">注：健康知识网站首页只展示3条，多余3条则展示靠前的前3条</a>
    </div>
    <table border="1" style="margin-left: 50px">
        <tr>
            <td class="td1">
                <b class="b1">健康知识</b>
            </td>
            <td class="td2">
                <b class="b2">是否展示</b>
            </td>
            <td class="td2">
                <b class="b3">操作</b>
            </td>
        </tr>
        <%
            List<Health> all_heal = (List<Health>) request.getAttribute("all_heal");
            if(all_heal!=null){
                Health health;
                for(int i = 0;i<all_heal.size();i++){
                    health = all_heal.get(i);
                    request.setAttribute("health",health);
        %>
        <tr>
            <td class="td1">
                ${health.health}
            </td>
            <td class="td2">
                <%
                    if(health.getShow()==1){
                %>
                <a style="margin-left: 45px">已经展示</a>
                <%
                    }
                %>
                <%
                    if(health.getShow()!=1){
                %>
                <a style="margin-left: 45px">没有展示</a>
                <%
                    }
                %>
            </td>
            <td class="td2">
               <form action="deleteHeal" method="post">
                   <input type="text" name="id" value="${health.heal_id}" hidden/>
                   <button type="submit" style="margin-left:55px;margin-top:20px;height: 30px;width: 55px">删除</button>
               </form>
                <%
                    if(health.getShow()!=1){
                %>
                <form action="updateShow" method="post">
                    <input type="text" name = "id" value="${health.heal_id}" hidden />
                    <button type="submit" style="width: 60px;height: 30px;margin-left: 55px;">展示</button>
                </form>
                <%
                    }
                %>
                <%
                    if(health.getShow()==1){
                %>
                <form action="updateNoShow" method="post">
                    <input type="text" name = "id" value="${health.heal_id}" hidden />
                    <button type="submit" style="width: 100px;height: 30px;margin-left: 40px;">取消展示</button>
                </form>
                <%
                    }
                %>
            </td>
        </tr>
        <%
           }
        %>
        <%
            }
        %>
    </table>
    <%
        String delete_heals = (String) request.getAttribute("delete_heals");
        if(delete_heals!=null){
    %>
    <a style="margin-left: 50px;color: red;font-size: 20px">删除成功</a>
    <%
        }
    %>
    <%
        String updateShow = (String) request.getAttribute("updateShow");
        if(updateShow!=null){
    %>
    <script>
        alert("展示状态已更改");
    </script>
    <%
        }
    %>
    <%
        String noUpdate = (String) request.getAttribute("noUpdate");
        if(noUpdate!=null){
    %>
         <script>
             alert("请稍后再试试");
         </script>
    <%
        }
    %>
    <%
        String updateNoShow = (String) request.getAttribute("updateNoShow");
        if(updateNoShow!=null){
    %>
        <script>
            alert("取消成功");
        </script>
    <%
        }
    %>
    <%
        String upNoShow = (String) request.getAttribute("upNoShow");
        if(upNoShow!=null){
    %>
        <script>
            alert("请稍后再试试");
        </script>
    <%
        }
    %>
</div>
<div style="margin-top: 20px;">
    <a style="font-size: 20px">3、返回管理页面：</a>
    <div style="margin-left: 50px;margin-top: 10px">
        <form action="returnMan" method="post">
            <button type="submit" style="width: 60px;height: 30px;border-radius: 10px;">返回</button>
        </form>
    </div>
</div>
<div style="height: 200px"></div>
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
