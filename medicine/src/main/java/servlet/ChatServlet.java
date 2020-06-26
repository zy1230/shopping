package servlet;

import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChatServlet",value = "/chat")
public class ChatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html;charset=utf-8");
         request.setCharacterEncoding("UTF-8");
         Way way = new Way();
         String name = request.getParameter("name");
         String user = way.select_user(name);
         if(user!=null){
             request.setAttribute("one","ok");
             request.getRequestDispatcher(request.getContextPath()+"/start.jsp").forward(request,response);
         }else {
             String chat = request.getParameter("chat");
             way.insert_chat(chat,name);
             request.setAttribute("chat1",chat);
             request.getRequestDispatcher(request.getContextPath()+"/start.jsp").forward(request,response);
         }
    }
}
