package com.springboot.mvc.bookstore.dao;

import com.springboot.mvc.bookstore.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> findMostRecentHome();
    List<Book> findBestSellerHome();
    List<Book> findMostPopularHome();
    List<Book> findTopRatedHome();
    List<Book> findMostRecentAll();
    List<Book> findBestSellerAll();
    List<Book> findMostPopularAll();
    List<Book> findTopRatedAll();
    Book findBookById(int id);
}
