package com.learn.service;

import com.learn.domain.Person;

import java.util.Collection;
import java.util.List;

public interface PersonService extends BaseService<Person, Integer> {
    List<Person> getAll();
}
