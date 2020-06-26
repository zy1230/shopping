package servlet;

import bean.User;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminsterServlet",value = "/adminster")
public class AdminsterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setUpass(password);
        Way way = new Way();
        int result = way.insert_manager(user);
        if(result>0){
            request.setAttribute("result","ok");
            request.getRequestDispatcher(request.getContextPath()+"/manAdmin.jsp").forward(request,response);
        }else {
            response.getWriter().write("出了点问题，请稍后添加");
        }

    }
}
