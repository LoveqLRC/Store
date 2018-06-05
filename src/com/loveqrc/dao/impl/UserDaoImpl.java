package com.loveqrc.dao.impl;

import com.loveqrc.dao.UserDao;
import com.loveqrc.domain.User;
import com.loveqrc.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.taglibs.standard.tag.common.sql.DataSourceUtil;

public class UserDaoImpl implements UserDao {

    @Override
    public void add(User user) throws Exception {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?);";
        queryRunner.update(sql, user.getUid(), user.getUsername(), user.getPassword(),
                user.getName(), user.getEmail(), user.getTelephone(),
                user.getBirthday(), user.getSex(), user.getState(), user.getCode());
    }

    @Override
    public User getByCode(String code) throws Exception {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where code = ? limit 1";

        return queryRunner.query(sql, new BeanHandler<>(User.class), code);

    }

    @Override
    public void update(User user) throws Exception {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update user set username = ?,password = ? ,name=?,email=?,birthday = ?,state = ?,code=? where uid =? ";
        queryRunner.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getBirthday(),
                user.getState(), null, user.getUid());

    }

    @Override
    public User getByUsernameAndPwd(String username, String password) throws Exception {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where username =? and password = ? limit 1";
        return queryRunner.query(sql, new BeanHandler<>(User.class), username, password);
    }
}
