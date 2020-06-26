<%@ page import="java.util.List" %>
<%@ page import="bean.Drug" %><%--
  Created by IntelliJ IDEA.
  User: ASUS-PC
  Date: 2019/11/24
  Time: 15:20
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
            margin-top: 20px;
            height: 30px;
        }
        .div2{
            height: 300px;
            margin-top: 5px;
        }
        hr{
            display: block;
            margin-top: 3px;
        }
        .div3 {
            margin-top: 10px;
            float: left;
            width: 20%;
            height: 600px;
        }
        .div4{
            float:left;
            width: 55%;
            height: 600px;
        }
        .div5{
            margin-top: 15px;
            float: left;
            width: 24%;
            height: 400px;
            background-image: url("/view/6.jpg");
        }
        #a1{
            display: inline-block;
            text-decoration: none;
            color: black;
        }
        .a2{
            display: inline-block;
            text-decoration: none;
            color: black;
        }
        .a3{
            color: black;
            text-decoration: none;
            font-size: 20px;
        }
        ul{
            border: 1px black solid;
            text-decoration: none;
            width: 86%;
        }
        .b1{
            font-size: 25px;

            color: black;
        }
        li{
            display: none;
        }
        ul:hover li{
            height: 30px;
            display: block;
        }
        input{
            width: 300px;
            height: 30px;
        }
        button{
            height: 30px;
        }
        .health4{
            font-size: 20px;
            display: inline-block;
            margin-left: 150px;
            width: 1000px;
        }
        .health1{
            width: 1300px;
            height: 240px;
            margin: 5px 15px;
            overflow: hidden;
        }
        .health2{
            width: 3900px;
            height: 240px;
            position: relative;
            animation: my 30s infinite alternate;
        }
        .health3{
            width: 1300px;
            height: 240px;
            float: left;
        }
        @keyframes my {
            0%{
                left:0;
            }
            50%{
                left:-1300px;
            }
            100%{
                left:-2600px;
            }
        }
        .jingDiv1{
            float: left;
            width: 30%;
            height: 180px;
            margin-top: 10px;
            margin-left: 20px;
        }
        .out{
            border:0;
            outline: none;
            background-color: transparent;
            font-size: 18px;
            color: cornflowerblue;
            display: inline;
            margin-left: 3%;
        }
        .out_div{
            margin-left: 75%;
        }
    </style>
    <script src="jqu/jquery.min.js"></script>
</head>
<body>
   <div class="div1">
       <%
           String username = (String) session.getAttribute("username");
           if(username==null){
       %>
       <a class="a2" href="${pageContext.request.contextPath}/login.jsp" style="font-size: 20px">登录</a>
       <a class="a2" href="${pageContext.request.contextPath}/register.jsp" style="font-size: 20px">注册</a>
       <a style="color: lightcoral;margin-left: 10px;font-size: 20px">请先登录，登录有惊喜</a>
       <%
           }
       %>
       <%
           if(username!=null){
               session.setAttribute("username",username);
       %>
       <div class="out_div">
           <form action="getOut" method="post">
               <a id="a1" style="font-size: 20px">${username}</a>，欢迎！！！
               <button type="submit" class="out">退出登录</button>
           </form>
       </div>
       <%
           }
       %>
       <hr>
   </div>
   <!--
       健康小知识展示
   -->
   <div class="div2">
       <div style="width: 200px;margin: 20px auto">
           <a style="font-size: 20px">
               健康小知识
           </a>
       </div>
       <div class="health1">
           <div class="health2">
               <%
                   List<String> list = (List<String>) session.getAttribute("heal_show");
                   if(list!=null){
                       for (int i = 0;i<list.size();i++){
                           session.setAttribute("healShow",list.get(i));
               %>
               <div class="health3" style = "background-image: url('/view/2.jpg');">
                   <a class="health4">
                       ${healShow}
                   </a>
               </div>
               <%
                       }
                   }
               %>
           </div>
       </div>
   </div>
   <!--
       商品分类
   -->
   <div class="div3">
           <ul><b class="b1" style="letter-spacing: 20px;">商品分类</b>
               <li style="margin-top: 5px">
                   <a class="a3">
                       <form action="sort1" method="post">
                           <input name="classification" value="花草茶" type="text" hidden />
                           <button type="submit" style="border: none;background-color: transparent;outline: none;font-size: 20px ">
                               花草茶
                           </button>
                       </form>
                   </a>
               </li>
               <li style="margin-top: 5px">
                   <a class="a3">
                       <form action="sort1" method="post">
                           <input name="classification" value="干果干货" type="text" hidden />
                           <button type="submit" style="border: none;background-color: transparent;outline: none;font-size: 20px ">
                               干果干货
                           </button>
                       </form>
                   </a>
               </li>
               <li style="margin-top: 5px">
                   <a class="a3">
                       <form action="sort1" method="post">
                           <input name="classification" value="滋补强身" type="text" hidden />
                           <button type="submit" style="border: none;background-color: transparent;outline: none;font-size: 20px ">
                               滋补强身
                           </button>
                       </form>
                   </a>
               </li>
               <li style="margin-top: 5px">
                   <a class="a3">
                       <form action="sort1" method="post">
                           <input name="classification" value="补气活血" type="text" hidden />
                           <button type="submit" style="border: none;background-color: transparent;outline: none;font-size: 20px ">
                               补气活血
                           </button>
                       </form>
                   </a>
               </li>
               <li style="margin-top: 5px">
                   <a class="a3">
                       <form action="sort1" method="post">
                           <input name="classification" value="健胃消食" type="text" hidden />
                           <button type="submit" style="border: none;background-color: transparent;outline: none;font-size: 20px ">
                               健胃消食
                           </button>
                       </form>
                   </a>
               </li>
               <li style="margin-top: 5px">
                   <a class="a3">
                       <form action="sort1" method="post">
                           <input name="classification" value="化瘀止痰" type="text" hidden />
                           <button type="submit" style="border: none;background-color: transparent;outline: none;font-size: 20px ">
                               化瘀止痰
                           </button>
                       </form>
                   </a>
               </li>
           </ul>
       <div title="点击上方商品分类">
           <div style="margin-top: 30px">
               <a style="font-size: 18px;">花草茶</a>
               <hr/>
           </div>
           <div style="margin-top: 15px">
               <a style="font-size: 18px;">干果干货</a>
               <hr/>
           </div>
           <div style="margin-top: 15px">
               <a style="font-size: 18px;">滋补强身</a>
               <hr/>
           </div>
           <div style="margin-top: 15px">
               <a style="font-size: 18px;">补气活血</a>
               <hr/>
           </div>
           <div style="margin-top: 15px">
               <a style="font-size: 18px;">健胃消食</a>
               <hr/>
           </div>
           <div style="margin-top: 15px">
               <a style="font-size: 18px;">化瘀止痰</a>
               <hr/>
           </div>
       </div>
   </div>
   <!--
       部分商品展示
   -->

   <div class="div4">
         <div style="margin-top: 20px;margin-left: 180px">
             <form action="selectUserDrug" method="post">
                 <input  name="drugName" type="text" placeholder="搜一搜" required = "required"/><button type="submit" style="width: 60px;height: 30px">查询</button>
             </form>
             <%
                 String find = (String) request.getAttribute("find_no");
                 if (find!=null) {
             %>
                    <script>
                        alert("可以试试搜索相关药品");
                    </script>
             <%
                 }
             %>
         </div>
         <div style="margin-top:10px;height: 400px;">
             <div style="margin-left: 30px"><b style="font-size: 16px">精品推荐</b>
             </div>
             <%
                 List<Drug> list1 = (List<Drug>) session.getAttribute("start_drug");
                 if(list1!=null&&list1.size()>0){
                     Drug drug;
                     for(int i = 0;i<list1.size();i++){
                        drug = list1.get(i);
                        session.setAttribute("sDrug",drug);
             %>
                     <div class="jingDiv1">
                         <div style="margin-left: 15px">
                             <form action="detail" method="post">
                                 <input type="text" name="drugName" value="${sDrug.m_name}" hidden />
                                 <img src="${sDrug.picture}" style="width: 180px;height: 150px" title="点击下方商品名称查看详情"><br/>
                                 <button type="submit" style="margin-left: 35px;margin-top: 2px;border: 0;background-color: #eee7d5;font-size: 18px" class="jButton">${sDrug.m_name}</button>
                             </form>

                         </div>

                     </div>
             <%
                     }
                 }
             %>
         </div>
   </div>

