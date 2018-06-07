package com.loveqrc.web.servlet;

import com.loveqrc.domain.Category;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class BaseServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Class<? extends BaseServlet> clazz = this.getClass();
            System.out.println(clazz);
            String methodName = req.getParameter("method");
            if (methodName == null) {
                methodName = "index";
            }
            System.out.println(methodName);
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            String isDispatcher = (String) method.invoke(this, req, resp);

            if (isDispatcher != null) {
                List<Category> clist = (List<Category>) req.getAttribute("clist");
                req.getRequestDispatcher(isDispatcher).forward(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }




    public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }


}
