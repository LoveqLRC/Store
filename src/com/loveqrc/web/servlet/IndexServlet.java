package com.loveqrc.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = "/index"
)
public class IndexServlet extends BaseServlet {

    public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //去数据库中查询最新商品和热门商品  将他们放入request域中 请求转发
        return "/jsp/index.jsp";
    }
}
