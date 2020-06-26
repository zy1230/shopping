package servlet;

import bean.User;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MoneyServlet",value = "/money")
public class MoneyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          response.setContentType("text/html;charset=utf-8");
          request.setCharacterEncoding("UTF-8");
          String drugName = request.getParameter("drugName");
          int piece = Integer.parseInt(request.getParameter("piece"));
          double money = Double.parseDouble(request.getParameter("money"));
          String username = request.getParameter("username");
          Way way = new Way();
          User user = way.user_shop(username);
          request.setAttribute("drug",drugName);
          request.setAttribute("piece",piece);
          request.setAttribute("money",money);
          request.setAttribute("user",user);
          request.getRequestDispatcher(request.getContextPath()+"/WEB-INF/views/money.jsp").forward(request,response);
    }
}
