package servlet;

import bean.Chat;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetChatServlet",value = "/getChat")
public class GetChatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        Way way = new Way();
        int id = way.chat_id();
        int id_max = way.charId_max();
        List<Chat> list = new ArrayList<Chat>();
        if(id_max>=10){
            for (int i = id;i<id+10;i++){
                Chat chat = way.chat(i);
                list.add(chat);
            }
            request.setAttribute("list",list);
            request.getRequestDispatcher(request.getContextPath()+"/manChat.jsp").forward(request,response);
        }else {
            for (int i = id;i<id+id_max;i++){
                Chat chat = way.chat(i);
                list.add(chat);
            }
            request.setAttribute("list",list);
            request.getRequestDispatcher(request.getContextPath()+"/manChat.jsp").forward(request,response);
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
