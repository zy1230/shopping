package servlet;

import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteServlet",value = "/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        Way way = new Way();
        int result = way.delete(username);
        if(result>0){
            request.setAttribute("result2","ok");
            request.getRequestDispatcher(request.getContextPath()+"/manAdmin.jsp").forward(request,response);
        }else {
            response.getWriter().write("请稍后删除");
        }
    }
}
