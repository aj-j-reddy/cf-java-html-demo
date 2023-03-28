package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Books;
import com.example.demo.service.BookService;

// import com.sap.alicloud.hc.SpringBootHANADemo.service.NativeSQLRunner;

@RestController
public class BooksController {
    @Autowired
    BookService bookService;

    @RequestMapping("/srv/hello")
    public String hello() {

        return "Hello!";
    }

    @RequestMapping("/srv/list")
    public List<Books> readAllBooks() {
        return bookService.readBooks();
    }

}