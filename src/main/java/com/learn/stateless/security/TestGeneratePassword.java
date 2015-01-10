package com.learn.stateless.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestGeneratePassword {

    public static void main(String ... args) {
        String password = "user";
        String encodedPassword = new BCryptPasswordEncoder().encode(password).toString();
        System.out.println("["+ password+"] password is ["+encodedPassword+"]");
    }
}
