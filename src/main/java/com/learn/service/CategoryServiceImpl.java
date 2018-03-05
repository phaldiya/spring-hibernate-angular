package com.learn.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.learn.domain.Category;
import com.learn.domain.Product;
import com.learn.repository.CategoryRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
/**
 * Created by priyaan-pc on 6/2/2015.
 */
public class CategoryServiceImpl extends BaseServiceImpl<Category,Integer> implements CategoryService  {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductService productService;

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

    @Override
    public void  delete(Integer id) {
         categoryRepository.delete(id);
    }

    @Override
    public void copyFormBean(Category source, Category destination) {
        BeanUtils.copyProperties(source, destination, ArrayUtils.addAll(IGNORE_PROPERTIES, "product"));
    }

    @Override
    public String toJsonWithChildrens(Collection<Category> categories) throws Exception {
        StringWriter sw = new StringWriter();
        JsonFactory factory = new JsonFactory();
        JsonGenerator json = factory.createGenerator(sw);

        json.writeStartArray();
        for (Category category : categories) {
            json.writeStartObject();
            json.writeNumberField("id", category.getId());
            json.writeStringField("name", category.getName());
            json.writeStringField("discription", category.getDiscription());

            json.writeFieldName("product");
            json.writeStartArray();
            for (Product product: category.getProduct()) {
                json.writeRawValue(productService.toJson(product));
            }
            json.writeEndArray();
            json.writeEndObject();
        }
        json.writeEndArray();
        json.close();

        return sw.toString();
    };

    @Override
    public String toJson(Collection<Category> categories) throws Exception {
        StringWriter sw = new StringWriter();
        JsonFactory factory = new JsonFactory();
        JsonGenerator json = factory.createGenerator(sw);

        json.writeStartArray();
        for (Category category : categories) {
            json.writeStartObject();
            json.writeNumberField("id", category.getId());
            json.writeStringField("name", category.getName());
            /*json.writeStringField("discription", category.getDiscription());*/
            json.writeEndObject();
        }
        json.writeEndArray();
        json.close();

        return sw.toString();
    }
}
