package servlet;

import bean.Drug;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet",value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           response.setContentType("text/html;charset=utf-8");
           request.setCharacterEncoding("UTF-8");
           String loginTwo = request.getParameter("login");
           String upass = request.getParameter("upass");
           Way way = new Way();
           String isLogin;
           String username;
           if(loginTwo.length()==6){
               isLogin = Way.isLogin(loginTwo,upass);
               username = loginTwo;
           }else{
               isLogin = Way.isLogin1(loginTwo,upass);
               username = way.select_username(loginTwo);
           }
           HttpSession session = request.getSession();
           if(isLogin.equals("用户")){
               session.setAttribute("username",username);
               List<Integer> list1 = way.start_DrugId();
               if (list1.size()>=6){
                   List<Drug> list2 = new ArrayList<Drug>();
                   Drug drug;
                   for(int i = 0;i<list1.size();i++){
                       drug = way.start_drug(list1.get(i));
                       list2.add(drug);
                   }
                   List<String> list = way.select_show();
                   if(list.size()>3){
                       List<String> list_1 = new ArrayList<String>();
                       for(int i = 0;i<3;i++){
                           list_1.add(list.get(i));
                       }
                       request.getSession().setAttribute("heal_show",list_1);
                       request.getSession().setAttribute("start_drug",list2);
                       request.getRequestDispatcher(request.getContextPath()+"/start.jsp").forward(request,response);
                  }else {
                       List<String> list_1 = way.select_show();
                       request.getSession().setAttribute("heal_show",list_1);
                       request.getSession().setAttribute("start_drug",list2);
                       request.getRequestDispatcher(request.getContextPath()+"/start.jsp").forward(request,response);
                   }
               }else{
                   List<Drug> list2 = new ArrayList<Drug>();
                   List<String> list_22 = way.select_show();
                   Drug drug;
                   for(int i = 0;i<6;i++){
                       drug = way.select_drugOne(i);
                       list2.add(drug);
                   }
                   if(list_22.size()>3){
                       List<String> list_2 = new ArrayList<String>();
                       for(int j = 0;j<3;j++){
                           list_2.add(list_22.get(j));
                       }
                       request.getSession().setAttribute("start_drug",list2);
                       request.getSession().setAttribute("heal_show",list_2);
                       request.getRequestDispatcher(request.getContextPath()+"/start.jsp").forward(request,response);
                   }else {
                       request.getSession().setAttribute("start_drug",list2);
                       request.getSession().setAttribute("heal_show",list_22);
                       request.getRequestDispatcher(request.getContextPath()+"/start.jsp").forward(request,response);
                   }
               }
           }else if(isLogin.equals("管理员")||isLogin.equals("超级管理员")){
               session.setAttribute("username",username);
               session.setAttribute("identity",isLogin);
               request.getRequestDispatcher(request.getContextPath()+"/manager.jsp").forward(request,response);
           }else if(upass!=null){
               List<String> list2 = way.select_userName_login();
               if(list2.size()>0){
                   for(int i = 0;i<list2.size();i++){
                       if(username.equals(list2.get(i))){
                           request.setAttribute("username",username);
                           request.setAttribute("no1","no");
                           request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request,response);
                       }else if(i==list2.size()-1){
                           request.getRequestDispatcher(request.getContextPath()+"/register.jsp").forward(request,response);
                       }
                   }
               }
           }else{
               request.getRequestDispatcher(request.getContextPath()+"/register.jsp").forward(request,response);
           }
    }

}
