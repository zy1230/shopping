package servlet;

import bean.Comment;
import bean.Order;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "EvaluateServlet",value = "/evaluate")
public class EvaluateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String time = request.getParameter("time");
        String drug = request.getParameter("drug");
        String comment = request.getParameter("comment");
        String date = request.getParameter("date");
        Comment comment1 = new Comment(username,comment,date,drug);
        Way way = new Way();
        int result = way.update_evaluate(username,time);
        if(result>0){
            int result1 = way.insert_comm(comment1);
            if(result1>0){
                List<Order> list = new ArrayList<Order>();
                Order order;
                for(int i = 0;i<list.size();i++){
                    order = way.select_notEval(username);
                    list.add(order);
                }
                request.setAttribute("result2","ok");
                request.getRequestDispatcher(request.getContextPath()+"/WEB-INF/views/order.jsp").forward(request,response);
            }else {
                response.getWriter().write("评价出了点问题，稍后评价");
            }
        }else{
            response.getWriter().write("出了点问题");
        }
    }
}
