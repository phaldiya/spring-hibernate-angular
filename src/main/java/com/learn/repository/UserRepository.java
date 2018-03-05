package com.learn.repository;


import com.learn.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u")
    List<User> getAll();

    @Query("select u from User u where u.username = ?1")
    User findByUsername(String username);

}