package com.springboot.mvc.bookstore.service;

import com.springboot.mvc.bookstore.dao.UserDAO;
import com.springboot.mvc.bookstore.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
    private final UserDAO dao;
    public UserServiceImpl(UserDAO dao){
        this.dao=dao;
    }
    @Override
    @Transactional
    public void save(User user) {
        user.setRole("ROLE_USER");
        user.setPassword("{noop}"+user.getPassword());
        dao.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return dao.findUserByEmail(email);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public User findUserAndBooks(String email) {
        int id = dao.findUserByEmail(email).getId();
        return dao.findUserAndBooks(id);
    }

    @Override
    @Transactional
    public void deleteBookFromCart(int bookId,String email){
        User user = findUserAndBooks(email);
        user.getBooks().remove(0);
        dao.updateUser(user);
    }
}
