package com.loveqrc.web.servlet;

import com.loveqrc.constant.Constant;
import com.loveqrc.converter.MyConverter;
import com.loveqrc.domain.User;
import com.loveqrc.service.UserService;
import com.loveqrc.service.impl.UserServiceImpl;
import com.loveqrc.utils.MD5Utils;
import com.loveqrc.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(
        urlPatterns = "/user"
)
public class UserServlet extends BaseServlet {


    public String register(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = new User();
        ConvertUtils.register(new MyConverter(), Date.class);
        BeanUtils.populate(user, req.getParameterMap());
        UserService userService = new UserServiceImpl();

        user.setUid(UUIDUtils.getId());
        user.setCode(UUIDUtils.getCode());

        user.setPassword(MD5Utils.md5(user.getPassword()));

        userService.regist(user);

        req.setAttribute("msg", "用户注册已成功，请去邮箱激活");
        System.out.println("register方法执行");

        return "/jsp/msg.jsp";
    }

    public String active(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String code = req.getParameter("code");
        UserService userService = new UserServiceImpl();
        User user = userService.active(code);
        if (user == null) {
            req.setAttribute("msg", "请重新激活");
        } else {
            req.setAttribute("msg", "激活成功");
        }

        return "/jsp/msg.jsp";

    }


    public String loginUi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("loginUi方法执行");
        return "/jsp/login.jsp";
    }

    public String registerUi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("registerUi方法执行");
        return "/jsp/register.jsp";
    }


    public String login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        password = MD5Utils.md5(password);

        UserService userService = new UserServiceImpl();
        User user = userService.login(username, password);
        if (user == null) {
            req.setAttribute("msg", "用户名密码不匹配");
            return "/jsp/login.jsp";
        } else {
            if (Constant.USER_IS_ACTIVE != user.getState()) {
                req.setAttribute("msg", "用户未激活");
                return "/jsp/login.jsp";
            }
        }
        req.getSession().setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/");

        return null;
    }

    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //干掉session
        request.getSession().invalidate();

        //重定向
        response.sendRedirect(request.getContextPath());

        //处理自动登录

        return null;
    }
}
