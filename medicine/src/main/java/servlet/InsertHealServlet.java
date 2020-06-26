package servlet;

import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "InsertHealServlet",value = "/insert_health")
public class InsertHealServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           response.setContentType("text/html;charset=utf-8");
           request.setCharacterEncoding("UTF-8");
           String health = request.getParameter("health");
           String show = request.getParameter("show");
           Way way = new Way();
           if(show.equals("是")){
               int result = way.insert_heal(health,1);
               if(result>0){
                   request.setAttribute("insert_heal1","ok");
                   request.getRequestDispatcher(request.getContextPath()+"/manHealth.jsp").forward(request,response);
               }
           }else if(show.equals("否")){
               int result = way.insert_heal(health,0);
               if(result>0){
                   request.setAttribute("insert_heal1","ok");
                   request.getRequestDispatcher(request.getContextPath()+"/manHealth.jsp").forward(request,response);
               }
           }else {
               request.setAttribute("no_show","no");
               request.getRequestDispatcher(request.getContextPath()+"/manHealth.jsp").forward(request,response);
           }
    }
}