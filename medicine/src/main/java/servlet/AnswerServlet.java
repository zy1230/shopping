package servlet;

import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AnswerServlet",value = "/answer")
public class AnswerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String answer = request.getParameter("answer");
        String user = request.getParameter("user");
        Way way = new Way();
        way.insert_answer(answer,user);
        if(answer!=null){
            way.delete_chat(user);
            request.getRequestDispatcher(request.getContextPath()+"/manChat.jsp").forward(request,response);
        }
    }
}
