package com.springboot.mvc.bookstore.controller;

import com.springboot.mvc.bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
public class HomeController {
    private final BookService service;
    public HomeController(BookService service){
        this.service=service;
    }

    @GetMapping("/home")
    public String home(Model theModel){
        theModel.addAttribute("mostRecentHome",service.findMostRecentHome());
        theModel.addAttribute("bestSellerHome",service.findBestSellerHome());
        theModel.addAttribute("MostPopularHome",service.findMostPopularHome());
        theModel.addAttribute("TopRatedHome",service.findTopRatedHome());
        return "home-page";
    }

    @GetMapping("/recent")
    public String recent(Model theModel){
        theModel.addAttribute("books",service.findMostRecentAll());
        theModel.addAttribute("title","Most Recent");
        return "category-page";
    }

    @GetMapping("/best-seller")
    public String best_seller(Model theModel){
        theModel.addAttribute("books",service.findBestSellerAll());
        theModel.addAttribute("title","Best Seller");
        return "category-page";
    }

    @GetMapping("/top-rated")
    public String top_rated(Model theModel){
        theModel.addAttribute("books",service.findTopRatedAll());
        theModel.addAttribute("title","Top Rated");
        return "category-page";
    }

    @GetMapping("/most-popular")
    public String most_popular(Model theModel){
        theModel.addAttribute("books",service.findMostPopularAll());
        theModel.addAttribute("title","Most Popular");
        return "category-page";
    }

    @GetMapping("/book/{bookId}")
    public String bookDetails(@PathVariable("bookId") int id,Model theModel){
        theModel.addAttribute("book",service.findBookById(id));
        return "details-page";
    }

}
