package servlet;

import bean.Order;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ConfirmServlet",value = "/confirm")
public class ConfirmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.setContentType("text/html;charset=utf-8");
       request.setCharacterEncoding("UTF-8");
       String username = request.getParameter("username");
       String date = request.getParameter("time");
       Way way = new Way();
       int result = way.update_receive(username,date);
       if(result>0){
           List<Integer> list = way.select_orderId(username);
           Order order;
           List<Order> list1 = new ArrayList<Order>();
           for (int i = 0;i<list.size();i++){
               order = way.select_orderAll(list.get(i));
               list1.add(order);
           }
           request.setAttribute("list_order",list1);
           request.setAttribute("res","ok");
           request.getRequestDispatcher(request.getContextPath()+"/WEB-INF/views/order.jsp").forward(request,response);
       }else {
           response.getWriter().write("出了点问题，请联系管理员");
       }
    }
}
