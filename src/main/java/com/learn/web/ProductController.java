package com.learn.web;

import com.learn.service.CategoryService;
import com.learn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by priyaan-pc on 6/3/2015.
 */
@RestController
@RequestMapping(value = "/secure/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public Object getAll() {
        return productService.getAll();
    }
}
