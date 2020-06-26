package servlet;

import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateGoodServlet",value = "/updateGood")
public class UpdateGoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String drugName = request.getParameter("drugName");
        Way way = new Way();
        int result = way.updateGood(drugName);
        if(result>0){
            List<String> list = way.goodDrugName();
            request.setAttribute("listGood",list);
            request.setAttribute("upGoodRes","ok");
            request.getRequestDispatcher(request.getContextPath()+"/manDrug.jsp").forward(request,response);
        }else{
            request.setAttribute("noUpGood","no");
            request.getRequestDispatcher(request.getContextPath()+"/manDrug.jsp").forward(request,response);
        }
    }
}
