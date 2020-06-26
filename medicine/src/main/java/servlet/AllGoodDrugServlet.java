package servlet;

import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AllGoodDrugServlet",value = "/allGoodDrug")
public class AllGoodDrugServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         Way way = new Way();
        List<String> list = way.goodDrugName();
        if(list.size()>0){
            request.setAttribute("listGood",list);
            request.getRequestDispatcher(request.getContextPath()+"/manDrug.jsp").forward(request,response);
        }else{
            request.setAttribute("NoGood","no");
            request.getRequestDispatcher(request.getContextPath()+"/manDrug.jsp").forward(request,response);
        }


    }
}
