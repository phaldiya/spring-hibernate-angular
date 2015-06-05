package com.learn.web;

import com.learn.domain.Category;
import com.learn.domain.Hospital;
import com.learn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by priyaan-pc on 6/2/2015.
 */
@RestController
@RequestMapping(value = "/secure/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public Object getAll() throws Exception {
        return categoryService.toJsonWithChildrens(categoryService.getAll());
    }

    @RequestMapping(value="/dropdown", method = RequestMethod.GET)
    public Object getCategoryDropdown() throws Exception {
        return categoryService.toJson(categoryService.getAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody Category formBean) throws Exception
    {
        return  categoryService.toJson(categoryService.save(formBean));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Object delete(@PathVariable Integer id)
    {
        categoryService.delete(id);
        return true;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Object update(@PathVariable Integer id, @RequestBody Category formBean) throws Exception
    {
        Category category = categoryService.find(id);
        categoryService.copyFormBean(formBean, category);

        return  categoryService.toJson(categoryService.save(category));
    }
}
