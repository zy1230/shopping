<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %><%--
  Created by IntelliJ IDEA.
  User: ASUS-PC
  Date: 2020/1/30
  Time: 13:30
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
        .div2{
            margin-top: 20px;
            width: 200px;
            font-size: 25px;
            margin-bottom: 15px;
            margin-left: 50%;
        }
        .div3{
            margin:100px auto;
            width: 450px;
        }
        p{
            font-size: 40px;
        }
        a{
           font-size: 18px;
        }
        .label1{
            display: inline-block;
            width: 130px;
            height: 30px;
        }
        .input1{
            width: 200px;
            height: 30px;
        }
        .label2{
            display: inline-block;
            width: 155px;
            height: 30px;
        }
        table{
            margin-left: 120px;
        }
        td{
            width: 300px;
        }
        select{
            width: 100px;
            height: 30px;
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
<div class="div2">药品管理</div>

<div class="div4">
    <a>1、药品添加：</a>
    <div style="margin-left: 100px;margin-top: 10px">
        <form action="manAddDrug" method="post">
            <div>
                <label class="label1">1、药品名称：</label><input type="text" name = "drugName" class="input1" required = "required" placeholder="不能为空"/>
            </div>
            <div>
                <label class="label1">2、药品价格：</label><input type="text" name = "drugPrice" class="input1" required = "required" placeholder="不能为空"/>
            </div>
            <div>
                <label class="label1">3、功效作用：</label><input type="text" name="drugEffect" class="input1" required = "required" placeholder="不能为空"/>
            </div>
            <div>
                <label class="label1">4、药品用法：</label><input type="text" name="drugUse" class="input1" required = "required" placeholder="不能为空"/>
            </div>
            <div>
                <label class="label1">5、使用禁忌：</label><input type="text" name="drugTaboo" class="input1" required = "required" placeholder="不能为空"/>
            </div>
            <div>
                <label class="label1">6、药品备注：</label><input type="text" name="drugRemarks" class="input1"/>
            </div>
            <div>
                <label class="label1">7、药品分类：</label>
                <!--<input type="text" name="drugClass" class="input1" required = "required" placeholder="不能为空"/>-->
                <select name="drugClass">
                    <option value="花草茶">花草茶</option>
                    <option value="干果干货">干果干货</option>
                    <option value="滋补强身">滋补强身</option>
                    <option value="补气活血">补气活血</option>
                    <option value="健胃消食">健胃消食</option>
                    <option value="化瘀止痰">化瘀止痰</option>
                </select>
            </div>
            <div style="margin-left: 100px;margin-top: 10px">
                <button type="submit" style="width: 60px;height: 30px;border-radius: 10px">添加</button>
                <button type="reset" style="width: 60px;height: 30px;margin-left: 30px;border-radius: 10px">清空</button>
            </div>
        </form>
        <%
            String success = (String) request.getAttribute("insertDrug");
            if(success!=null){
        %>
             <script>
                 alert("请上传该药品的照片");
             </script>
        <%
            }
        %>
        <%
            String fail = (String) request.getAttribute("insertNo");
            if(fail!=null){
        %>
               <div style="margin-left: 100px">
                   <a style="color: red;font-size: 18px">添加失败，请稍后添加</a>
               </div>
        <%
            }
        %>
    </div>
    <div>
        <%
            String drugName2 = (String) request.getAttribute("drugName");
            String drugId2 = (String) request.getAttribute("drugId");
            if(drugName2!=null&&drugId2!=null){
                request.setAttribute("drugName",drugName2);
                request.setAttribute("drugId",drugId2);
        %>
        <div style="margin-bottom: 10px">
            <a style="display: inline-block;color: red;margin-bottom: 10px">请上传该药品的一张图片</a><br/>
            <label style="display:inline-block;font-size: 18px">药品名称：</label><input type="text" value="${drugName}" /><br/>
            <label style="display:inline-block;margin-top:10px;font-size: 18px">药品图片名称（<span style="color: red">请以该名称命名药品图片</span>）:</label><b>0${drugId}</b>
        </div>
        <%
            }
        %>
        <form action="drugPicture" method="post" enctype="multipart/form-data">
            <div>
                <label class="label1" style="margin-left: 7%">药品的图片：</label><input type="file" name="drugPicture" class="input1"/>
                <button type="submit" style="width: 100px;height: 30px;border-radius: 10px">上传图片</button>
            </div>
        </form>
        <%
            String upPicture = (String) request.getAttribute("upPicture");
            if(upPicture!=null){
        %>
           <div style="margin-left: 7%;margin-bottom: 10px">
               <a style="color: red;font-size: 18px">上传成功</a>
           </div>
        <%
            }
        %>
        <%
            String upNo = (String) request.getAttribute("upNo");
            if(upNo!=null){
        %>
                <script>
                    alert("稍后再试");
                </script>
        <%
            }
        %>
        <%
            String idNo = (String) request.getAttribute("idNo");
            if(idNo!=null){
        %>
        <script>
            alert("稍后再试");
        </script>
        <%
            }
        %>
    </div>
</div>

<div class="div4">
    <a>2、药品价格修改：</a>
    <div style="margin-left: 100px;margin-top: 10px">
        <form action="manUpPrice" method="post">
            <div>
                <label class="label2">1、输入商品名称：</label>
                <input type="text" name = "drugName" class="input1" required="required" placeholder="不能为空"/>
            </div>
            <div>
                <label class="label2">2、输入价格：</label>
                <input type="text" name = "drugPrice" class="input1" required pattern="*[0-9]" placeholder="请输入纯数字"/>
            </div>
            <button type="submit" style="width: 60px;height: 30px;border-radius: 10px;margin-left: 100px;margin-top: 10px">修改</button>
            <button type="submit" style="width: 60px;height: 30px;border-radius: 10px;margin-left:30px;margin-top: 10px">清空</button>
        </form>
</div>
    <%
        String update = (String) request.getAttribute("result_price");
        if(update!=null){
    %>
              <div style="margin-left: 100px">
                  <a style="font-size: 18px;color: red;">修改成功</a>
              </div>
    <%
        }
    %>
    <%
        String update_no = (String) request.getAttribute("res_price");
        if(update_no!=null){
    %>
            <script>
                alert("更新失败，请确认是否有该商品");
            </script>
    <%
        }
    %>
</div>

<div>
    <a>3、添加药品备注：（一般用于备注存货量）</a>
    <div style="margin-left: 100px;margin-top: 10px;">
        <form action="addRemarks" method="post">
            <div style="width: 500px;">
                <label>1、输入药品名称：</label>
                <input style="width: 300px;height: 30px" type="text" required="required" placeholder="不能为空，请输入正确的药品名称" name="drugName">
            </div>
            <div style="margin-top: 5px;width: 500px;">
                <label>2、输入备注内容：</label>
                <input style="width: 300px;height: 30px" type="text" required = "required" placeholder="不能为空" name="remarks">
            </div>
            <button type="submit" style="width: 60px;height: 30px;border-radius: 10px;margin-left: 100px;margin-top: 10px">更新</button>
            <button type="submit" style="width: 60px;height: 30px;border-radius: 10px;margin-left:30px;margin-top: 10px">清空</button>
        </form>
    </div>
    <%
        String resRemarks = (String) request.getAttribute("resRemarks");
        if(resRemarks!=null){
    %>
         <div style="margin-left: 100px">
             <a style="color: red">
                 更新成功
             </a>
         </div>
    <%
        }
    %>
    <%
        String noRemarks = (String) request.getAttribute("noRemarks");
        if(noRemarks!=null){
    %>
           <script>
               alert("请查看商品名称是否正确")
           </script>
    <%
        }
    %>
</div>

<div>
    <a>4、药品精品推荐管理</a>
    <div style="margin-left: 100px;margin-top: 10px">
        <form action="allGoodDrug" method="post">
            <label>1）、点击查看所有精品药品：</label>
            <button type ="submit" style="width: 60px;height: 30px;border-radius: 10px">查看</button>
        </form>
    </div>
    <%
        List<String> list = (List<String>) request.getAttribute("listGood");
        String drugName;
        if (list!=null&&list.size()>0){
            request.setAttribute("size",list.size());
    %>
        <div style="margin-left: 135px;margin-bottom: 10px">
            <label style="color: red">
                精品推荐共${size}种，少于6种则选择花草茶种类的药品进行推荐
            </label>
        </div>
             <table border="1">
                 <tr>
                     <td>
                         <label style="display: inline-block;margin-left: 60px">精品推荐的药品名称</label>
                     </td>
                     <td>
                         <label style="display: inline-block;margin-left: 130px">操作</label>
                     </td>
                 </tr>
     <%
         for (int i = 0;i<list.size();i++){
             drugName = list.get(i);
             request.setAttribute("name",drugName);
     %>
                 <tr>
                     <td>
                         <label style="display: inline-block;margin-left: 100px">${name}</label>
                     </td>
                     <td>
                         <div style="margin-left: 120px;margin-top: 10px">
                         <form action="updateGood" method="post">
                             <input type="text" name="drugName" hidden value="${name}" />
                             <button type="submit" style="width: 60px;height: 30px;border-radius: 10px">移除</button>
                         </form>
                         </div>
                     </td>
                 </tr>
                 <%
                     }
                 %>
             </table>
    <%
        }
    %>
    <%
        String noGood = (String) request.getAttribute("noGood");
        if(noGood!=null){
     %>
          <div style="margin-left: 100px">
              <label  style="color: red">
                  暂无精品推荐
              </label>
          </div>
    <%
        }
    %>
    <%
        String upGoodRes = (String) request.getAttribute("upGoodRes");
        if(upGoodRes!=null){
    %>
        <div style="margin-left: 120px;margin-top: 10px">
            <label style="color: red">移除成功</label>
        </div>
    <%
        }
    %>
    <%
        String noUpGood = (String) request.getAttribute("noUpGood");
        if(noUpGood!=null){
    %>
        <div style="margin-left: 100px">
            <label style="color: red;font-size: 20px">请稍后移除</label>
        </div>
    <%
        }
    %>
    <div>
        <div style="margin-left: 100px;margin-top: 10px">
            <label>
               2）、挑选推荐药品：（页面展示6个精选药品，超过6个，择选前6个）
           </label>
            <div style="margin-top: 10px;margin-left: 35px">
                <form action="updateGoodTwo" method="post">
                    <label>请输入要推荐的药品</label>
                    <input style="width: 300px;height: 30px" type="text" name="drugName" placeholder="请输入正确的药品名称" required="required" /><button type="submit" style="width: 60px;height: 30px">推荐</button>
                </form>
            </div>
        </div>
        <%
            String updateOk = (String) request.getAttribute("updateGoodTwo");
            if(updateOk!=null){
                request.setAttribute("updateOk",updateOk);
        %>
             <div style="margin-left: 100px">
                 <label style="color: red">${updateOk}</label>
             </div>
        <%
            }
        %>
        <%
            String noGoodTwo = (String) request.getAttribute("noGoodTwo");
            if(noGoodTwo!=null){
                request.setAttribute("noGoodTwo",noGoodTwo);
        %>
             <div style="margin-left: 100px">
                 <label style="color: red">${noGoodTwo}</label>
             </div>
        <%
            }
        %>

    </div>
</div>

<div style="margin-top: 10px">
    <a>5、药品删除：</a>
    <div style="margin-left: 100px;margin-top: 10px">
        <form action="manDeleteDrug" method="post">
            <div>
                 <label class="label2">1、输入商品名称：</label>
                 <input type="text" name = "drugName" required = "required" placeholder="不能为空" class="input1"/><button type="submit" style="width: 60px;height: 30px;">删除</button>
            </div>
        </form>
    </div>

    <%
        String delete = (String) request.getAttribute("result_delete");
        if(delete!=null){
    %>
             <div style="margin-left: 100px">
                 <a style="font-size: 18px;color: red">删除成功</a>
             </div>
    <%
        }
    %>
    <%
        String delete_no = (String) request.getAttribute("res_delete");
        if(delete_no!=null){
    %>
            <script>
                alert("删除失败，请输入正确的商品名称");
            </script>
    <%
        }
    %>
</div>
<div style="margin-top: 20px;">
    <a style="font-size: 20px">6、返回管理页面：</a>
    <div style="margin-left: 100px;margin-top: 10px">
        <form action="returnMan" method="post">
            <button type="submit" style="width: 60px;height: 30px;border-radius: 10px;">返回</button>
        </form>
    </div>
</div>
<div style="height: 100px"></div>

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
