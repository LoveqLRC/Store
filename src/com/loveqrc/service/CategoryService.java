package com.loveqrc.service;

import com.loveqrc.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll() throws Exception;
}
