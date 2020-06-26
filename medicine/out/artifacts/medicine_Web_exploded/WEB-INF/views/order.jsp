<%@ page import="java.util.List" %>
<%@ page import="bean.Order" %><%--
  Created by IntelliJ IDEA.
  User: ASUS-PC
  Date: 2020/2/14
  Time: 11:21
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
            margin-left: 70%;
            font-size: 20px;
        }
        .a2{
            font-size: 20px;
        }
        table {
            margin-top: 10px;
            margin-left: 20px;
        }
        td{
            width: 400px;
        }
        .b3{
            font-size: 20px;
            margin-left: 100px;
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
    <div style="margin-top: 10px">
        <form action="returnStart" method="post">
            <a id="a1">${username}，欢迎！！！</a><button type="submit" style="width: 60px;height: 30px;border-radius: 10px">返回</button>
        </form>
    </div>
    <%
        }
    %>
</div>
<hr/>
<div>
    <a class="a2">待发货订单（或待评价)：</a>
</div>
<table border="1">
    <tr>
        <td>
            <b class="b3">商品名称</b>
        </td>
        <td>
            <b class="b3">是否发货</b>
        </td>
        <td>
            <b class="b3">确认收货</b>
        </td>
    </tr>
<%
    List<Order> list = (List<Order>) request.getAttribute("list_order");
    Order order;
%>
<%
    String res = (String) request.getAttribute("res");
    if(res!=null&&list!=null&&list.size()>0){
        for (int i = 0;i<list.size();i++){
            order = list.get(i);
            request.setAttribute("order",order);
%>
         <tr>
             <td>
                 <a style="margin-left: 120px;font-size: 20px">${order.drug}</a>
             </td>
             <td>
                 <%
                     if(order.getState()==0){
                 %>
                        <a style="margin-left: 100px;font-size: 20px">等待发货</a>
                 <%
                     }else{
                 %>
                        <a style="margin-left: 100px;font-size: 20px">已经发货</a>
                 <%
                     }
                 %>
             </td>
             <td>
                 <%
                     if(order.getReceive()==0){
                         request.setAttribute("date",order.getTime());
                 %>
                      <div style="margin-top: 10px;margin-left: 100px">
                          <form action="confirm" method="post">
                              <input type="text" name = "username" value="${username}" hidden />
                              <input type="text" name="time" value="${date}" hidden />
                              <button type="submit" style="width: 100px;height: 30px;border-radius: 10px">确认收货</button>
                          </form>
                      </div>

                 <%
                     }else if(order.getReceive()!=0&&order.getEvaluate()==0){
                         request.setAttribute("date",order.getTime());
                 %>
                         <div style="margin-top: 10px;margin-left: 20px">
                             <form action="evaluate" method="post">
                                 <input type="text" name = "username" value="${username}" hidden />
                                 <input type="text" name="time" value="${date}" hidden />
                                 <input type="text" name="drug" value="${order.drug}" hidden />
                                 <input type="text" name="date" hidden id = "input_date1" value="aa"/>
                                 <a style="font-size: 20px">您的评价：</a>
                                 <input type="text" name="comment" style="width: 200px;height: 30px" /><button type="submit" style="width: 60px;height: 30px">评价</button>
                             </form>
                         </div>
                 <script>
                         var input = document.getElementById("input_date1");
                         var date = new Date();
                         var year = date.getFullYear();
                         var month = date.getMonth()+1;
                         var day = date.getDate();
                         input.value = year+"年"+month+"月"+day+"日";
                 </script>
                 <%
                     }
                 %>
             </td>
         </tr>
<%
        }
    }else {
        String result = (String) request.getAttribute("result");
        if(result!=null&&list!=null&&list.size()>0){
            for (int i = 0;i<list.size();i++){
                order = list.get(i);
                request.setAttribute("order",order);
%>
    <tr>
        <td>
            <a>${order.drug}</a>
        </td>
        <td>
            <%
                if(order.getState()==0){
            %>
            <a style="font-size: 20px;margin-left: 100px">等待发货</a>
            <%
                 }else{
            %>
            <a style="font-size: 20px;margin-left: 100px">已经发货</a>
            <%
                }
            %>
        </td>
        <td>
            <%
                if(order.getReceive()==0){
                    request.setAttribute("date",order.getTime());
            %>
            <div style="margin-top: 10px;margin-left: 100px">
                <form action="confirm" method="post">
                    <input type="text" name = "username" value="${username}" hidden />
                    <input type="text" name="time" value="${date}" hidden />
                    <button type="submit" style="width: 100px;height: 30px;margin-left: 50px;margin-top: 10px;border-radius: 10px">确认收货</button>
                </form>
            </div>
            <%
                 }else if(order.getReceive()!=0&&order.getEvaluate()==0){
                       request.setAttribute("date",order.getTime());
            %>
            <div style="margin-left: 20px;margin-top: 10px">
                <form action="evaluate" method="post">
                <input type="text" name = "username" value="${username}" hidden />
                <input type="text" name="time" value="${date}" hidden />
                <input type="text" name="drug" value="${order.drug}" hidden />
                <input type="text" name="date" hidden id = "input_date2"/>
                <a style="font-size: 20px;">您的评价：</a>
                <input type="text" name="comment" style="width: 200px;height: 30px"/><button type="submit" style="width: 60px;height: 30px">评价</button>
            </form>
            </div>
            <script>
                    var input = document.getElementById("input_date2");
                    var date = new Date();
                    var year = date.getFullYear();
                    var month = date.getMonth()+1;
                    var day = date.getDate();
                    input.value = year+"年"+month+"月"+day+"日";
            </script>
            <%
                }
            %>
        </td>
    </tr>
<%
        }
      }
    }
%>
</table>

<%
    String result2 = (String) request.getAttribute("result2");
    if(result2!=null){
%>
       <div><a style="font-size: 20px;color:red;margin-top: 20px;margin-left: 20px">评价成功</a></div>
<%
    }
%>

</body>
</html>
