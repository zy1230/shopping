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

@WebServlet(name = "SeeOrderServlet",value = "/seeOrder")
public class SeeOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        Way way = new Way();
        List<Integer> list = way.select_orderId(username);
        Order order;
        List<Order> list1 = new ArrayList<Order>();
        for (int i = 0;i<list.size();i++){
            order = way.select_orderAll(list.get(i));
            if(order.getEvaluate()==0) {
                list1.add(order);
            }
        }
        request.setAttribute("list_order",list1);
        request.setAttribute("res","ok");
        request.getRequestDispatcher(request.getContextPath()+"/WEB-INF/views/order.jsp").forward(request,response);
    }
}
