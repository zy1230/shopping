package servlet;

import bean.Drug;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SelectUserDrugServlet",value = "/selectUserDrug")
public class SelectUserDrugServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String drugName = request.getParameter("drugName");
        Way way = new Way();
        List<Integer> list = way.userLikeDrug(drugName);
            if(list.size()>0){
                List<Drug> list1 = new ArrayList<Drug>();
                Drug drug;
                for(int i = 0;i<list.size();i++){
                    drug = way.select_drugOne(list.get(i));
                    list1.add(drug);
                }
                request.getSession().setAttribute("list1",list1);
                request.getRequestDispatcher(request.getContextPath()+"/sort1.jsp").forward(request,response);
            }else {
                request.setAttribute("find_no","no");
                request.getRequestDispatcher(request.getContextPath()+"/start.jsp").forward(request,response);
            }

    }
}
