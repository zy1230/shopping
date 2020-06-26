package servlet;

import bean.Health;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateShowServlet",value = "/updateShow")
public class UpdateShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        int heal_id = Integer.parseInt(request.getParameter("id"));
        Way way = new Way();
        int result = way.update_heal(heal_id);
        if(result>0){
            request.setAttribute("updateShow","ok");
            request.getRequestDispatcher(request.getContextPath()+"/manHealth.jsp").forward(request,response);
        }else{
            request.setAttribute("noUpdate","no");
            request.getRequestDispatcher(request.getContextPath()+"/manHealth.jsp").forward(request,response);
        }
    }
}
