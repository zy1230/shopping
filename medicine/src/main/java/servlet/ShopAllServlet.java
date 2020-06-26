package servlet;

import bean.Shop;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShopAllServlet",value = "/shopAll")
public class ShopAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        Way way = new Way();
        List<Integer> list = way.select_shopId(username);
        List<Shop> list1 = new ArrayList<Shop>();
        Shop shop;
        int id;
        for (int i = 0 ; i<list.size();i++){
            id = list.get(i);
            shop = way.select_shopAll(id);
            list1.add(shop);
        }
        request.setAttribute("list1_shop",list1);
        request.getRequestDispatcher(request.getContextPath()+"/WEB-INF/views/shop.jsp").forward(request,response);
    }
}
