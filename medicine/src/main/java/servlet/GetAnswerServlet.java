package servlet;

import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetAnswerServlet",value = "/getAnswer")
public class GetAnswerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           response.setContentType("text/html;charset=utf-8");
           request.setCharacterEncoding("UTF-8");
           String user = request.getParameter("user");
           Way way = new Way();
           String answer = way.answer(user);
           request.getSession().setAttribute("answer",answer);
           request.setAttribute("two","ok");
           way.delete_answer(user);
           request.getRequestDispatcher(request.getContextPath()+"/start.jsp").forward(request,response);
    }
}
