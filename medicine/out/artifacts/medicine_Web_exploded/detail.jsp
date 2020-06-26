<%@ page import="bean.Drug" %>
<%@ page import="bean.Comment" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ASUS-PC
  Date: 2019/12/14
  Time: 20:24
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
            display: inline-block;
            margin-left: 70%;
            font-size: 20px;
        }
        img{
            width: 300px;
            height: 280px;
        }
        .div1{
            margin-left: 30px;
            margin-top: 20px;
        }
        .a1{
            display: inline-block;
            margin-top: 10px;
            font-size: 22px;
        }
        .a2{
            display: inline-block;
            margin-top: 10px;
            font-size: 22px;
        }
        .a3{
            display: inline-block;
            margin-top: 10px;
            font-size: 22px;
        }
        .a4{
            display: inline-block;
            margin-top: 15px;
            font-size: 22px;
        }
        .label1{
            color: red;
        }
        .label2{
            color: black;
        }
        .a_detail{
            font-size: 20px;
            display: inline-block;
            margin-left:20px;
            margin-top: 8px;
        }
    </style>
</head>
<body>
<div class="div1">
    <%
        String username = (String) session.getAttribute("username");
        if(username==null){
    %>
    <a href="${pageContext.request.contextPath}/login.jsp" style="font-size: 20px;text-decoration: none">登录</a>
    <a href="${pageContext.request.contextPath}/register.jsp" style="font-size: 20px;text-decoration: none">注册</a>
    <a href="start.jsp" style="font-size: 20px;text-decoration: none;">返回</a>
    <hr/>
    <div style="width: 300px;margin: 10px auto">
        <p style="font-size: 22px;color: lightcoral">请先登录哦</p>
    </div>
    <%
        }
    %>
    <%
        if(username!=null){
            session.setAttribute("username",username);
    %>
        <form action="shopAll" method="post">
            <input type="text" name="username" value="${username}" hidden />
            <a id="a1">${username}</a>，欢迎！！！
            <button type="submit" style="width: 80px;height: 30px;border-radius: 10px;font-size: 16px">购物车</button>
        </form>
</div>
    <hr/>

<%
    Drug drug = (Drug) session.getAttribute("detail");
    if(drug!=null){
        session.setAttribute("detail_drug",drug);
%>
        <div class="div1">
            <div style="float: left;">
                <img src="${detail_drug.picture}">
            </div>
            <div style="float: left;margin-left: 15px;">
                <a class="a1">
                    <label class="label2">
                        商品名称:
                    </label>
                    ${detail_drug.m_name}
                </a>
                <br/>
                <a class="a2">
                    <label class="label2">
                        价格:
                    </label>
                    <label class="label1">
                        ￥${detail_drug.price}
                    </label>
                </a>
                <br/>
                <a class="a3">
                    <label class="label2">
                        重量:
                    </label>
                    500g
                </a>
                <br/>
                <a class="a4">
                    <label class="label2">
                        备注:
                    </label>
                    <%
                        if(drug.getRemark()==null){
                    %>
                            暂无
                    <%
                        }else {
                    %>
                    <label style="color: red">${detail_drug.remark}</label>
                    <%
                        }
                    %>
                </a>
                <br/>
                <a class="a4">
                    <label class="label2">
                        购买数量:
                    </label>
                    <button id = "button1" style="width: 25px;height: 20px">—</button><input type="number" id = "input1" value="1" style="width: 50px;height: 20px"/><button id = "button2" style="width: 25px;height: 20px">+</button>
                </a><br/>
                <a class="a4">
                        <form action="shopInsert" method="post">
                                <label class="label2">
                                加入购物车:
                                </label>
                                <input type="text" name="username" value="${username}" hidden />
                                <input type="text" name = "piece" hidden id = "input2" value="1"/>
                                <input name="drugName" value="${detail_drug.m_name}" type="text" hidden />
                                <input name="drugPrice" value="${detail_drug.price}" type="text" hidden />
                                <button type="submit" style="width: 60px;height: 30px;border-radius: 10px">加入</button>
                        </form>
                </a>
                <script>
                    var input = $('#input1');
                    var input1 = $('#input2');
                    var button1 = $('#button1');
                    var button2 = $('#button2');
                    button1.click(function () {
                        if(input.val()<=0){
                            input.val(1);
                            alert("购买数量要大于0");
                        }else{
                            var a = input.val();
                            input.val(a-1);
                            input1.val(a-1);
                        }
                    });
                    button2.click(function () {
                        if(input.val()<=0){
                            input.val(1);
                            alert("购买数量要大于0");
                        }else{
                            var a = input.val();
                            input.val(parseInt(a)+1);
                            input1.val(parseInt(a)+1);
                        }
                    })
                </script>
            </div>
        </div>

        <div>
            <div style="margin-top: 320px;margin-left: 30px">
                <b style="font-size: 20px">1、功效与作用:</b>
                <a style="font-size: 21px">${detail_drug.effect}</a>
            </div>
            <div style="margin-left: 30px;margin-top: 10px">
                <b style="font-size: 20px">2、使用禁忌:</b>
                <a style="font-size: 21px">${detail_drug.taboo}</a>
            </div>
            <div style="margin-left: 30px;margin-top: 10px">
                <b style="font-size: 20px">3、如何使用:</b>
                <a style="font-size: 21px">
                    <%
                        if(drug.getM_use().contains("⑧")){
                            if(drug.getM_use().contains("①")){
                                String[] a = drug.getM_use().split("①");
                                if(a[1].contains("②")){
                                    String[] a1 = a[1].split("②");
                                    session.setAttribute("a1",a1[0]);
                                    if(a[1].contains("③")){
                                        String[] a2 = a1[1].split("③");
                                        session.setAttribute("a2",a2[0]);
                                        if(a[1].contains("④")){
                                            String[] a3 = a2[1].split("④");
                                            session.setAttribute("a3",a3[0]);
                                            if(a[1].contains("⑤")){
                                                String[] a4 = a3[1].split("⑤");
                                                session.setAttribute("a4",a4[0]);
                                                if(a[1].contains("⑥")){
                                                    String[] a5 = a4[1].split("⑥");
                                                    session.setAttribute("a5",a5[0]);
                                                    if(a[1].contains("⑦")){
                                                        String[] a6 = a5[1].split("⑦");
                                                        session.setAttribute("a6",a6[0]);
                                                        if(a[1].contains("⑧")){
                                                            String[] a7 = a6[1].split("⑧");
                                                            session.setAttribute("a7",a7[0]);
                                                            session.setAttribute("a8",a7[1]);
                    %>
                    <br/>
                    <a class="a_detail">(1)、${a1}</a><br/>
                    <a class="a_detail">(2)、${a2}</a><br/>
                    <a class="a_detail">(3)、${a3}</a><br/>
                    <a class="a_detail">(4)、${a4}</a><br/>
                    <a class="a_detail">(5)、${a5}</a><br/>
                    <a class="a_detail">(6)、${a6}</a><br/>
                    <a class="a_detail">(7)、${a7}</a><br/>
                    <a class="a_detail">(8)、${a8}</a><br/>
                    <%
                          } } } } } } } }
                        }else if(drug.getM_use().contains("⑦")) {
                            if (drug.getM_use().contains("①")) {
                                String[] a = drug.getM_use().split("①");
                                if (a[1].contains("②")) {
                                    String[] a1 = a[1].split("②");
                                    request.setAttribute("a1", a1[0]);
                                    if (a[1].contains("③")) {
                                        String[] a2 = a1[1].split("③");
                                        request.setAttribute("a2", a2[0]);
                                        if (a[1].contains("④")) {
                                            String[] a3 = a2[1].split("④");
                                            request.setAttribute("a3", a3[0]);
                                            if (a[1].contains("⑤")) {
                                                String[] a4 = a3[1].split("⑤");
                                                request.setAttribute("a4", a4[0]);
                                                if (a[1].contains("⑥")) {
                                                    String[] a5 = a4[1].split("⑥");
                                                    request.setAttribute("a5", a5[0]);
                                                    if (a[1].contains("⑦")) {
                                                        String[] a6 = a5[1].split("⑦");
                                                        request.setAttribute("a6", a6[0]);
                                                        request.setAttribute("a7", a6[1]);
                     %>
                    <br/>
                    <a class="a_detail">(1)、${a1}</a><br/>
                    <a class="a_detail">(2)、${a2}</a><br/>
                    <a class="a_detail">(3)、${a3}</a><br/>
                    <a class="a_detail">(4)、${a4}</a><br/>
                    <a class="a_detail">(5)、${a5}</a><br/>
                    <a class="a_detail">(6)、${a6}</a><br/>
                    <a class="a_detail">(7)、${a7}</a><br/>
                    <%
                          } } } } } } }
                        }else if(drug.getM_use().contains("⑥")){
                            if (drug.getM_use().contains("①")) {
                                String[] a = drug.getM_use().split("①");
                                if (a[1].contains("②")) {
                                    String[] a1 = a[1].split("②");
                                    request.setAttribute("a1", a1[0]);
                                    if (a[1].contains("③")) {
                                        String[] a2 = a1[1].split("③");
                                        request.setAttribute("a2", a2[0]);
                                        if (a[1].contains("④")) {
                                            String[] a3 = a2[1].split("④");
                                            request.setAttribute("a3", a3[0]);
                                            if (a[1].contains("⑤")) {
                                                String[] a4 = a3[1].split("⑤");
                                                request.setAttribute("a4", a4[0]);
                                                if (a[1].contains("⑥")) {
                                                    String[] a5 = a4[1].split("⑥");
                                                    request.setAttribute("a5", a5[0]);
                                                    request.setAttribute("a6",a5[1]);
                    %>
                    <br/>
                    <a class="a_detail">(1)、${a1}</a><br/>
                    <a class="a_detail">(2)、${a2}</a><br/>
                    <a class="a_detail">(3)、${a3}</a><br/>
                    <a class="a_detail">(4)、${a4}</a><br/>
                    <a class="a_detail">(5)、${a5}</a><br/>
                    <a class="a_detail">(6)、${a6}</a><br/>
                    <%
                          } } } } } }
                        }else if(drug.getM_use().contains("⑤")){
                            if (drug.getM_use().contains("①")) {
                                String[] a = drug.getM_use().split("①");
                                if (a[1].contains("②")) {
                                    String[] a1 = a[1].split("②");
                                    request.setAttribute("a1", a1[0]);
                                    if (a[1].contains("③")) {
                                        String[] a2 = a1[1].split("③");
                                        request.setAttribute("a2", a2[0]);
                                        if (a[1].contains("④")) {
                                            String[] a3 = a2[1].split("④");
                                            request.setAttribute("a3", a3[0]);
                                            if (a[1].contains("⑤")) {
                                                String[] a4 = a3[1].split("⑤");
                                                request.setAttribute("a4", a4[0]);
                                                request.setAttribute("a5", a4[1]);
                    %>
                    <br/>
                    <a class="a_detail">(1)、${a1}</a><br/>
                    <a class="a_detail">(2)、${a2}</a><br/>
                    <a class="a_detail">(3)、${a3}</a><br/>
                    <a class="a_detail">(4)、${a4}</a><br/>
                    <a class="a_detail">(5)、${a5}</a><br/>
                    <%
                          } } } } }
                        }else if(drug.getM_use().contains("④")){
                            if (drug.getM_use().contains("①")) {
                                String[] a = drug.getM_use().split("①");
                                if (a[1].contains("②")) {
                                    String[] a1 = a[1].split("②");
                                    request.setAttribute("a1", a1[0]);
                                    if (a[1].contains("③")) {
                                        String[] a2 = a1[1].split("③");
                                        request.setAttribute("a2", a2[0]);
                                        if (a[1].contains("④")) {
                                            String[] a3 = a2[1].split("④");
                                            request.setAttribute("a3", a3[0]);
                                            request.setAttribute("a4",a3[1]);
                    %>
                    <br/>
                    <a class="a_detail">(1)、${a1}</a><br/>
                    <a class="a_detail">(2)、${a2}</a><br/>
                    <a class="a_detail">(3)、${a3}</a><br/>
                    <a class="a_detail">(4)、${a4}</a><br/>
                    <%
                                        } } } }
                        }else if(drug.getM_use().contains("③")){
                            if (drug.getM_use().contains("①")) {
                                String[] a = drug.getM_use().split("①");
                                if (a[1].contains("②")) {
                                    String[] a1 = a[1].split("②");
                                    request.setAttribute("a1", a1[0]);
                                    if (a[1].contains("③")) {
                                        String[] a2 = a1[1].split("③");
                                        request.setAttribute("a2", a2[0]);
                                        request.setAttribute("a3",a2[1]);
                    %>
                    <br/>
                    <a class="a_detail">(1)、${a1}</a><br/>
                    <a class="a_detail">(2)、${a2}</a><br/>
                    <a class="a_detail">(3)、${a3}</a><br/>
                    <%
                                    } } }
                        }else if(drug.getM_use().contains("②")){
                            if (drug.getM_use().contains("①")) {
                                String[] a = drug.getM_use().split("①");
                                if (a[1].contains("②")) {
                                    String[] a1 = a[1].split("②");
                                    request.setAttribute("a1", a1[0]);
                                    request.setAttribute("a2",a1[1]);
                    %>
                    <br/>
                    <a class="a_detail">(1)、${a1}</a><br/>
                    <a class="a_detail">(2)、${a2}</a><br/>
                    <%
                                } }
                        }else if(drug.getM_use().contains("①")){
                            String[] a = drug.getM_use().split("①");
                            request.setAttribute("a1",a[1]);
                    %>
                    <br/>
                    <a class="a_detail">(1)、${a1}</a><br/>
                    <%
                        }else{
                            request.setAttribute("m_use",drug.getM_use());
                    %>
                          <a>${m_use}</a>
                    <%
                        }
                    %>
                </a>
            </div>
        </div>
<%
    }
%>

<div style="margin-top: 10px;margin-left: 30px">
    <b style="font-size: 20px">
        4、用户评价:
    </b>
    <%
        List<Comment> list = (List<Comment>) session.getAttribute("list_comm");
        Comment comment;
        if(list!=null&&list.size()>0){
            for(int i = 0;i<list.size();i++){
                comment = list.get(i);
                request.setAttribute("comment",comment);
                request.setAttribute("i",i+1);
    %>
    <div style="margin-left: 120px;margin-top: 10px">
        <a style="font-size: 20px">${i}、用户${comment.username}:</a>
        <a style="font-size: 20px">${comment.comment}</a>
        <a style="display:inline-block;margin-left:50px;font-size: 20px">——${comment.time}</a>
    </div>
    <hr/>
    <%
        } }else{
    %>
                <a style="display: inline-block;margin-top: 10px;font-size: 20px">暂无评价</a>
    <%
        }
    %>
</div>

    <%
        String result = (String) request.getAttribute("result");
        if(result!=null){
    %>
           <script>
               alert("加入购物车成功");
           </script>
    <%
        }
    %>
<div style="margin-left: 30px;margin-top: 15px">
       <form action="returnStart" method="post">
           <b style="font-size: 20px">5、返回商品页面:</b>
           <button type="submit" style="width: 60px;height: 32px;border-radius: 10px">返回</button>
       </form>
</div>
<div style="height: 100px">

</div>
<%
    }
%>
</body>
</html>
