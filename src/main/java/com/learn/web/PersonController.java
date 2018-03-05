package com.learn.web;

import com.learn.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/secure/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public Object getAllPersons() throws Exception {
        return personService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object find(@PathVariable Integer id) throws Exception { return personService.toJson(personService.find(id)); }
}
