package com.loveqrc.service.impl;

import com.loveqrc.dao.CategoryDao;
import com.loveqrc.dao.impl.CategoryDaoImpl;
import com.loveqrc.domain.Category;
import com.loveqrc.service.CategoryService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.InputStream;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<Category> findAll() throws Exception {
        CacheManager cacheManager = CacheManager
                .create(CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));

        Cache cache = cacheManager.getCache("categoryCache");

        Element element = cache.get("clist");
        List<Category> categoryList = null;
        if (element == null) {
            System.out.println("没有缓存数据");
            CategoryDao categoryDao = new CategoryDaoImpl();
            categoryList = categoryDao.findAll();

            cache.put(new Element("clist", categoryList));
        } else {
            System.out.println("有缓存数据");
            categoryList = ((List<Category>) element.getObjectValue());

        }

        return categoryList;
    }

    public static void main(String[] args) {
        InputStream is = CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml");
        System.out.println(is);
    }

}
