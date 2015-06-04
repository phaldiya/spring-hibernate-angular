package com.learn.web;

import com.learn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by priyaan-pc on 6/2/2015.
 */
@RestController
@RequestMapping(value = "/secure/api/category")
public class CategoryController {
@Autowired
    CategoryService categoryService;
    @RequestMapping(method = RequestMethod.GET)
    public Object getAll() {
        return categoryService.getAll();
    }
}
