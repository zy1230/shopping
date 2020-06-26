package servlet;

import bean.Shop;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShopServlet",value = "/shopInsert")
public class ShopServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html;charset=utf-8");
         request.setCharacterEncoding("UTF-8");
         String username = request.getParameter("username");
         String drugName = request.getParameter("drugName");
         double drugPrice = Double.parseDouble(request.getParameter("drugPrice"));
         int piece = Integer.parseInt(request.getParameter("piece"));
         Shop shop = new Shop(username,piece,drugName,drugPrice);
         Way way = new Way();
         int result = way.insert_shop(shop);
         if(result>0){
             request.setAttribute("result","ok");
             request.getRequestDispatcher(request.getContextPath()+"detail.jsp").forward(request,response);
         }else{
             response.getWriter().write("加入失败，请稍后加入");
         }

    }
}
