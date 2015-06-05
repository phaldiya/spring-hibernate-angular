package com.learn.service;

import com.learn.domain.Product;

import java.util.List;

/**
 * Created by priyaan-pc on 6/3/2015.
 */
public interface ProductService extends BaseService<Product,Integer> {
    List<Product> getAll();

    public void delete(Integer productId);
}
