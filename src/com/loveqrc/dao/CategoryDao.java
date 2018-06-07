package com.loveqrc.dao;

import com.loveqrc.domain.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll() throws Exception;
}
