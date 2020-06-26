package servlet;

import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateGoodTwoServlet",value = "/updateGoodTwo")
public class UpdateGoodTwoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.setContentType("text/html;charset=utf-8");
       request.setCharacterEncoding("UTF-8");
       String drugName = request.getParameter("drugName");
       Way way = new Way();
       int result = way.updateGood2(drugName);
       if(result>0){
           request.setAttribute("updateGoodTwo","推荐成功");
           request.getRequestDispatcher(request.getContextPath()+"/manDrug.jsp").forward(request,response);
       }else {
           request.setAttribute("noGoodTwo","请检查是否有此药品");
           request.getRequestDispatcher(request.getContextPath()+"/manDrug.jsp").forward(request,response);
       }

    }
}
