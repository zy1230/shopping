package servlet;

import bean.Drug;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ManAddDrugServlet",value = "/manAddDrug")
public class ManAddDrugServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html;charset=utf-8");
         request.setCharacterEncoding("UTF-8");
         String drugName = request.getParameter("drugName");
         double drugPrice = Double.parseDouble(request.getParameter("drugPrice"));
         String drugEffect = request.getParameter("drugEffect");
         String drugUse = request.getParameter("drugUse");
         String drugTaboo = request.getParameter("drugTaboo");
         String drugClassification = request.getParameter("drugClass");
         String drugRemarks = request.getParameter("drugRemarks");
         Drug drug = new Drug(drugName,drugEffect,drugPrice,drugUse,drugTaboo,drugClassification,drugRemarks);
         Way way = new Way();
         int result = way.man_insertDrug(drug);
         if(result>0){
             int id = way.manMaxDrugId();
             String id_2 = String.valueOf(id);
             request.setAttribute("insertDrug","ok");
             request.setAttribute("drugName",drugName);
             request.setAttribute("drugId",id_2);
             request.getRequestDispatcher(request.getContextPath()+"/manDrug.jsp").forward(request,response);
         }else {
             request.setAttribute("insertNo","no");
             request.getRequestDispatcher(request.getContextPath()+"manDrug.jsp").forward(request,response);
         }
    }
}
