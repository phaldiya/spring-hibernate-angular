package com.learn.service;

import com.learn.domain.Product;
import com.learn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by priyaan-pc on 6/3/2015.
 */
@Service
@Transactional
public class ProductServiceImpl extends BaseServiceImpl<Product,Integer> implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Override
    public Product save(Product product) throws Exception {
        return productRepository.save(product);
    }

    @Override
    public Product find(Integer Id) {
        return productRepository.findOne(Id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
