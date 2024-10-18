package com.springboot.mvc.bookstore.dao;

import com.springboot.mvc.bookstore.entity.User;

public interface UserDAO {
    void save(User user);
    User findUserByEmail(String email);
    void updateUser(User user);
    User findUserAndBooks(int id);
}
