package com.springboot.mvc.bookstore.controller;

import com.springboot.mvc.bookstore.entity.User;
import com.springboot.mvc.bookstore.service.BookService;
import com.springboot.mvc.bookstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;
    private final BookService bookService;
    public UserController(UserService userService,BookService bookService){
        this.userService=userService;
        this.bookService=bookService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("/login")
    public String login(){
        return "login-page";
    }
    @GetMapping("/register")
    public String register(Model theModel){
        theModel.addAttribute("user",new User());
        return "register-page";
    }
    @PostMapping("/save")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult theBindingResult){
        if(theBindingResult.hasErrors()){
            return "register-page";
        }else {
            userService.save(user);
            return "login-page";
        }
    }

    @GetMapping("/add-to-cart/{bookId}")
    public String addToCart(@PathVariable("bookId") int bookId,@RequestParam("email")String email){
        User user = userService.findUserByEmail(email);
        user.addBook(bookService.findBookById(bookId));
        userService.updateUser(user);
        return "redirect:/store/home";
    }
    @GetMapping("/cart")
    public String showCart(@RequestParam("email") String email,Model theModel){
        User user = userService.findUserAndBooks(email);
        theModel.addAttribute("books",user.getBooks());
        return "cart-page";
    }
    @GetMapping("/buyBook")
    public String buyBook(){
        return "purchase-page";
    }

    @GetMapping("/deleteBook/{bookId}")
    public String deleteBook(@PathVariable("bookId") int bookId,@RequestParam("email") String email){
        userService.deleteBookFromCart(bookId,email);
        return "redirect:/cart?email="+email;
    }
}
