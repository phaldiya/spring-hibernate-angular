package com.learn.repository;

import com.learn.domain.Person;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PersonRepository extends CustomJpaRepository<Person, Integer> {
    @Query("select p from Person p")
    List<Person> getAll();
}
