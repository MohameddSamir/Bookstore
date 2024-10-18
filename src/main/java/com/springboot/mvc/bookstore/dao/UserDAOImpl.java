package com.springboot.mvc.bookstore.dao;

import com.springboot.mvc.bookstore.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{
    private final EntityManager entityManager;
    public UserDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findUserByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("FROM User Where email=:data",User.class);
        query.setParameter("data",email);
        return query.getSingleResult();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findUserAndBooks(int id) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u " +
                        "JOIN FETCH u.books " +
                        "WHERE u.id=:data", User.class
        );
        query.setParameter("data",id);
        return query.getSingleResult();
    }


}
