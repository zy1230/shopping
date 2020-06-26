package servlet;

import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddRemarksServlet",value = "/addRemarks")
public class AddRemarksServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String remarks = request.getParameter("remarks");
        String drugName = request.getParameter("drugName");
        Way way = new Way();
        int result = way.addRemarks(drugName,remarks);
        if(result>0){
            request.setAttribute("resRemarks","ok");
            request.getRequestDispatcher(request.getContextPath()+"/manDrug.jsp").forward(request,response);
        }else{
            request.setAttribute("noRemarks","no");
            request.getRequestDispatcher(request.getContextPath()+"/manDrug.jsp").forward(request,response);
        }
    }
}
