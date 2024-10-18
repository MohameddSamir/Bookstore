package com.springboot.mvc.bookstore.service;

import com.springboot.mvc.bookstore.entity.User;

public interface UserService {
    void save(User user);
    User findUserByEmail(String email);
    void updateUser(User user);
    User findUserAndBooks(String email);
    void deleteBookFromCart(int bookId,String email);
}
