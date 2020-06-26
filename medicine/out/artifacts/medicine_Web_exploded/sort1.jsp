<%@ page import="java.util.List" %>
<%@ page import="bean.Drug" %><%--
  Created by IntelliJ IDEA.
  User: ASUS-PC
  Date: 2020/2/10
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="jqu/jquery.min.js"></script>
    <style>
        body{
            background-color: #eee7d5;
        }
        img{
            width: 220px;
            height: 180px;
            margin-left: 60px;
        }
        table{
            margin-left: 100px;
        }
        td{
            width: 370px;
            height: 370px;
        }
        #a1{
            display: inline-block;
            margin-top: 10px;
            margin-left: 900px;
        }
        .a3{
            font-size: 20px;
        }
        .label2{
            font-size: 18px;
            color: red;
        }
        .div1{
            margin-left: 60px;
            margin-top: 10px;
        }
        .label1{
            display: inline-block;
            width: 80px;
            font-size: 20px;
        }
        .button1{
            display: inline-block;
            margin-top: 10px;
            width: 210px;
            height: 30px;
            font-size: 18px;
        }
    </style>
</head>
<body>
<div class="div1">
    <%
        String username = (String) session.getAttribute("username");
        if(username==null){
    %>
    <a class="a1" href="${pageContext.request.contextPath}/login.jsp" style="font-size: 20px">登录</a>
    <a class="a2" href="${pageContext.request.contextPath}/register.jsp" style="font-size: 20px">注册</a>
    <%
        }
    %>
    <%
        if(username!=null){
            session.setAttribute("username",username);
    %>
    <form action="returnStart" method="post">
        <a id="a1" style="font-size: 20px">${username}，欢迎！！！</a>
        <button type="submit" style="width: 60px;height: 32px;border-radius: 10px">返回</button>
    </form>
    <%
        }
    %>
</div>
    <hr/>

<table border="1">
    <%
        List<Drug> list = (List<Drug>) session.getAttribute("list1");
        Drug drug;
        if(list!=null){
            for (int i = 0;i<list.size();i=i+3){
    %>
            <tr>
                <td>
                    <%
                        drug = list.get(i);
                        session.setAttribute("drug",drug);
                    %>
                    <img src="${drug.picture}">
                    <div class="div1">
                            <label class="label1">
                                ${drug.m_name}
                            </label>
                        <a class="a3">
                            价格:
                            <label class="label2">
                                ￥${drug.price}
                            </label>
                        </a>
                        <form action="detail" method="post">
                            <input type="text" value="${drug.m_name}" name="drugName" hidden />
                            <button class="button1">查看详情</button>
                        </form>
                    </div>
                </td>
                <td>
                    <%
                        if(i+1<list.size()){
                            drug = list.get(i+1);
                            session.setAttribute("drug",drug);
                    %>
                    <img src="${drug.picture}">
                    <div class="div1">
                            <label class="label1">
                               ${drug.m_name}
                            </label>
                        <a class="a3">
                            价格:
                            <label class="label2">
                                ￥${drug.price}
                            </label>
                        </a>
                        <form action="detail" method="post">
                            <input type="text" value="${drug.m_name}" name="drugName" hidden />
                            <button class="button1">查看详情</button>
                        </form>
                    </div>
                    <%
                        }
                    %>
                </td>
                <td>
                    <%
                        if(i+2<list.size()){
                            drug = list.get(i+2);
                            session.setAttribute("drug",drug);
                    %>
                    <img src="${drug.picture}">
                    <div class="div1">
                            <label class="label1">
                                ${drug.m_name}
                            </label>
                        <a class="a3">
                            价格:
                            <label class="label2">
                                ￥${drug.price}
                            </label></a>
                        <form action="detail" method="post">
                            <input type="text" value="${drug.m_name}" name="drugName" hidden />
                            <button class="button1">查看详情</button>
                        </form>
                    </div>
                    <%
                        }
                    %>
                </td>
            </tr>
<%
        }
    }
%>
</table>
</body>
</html>
