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

@WebServlet(name = "ManUpStateServlet",value = "/manUpState")
public class ManUpStateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("UTF-8");
            int state = Integer.parseInt(request.getParameter("state"));
            int id = Integer.parseInt(request.getParameter("id"));
            if(state==0){
                Way way = new Way();
                int result = way.manUpState(id);
                if(result>0){
                    List<Integer> list1 = way.man_orderId(0);
                    int id1;
                    if(list1.size()>0){
                        List<Order> list2 = new ArrayList<Order>();
                        Order order;
                        for(int i = 0;i<list1.size();i++){
                            id1 = list1.get(i);
                            order = way.man_orderAll(id1);
                            list2.add(order);
                        }
                        request.setAttribute("list_order",list2);
                        request.setAttribute("state","ok");
                        request.getRequestDispatcher(request.getContextPath()+"/manOrder.jsp").forward(request,response);
                    }else{
                        request.setAttribute("no","not");
                        request.getRequestDispatcher(request.getContextPath()+"/manOrder.jsp").forward(request,response);
                    }
                }
            }
    }
}
