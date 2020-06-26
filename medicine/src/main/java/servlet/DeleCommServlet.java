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

@WebServlet(name = "DeleCommServlet",value = "/deleComm")
public class DeleCommServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("comm_id"));
        Way way = new Way();
        int result = way.dele_comm(id);
        if(result>0){
            List<Integer> list = way.man_commId();
            if(list.size()>0){
                Comment comment;
                List<Comment> list1 = new ArrayList<Comment>();
                for(int i = 0;i<list.size();i++){
                    comment = way.man_CommAll(list.get(i));
                    list1.add(comment);
                }
                request.setAttribute("list_comm",list1);
                request.setAttribute("delete","ok");
                request.getRequestDispatcher(request.getContextPath()+"/manComment.jsp").forward(request,response);
            }else {
                request.setAttribute("delete","ok");
                request.setAttribute("no","ok");
                request.getRequestDispatcher(request.getContextPath()+"/manComment.jsp").forward(request,response);
            }
        }
    }
}
