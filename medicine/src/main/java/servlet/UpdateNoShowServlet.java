package servlet;

import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateNoShowServlet",value = "/updateNoShow")
public class UpdateNoShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        int id = Integer.valueOf(request.getParameter("id"));
        Way way = new Way();
        int result = way.update_noShow(id);
        if(result>0){
            request.setAttribute("updateNoShow","ok");
            request.getRequestDispatcher(request.getContextPath()+"/manHealth.jsp").forward(request,response);
        }else {
            request.setAttribute("upNoShow","no");
            request.getRequestDispatcher(request.getContextPath()+"/manHealth.jsp").forward(request,response);
        }
    }
}
