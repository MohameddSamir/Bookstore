package com.springboot.mvc.bookstore.controller;

import com.springboot.mvc.bookstore.entity.Book;
import com.springboot.mvc.bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class AdminController {
    private final BookService service;
    public AdminController(BookService service){
        this.service=service;
    }

    @GetMapping("/add-new")
    public String addNewBook(Model theModel){
        theModel.addAttribute("book",new Book());
        return "add-book-form";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book){
        service.save(book);
        return "redirect:/book/add-new";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("bookId")int id){
        service.deleteBookById(id);
        return "redirect:/store/home";
    }
}
