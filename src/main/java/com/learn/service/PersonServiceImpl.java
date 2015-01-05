package com.learn.service;

import com.learn.domain.Person;
import com.learn.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl extends BaseServiceImpl<Person, Integer> implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person find(Integer id) { return personRepository.findOne(id); }

    @Override
    public Person save(Person entity) {
        return personRepository.save(entity);
    }

    public  List<Person> getAll() {
        return personRepository.getAll();
    }
}