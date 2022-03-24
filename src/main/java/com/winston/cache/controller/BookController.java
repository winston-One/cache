package com.winston.cache.controller;

import com.winston.cache.domin.Book;
import com.winston.cache.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    @GetMapping()
    public Book getById(@RequestParam Integer id) {
        System.out.println("getById IS running");
        Book book = new Book();
        Book byId = bookService.getById(id);
        return byId;
    }
}
