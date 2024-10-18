package com.springboot.mvc.bookstore.service;

import com.springboot.mvc.bookstore.dao.BookDAO;
import com.springboot.mvc.bookstore.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    private final BookDAO dao;
    public BookServiceImpl(BookDAO dao){
        this.dao=dao;
    }
    @Override
    public List<Book> findMostRecentHome() {
        return dao.findMostRecentHome();
    }

    @Override
    public List<Book> findBestSellerHome() {
        return dao.findBestSellerHome();
    }

    @Override
    public List<Book> findMostPopularHome() {
        return dao.findMostPopularHome();
    }

    @Override
    public List<Book> findTopRatedHome() {
        return dao.findTopRatedHome();
    }

    @Override
    public List<Book> findMostRecentAll() {
        return dao.findMostRecentAll();
    }

    @Override
    public List<Book> findBestSellerAll() {
        return dao.findBestSellerAll();
    }

    @Override
    public List<Book> findMostPopularAll() {
        return dao.findMostPopularAll();
    }

    @Override
    public List<Book> findTopRatedAll() {
        return dao.findTopRatedAll();
    }

    @Override
    public Book findBookById(int id) {
        return dao.findBookById(id);
    }
}
