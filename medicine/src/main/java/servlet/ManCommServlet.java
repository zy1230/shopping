package servlet;

import bean.Comment;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ManCommServlet",value = "/manComm")
public class ManCommServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        Way way = new Way();
        List<Integer> list = way.man_commId();
        if(list.size()>0){
            Comment comment;
            List<Comment> list1 = new ArrayList<Comment>();
            for(int i = 0;i<list.size();i++){
                comment = way.man_CommAll(list.get(i));
                list1.add(comment);
            }
            request.setAttribute("list_comm",list1);
            request.getRequestDispatcher(request.getContextPath()+"/manComment.jsp").forward(request,response);
        }else {
            request.setAttribute("no","ok");
            request.getRequestDispatcher(request.getContextPath()+"/manComment.jsp").forward(request,response);
        }
    }
}