<!--
      会话模块
-->

   <div class="div5">
       <div style="margin-top: 12px;margin-left: 120px">
           <a style="margin: 20px auto;width: 60px;font-size: 23px">咨询室</a>
       </div>
       <div>
           <form action="getAnswer" method="post">
               <img src="view/timg.jpg" style="width: 30px;height: 30px;border-radius: 15px">
               <div style="display:inline-block;border: 1px black solid;width: 150px;border-radius: 5px">
                   <a id="a6">您好！</a>
               </div>
               <input type="text" value="${username}" name="user" hidden />
               <button type="submit" style="width: 80px;height: 30px;border-radius: 10px">查看回复</button>
           </form>
       </div>
     <%
        String answer = (String) session.getAttribute("answer");
        if(answer!=null){
            session.setAttribute("answer",answer);
     %>
       <div>
               <img src="view/timg.jpg" style="width: 30px;height: 30px;border-radius: 15px">
               <div style="display:inline-block;border: 1px black solid;width: 150px;border-radius: 5px">
                   <a>${answer}</a>
               </div>
       </div>
     <%
        }
     %>
       <%
           String two = (String) request.getAttribute("two");
           if (two!=null){
       %>
       <script>
           alert("耐心等待管理员回复")
       </script>
     <%
        }
     %>
<%
    String chat = (String) request.getAttribute("chat1");
    if(chat!=null){
        session.setAttribute("chat1",chat);
%>
       <div class="div7" style="float:right;width: 150px;margin-left: 230px;margin-top: 20px;border: 1px black solid">
           ${chat1}
               <!-- 用户发送的消息展示  -->
       </div>
<%
    }
%>
   <%
       if(username!=null){
   %>
   <div style="width: 300px;margin-top: 300px;margin-left: 10px">
       <form action="chat" method="post">
           <input type="text" value="${username}" name="name" hidden>
           <input id="input1" type="text" name="chat" style="width: 250px;height: 30px" placeholder="有没有想咨询的呢"><button type="submit">发送</button>
       </form>
   </div>
   </div>
   <%
       String one = (String) request.getAttribute("one");
       if(one!=null){
   %>
   <script>
       alert("请先等待管理员回复");
   </script>
   <%
       }
   %>
   <%
       }else{
   %>
   <script>
       $(document).ready(function () {
           var btn = $('button')[1];
           btn.onclick = function () {
                  alert("请先登录哦，亲")
           }
       })
   </script>
   <%
       }
   %>
</body>
</html>
