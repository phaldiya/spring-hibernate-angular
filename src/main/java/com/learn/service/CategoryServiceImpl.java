package com.learn.service;

import com.learn.domain.Category;
import com.learn.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
/**
 * Created by priyaan-pc on 6/2/2015.
 */
public class CategoryServiceImpl extends BaseServiceImpl<Category,Integer> implements CategoryService  {

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Category save(Category category) throws Exception {
        return categoryRepository.save(category);
    }

    @Override
    public Category find(Integer ID) {
        return categoryRepository.findOne(ID);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
