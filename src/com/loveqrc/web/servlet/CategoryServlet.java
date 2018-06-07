package com.loveqrc.web.servlet;

import com.loveqrc.domain.Category;
import com.loveqrc.service.CategoryService;
import com.loveqrc.service.impl.CategoryServiceImpl;
import com.loveqrc.utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        urlPatterns = "/category"
)
public class CategoryServlet extends BaseServlet {
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //去数据库中查询最新商品和热门商品  将他们放入request域中 请求转发
        CategoryService service = new CategoryServiceImpl();
        List<Category> list = null;

        try {
            list = service.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        request.setAttribute("clist", list);
        String json = JsonUtil.list2json(list);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(json);
        return null;
    }
}
