package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DrugServlet",value = "/drug")
public class DrugServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter("username");
            if(username!=null){
                request.getRequestDispatcher(request.getContextPath()+"/manDrug.jsp").forward(request,response);
            }else {
                response.getWriter().write("There is something wrong!!!");
            }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request,response);
    }
}
