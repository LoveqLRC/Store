package com.loveqrc.web.servlet;

import com.loveqrc.domain.Category;
import com.loveqrc.domain.Product;
import com.loveqrc.domain.User;
import com.loveqrc.service.CategoryService;
import com.loveqrc.service.ProductService;
import com.loveqrc.service.impl.CategoryServiceImpl;
import com.loveqrc.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        urlPatterns = "/index"
)
public class IndexServlet extends BaseServlet {

    public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductServiceImpl();
        List<Product> hotList = null;
        List<Product> newList = null;

        try {
            hotList = productService.findHot();
            newList = productService.findNew();

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("nList", newList);
        request.setAttribute("hList", hotList);

        return "/jsp/index.jsp";
    }
}
