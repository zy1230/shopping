package servlet;

import bean.Health;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "AllHealServlet",value = "/allHealth")
public class AllHealServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        Way way = new Way();
        List<Integer> list = way.select_healId();
        System.out.println(list.get(2));
        Health health;
        List<Health> list1 = new ArrayList<Health>();
        for(int i = 0;i<list.size();i++){
            health = way.select_heal(list.get(i));
            list1.add(health);
        }
        if(list1.size()>0) {
            request.setAttribute("all_heal", list1);
            request.getRequestDispatcher(request.getContextPath() + "/manHealth.jsp").forward(request,response);
        }
    }
}
