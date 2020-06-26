package servlet;

import db.Way;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

@MultipartConfig
@WebServlet(name = "DrugPictureServlet",value = "/drugPicture")
public class DrugPictureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        Iterator iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            if (item.isFormField()) {
                String fieldName = item.getFieldName();
                String value = item.getString();
                request.setAttribute(fieldName, value);
            }
            else {
                String fileName = item.getName();
                String url = "/view/picture/"+fileName;
                int index = fileName.lastIndexOf("\\");
                fileName = fileName.substring(index + 1);
                request.setAttribute("realFileName", fileName);
                String basePath = request.getRealPath("/view/picture");
                File file = new File(basePath, fileName);
                try {
                    item.write(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Way way = new Way();
                int maxId = way.manMaxDrugId();
                if(maxId>0){
                    int result = way.manUpPicture(maxId,url);
                    if(result>0){
                        request.setAttribute("upPicture","ok");
                        request.getRequestDispatcher(request.getContextPath()+"/manDrug.jsp").forward(request, response);
                    }else{
                        request.setAttribute("upNo","no");
                        request.getRequestDispatcher(request.getContextPath()+"/manDrug.jsp").forward(request,response);
                    }
                }else{
                    request.setAttribute("idNo","no");
                    request.getRequestDispatcher(request.getContextPath()+"manDrug.jsp").forward(request,response);
                }
            }




        }
    }
}
