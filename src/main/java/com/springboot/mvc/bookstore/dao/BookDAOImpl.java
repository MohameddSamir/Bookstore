package com.springboot.mvc.bookstore.dao;

import com.springboot.mvc.bookstore.entity.Book;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BookDAOImpl implements BookDAO{
    private final EntityManager entityManager;
    public BookDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    public List<Book> findMostRecentHome() {
        return entityManager.createQuery("FROM Book ORDER BY publication_year DESC LIMIT 5", Book.class).getResultList();
    }

    @Override
    public List<Book> findBestSellerHome() {
        return entityManager.createQuery("FROM Book ORDER BY price ASC LIMIT 5", Book.class).getResultList();
    }

    @Override
    public List<Book> findMostPopularHome() {
        return entityManager.createQuery("FROM Book WHERE id BETWEEN 419 AND 428", Book.class).getResultList();
    }

    @Override
    public List<Book> findTopRatedHome() {
        return entityManager.createQuery("FROM Book WHERE id BETWEEN 370 AND 386", Book.class).getResultList();
    }

    @Override
    public List<Book> findMostRecentAll() {
        return entityManager.createQuery("FROM Book ORDER BY publication_year DESC LIMIT 70", Book.class).getResultList();
    }

    @Override
    public List<Book> findBestSellerAll() {
        return entityManager.createQuery("FROM Book ORDER BY price ASC LIMIT 70", Book.class).getResultList();
    }

    @Override
    public List<Book> findMostPopularAll() {
        return entityManager.createQuery("FROM Book WHERE id>=419", Book.class).getResultList();
    }

    @Override
    public List<Book> findTopRatedAll() {
        return entityManager.createQuery("FROM Book WHERE id>=370", Book.class).getResultList();
    }

    @Override
    public Book findBookById(int id) {
        return entityManager.find(Book.class,id);
    }

}
