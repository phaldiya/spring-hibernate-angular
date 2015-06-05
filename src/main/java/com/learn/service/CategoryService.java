package com.learn.service;

import com.learn.domain.Category;

import java.util.List;

/**
 * Created by priyaan-pc on 6/2/2015.
 */
public interface CategoryService extends BaseService<Category,Integer>{
    List<Category> getAll();

    public void delete(Integer id);
    void copyFormBean(Category source, Category destination);

}
