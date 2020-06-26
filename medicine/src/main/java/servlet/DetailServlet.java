package servlet;

import bean.Comment;
import bean.Drug;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DetailServlet",value = "/detail")
public class DetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String drugName = request.getParameter("drugName");
        Way way = new Way();
        Drug drug = way.select_detail(drugName);
        List<Integer> list = way.comment_drugID(drugName);
        List<Comment> list1 = new ArrayList<Comment>();
        int id;
        Comment comment;
        for(int i = 0;i<list.size();i++){
            id = list.get(i);
            comment = way.comment_drug(id);
            list1.add(comment);
        }
        request.getSession().setAttribute("list_comm",list1);
        request.getSession().setAttribute("detail",drug);
        request.getRequestDispatcher(request.getContextPath()+"/detail.jsp").forward(request,response);
    }
}
