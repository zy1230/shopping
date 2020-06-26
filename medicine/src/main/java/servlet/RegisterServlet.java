package servlet;

import bean.User;
import db.Way;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegisterServlet",value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String uname = request.getParameter("uname");
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        User user1 = new User();
        user1.setUname(uname);
        user1.setUsername(username);
        user1.setPhone(phone);
        user1.setAddress(address);
        user1.setUpass(password);
        Way way = new Way();
        String have = "";
        List<String> list = way.select_userName();
        String username2;
        for(int i = 0;i<list.size();i++){
            username2 = list.get(i);
            if(username.equals(username2)){
                request.setAttribute("no","登录名已存在");
                request.setAttribute("user1",user1);
                have = "登录名";
                request.getRequestDispatcher(request.getContextPath()+"/register.jsp").forward(request,response);

            }
        }

        List<String> list1 = way.select_phone();
        String phone1;
        for(int i = 0;i<list1.size();i++){
            phone1 = list1.get(i);
            if(phone.equals(phone1)) {
                request.setAttribute("no1", "手机号已经注册");
                request.setAttribute("user1", user1);
                have = "手机号";
                request.getRequestDispatcher(request.getContextPath() + "/register.jsp").forward(request,response);
            }
        }
        if(have.equals("")){
            User user = new User(uname,username,password,phone,address,"用户");
            int  result = way.insert_user(user);
            if(result>0){
                response.sendRedirect("login.jsp");
            }else {
                response.getWriter().write("There is something wrong");
            }
        }
    }
}
