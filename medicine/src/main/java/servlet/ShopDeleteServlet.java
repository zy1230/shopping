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

@WebServlet(name = "ShopDeleteServlet",value = "/shopDelete")
public class ShopDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        int shopId = Integer.parseInt(request.getParameter("shopId"));
        String username = request.getParameter("username");
        Way way = new Way();
        int result = way.shop_delete(shopId);
        if(result>0){
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
            request.setAttribute("result","ok");
            request.getRequestDispatcher(request.getContextPath()+"/WEB-INF/views/shop.jsp").forward(request,response);
        }else {
            response.getWriter().write("出了点问题，稍后删除");
        }
    }
}
