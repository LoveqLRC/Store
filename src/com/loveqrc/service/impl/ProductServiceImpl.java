package com.loveqrc.service.impl;

import com.loveqrc.dao.ProductDao;
import com.loveqrc.dao.impl.ProductDaoImpl;
import com.loveqrc.domain.Product;
import com.loveqrc.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> findNew() throws Exception {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.findNew();
    }

    @Override
    public List<Product> findHot() throws Exception {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.findNew();
    }

    @Override
    public Product getByPid(String pid) throws Exception {
        return null;
    }
}
