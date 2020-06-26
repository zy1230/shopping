package servlet;

import bean.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ManSeeOrderServlet",value = "/manSeeOrder")
public class ManSeeOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        Way way = new Way();
        List<Integer> list = way.man_orderId(0);
        if(list.size()>0){
            int id;
            List<Order> list1 = new ArrayList<Order>();
            Order order;
            for(int i = 0;i<list.size();i++){
                id = list.get(i);
                order = way.man_orderAll(id);
                list1.add(order);
            }
            request.setAttribute("list_order",list1);
            request.getRequestDispatcher(request.getContextPath()+"/manOrder.jsp").forward(request,response);
        }else{
            request.setAttribute("no","no");
            request.getRequestDispatcher(request.getContextPath()+"/manOrder.jsp").forward(request,response);
        }
    }
}
