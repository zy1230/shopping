package servlet;

import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ManUpPriceServlet",value = "/manUpPrice")
public class ManUpPriceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String drugName = request.getParameter("drugName");
        double drugPrice = Double.parseDouble(request.getParameter("drugPrice"));
        Way way = new Way();
        int result = way.man_updatePrice(drugName,drugPrice);
        System.out.println(drugName);
        System.out.println(drugPrice);
        if(result>0){
            request.setAttribute("result_price","ok");
            request.getRequestDispatcher(request.getContextPath()+"/manDrug.jsp").forward(request,response);
        }else{
            request.setAttribute("res_price","no");
            request.getRequestDispatcher(request.getContextPath()+"/manDrug.jsp").forward(request,response);
        }
    }
}
