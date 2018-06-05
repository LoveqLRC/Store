package com.loveqrc.service.impl;

import com.loveqrc.dao.UserDao;
import com.loveqrc.dao.impl.UserDaoImpl;
import com.loveqrc.domain.User;
import com.loveqrc.service.UserService;
import com.loveqrc.utils.MailUtils;

public class UserServiceImpl implements UserService {

    @Override
    public void regist(User user) throws Exception {
        UserDao userDao = new UserDaoImpl();
        userDao.add(user);

        String emailMsg = "欢迎您注册成我们的一员,<a href='http://localhost:8080/user?method=active&code=" + user.getCode() + "'>点此激活</a>";
        MailUtils.sendMail(user.getEmail(), emailMsg);
    }

    @Override
    public User active(String code) throws Exception {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getByCode(code);

        if (user == null) {
            return null;
        }
        user.setState(1);
        userDao.update(user);
        return user;
    }

    @Override
    public User login(String username, String password) throws Exception {
        UserDao userDao = new UserDaoImpl();
        return userDao.getByUsernameAndPwd(username, password);
    }
}
