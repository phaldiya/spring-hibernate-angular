package com.learn.web;

import com.learn.domain.Product;
import com.learn.service.CategoryService;
import com.learn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody Product formBean) throws Exception
    {
        return productService.toJson(productService.save(formBean));
    }

    @RequestMapping(value = "/{productId}",method = RequestMethod.DELETE)
    public Object delete(@PathVariable Integer productId) throws Exception
    {
        try {
            Product product = productService.find(productId);
        /*product.setCategory(null);
        productService.save(product);
*/
            productService.delete(product.getProductId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
