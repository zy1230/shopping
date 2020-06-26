package servlet;

import bean.Order;
import bean.User;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderServlet",value = "/order")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String address = request.getParameter("orderAddress");
        int piece = Integer.parseInt(request.getParameter("orderPiece"));
        String username = request.getParameter("username");
        String drug = request.getParameter("orderDrug");
        String time = request.getParameter("time");
        Way way = new Way();
        User user = way.select_order(username);
        if(address.equals("")){
            int res = way.insert_orderOne(drug,piece,time,user);
            if(res>0){
                List<Integer> list = way.select_orderId(username);
                Order order;
                List<Order> list1 = new ArrayList<Order>();
                for (int i = 0;i<list.size();i++){
                    order = way.select_orderAll(list.get(i));
                    list1.add(order);
                }
                request.setAttribute("list_order",list1);
                request.setAttribute("res","ooo");
                request.getRequestDispatcher(request.getContextPath()+"/WEB-INF/views/order.jsp").forward(request,response);
            }else{
                response.getWriter().write("出了点问题，联系一下管理员");
            }

        }else {
            int result = way.insert_orderTwo(drug,piece,address,time,user);
            if(result>0){
                    List<Integer> list = way.select_orderId(username);
                    Order order;
                    List<Order> list1 = new ArrayList<Order>();
                    for (int i = 0;i<list.size();i++){
                        order = way.select_orderAll(list.get(i));
                        list1.add(order);
                    }
                    request.setAttribute("list_order",list1);
                    request.setAttribute("result","ok");
                    request.getRequestDispatcher(request.getContextPath()+"/WEB-INF/views/order.jsp").forward(request,response);
                }else{
                    response.getWriter().write("出了点问题，联系一下管理员");
                }
         }
    }
}
