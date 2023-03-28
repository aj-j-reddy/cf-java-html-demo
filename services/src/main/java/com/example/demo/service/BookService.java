package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.model.NativeSQL;
import com.example.demo.model.Books;

@Service
public class BookService {
    private static int MAX_RUM = 100;

    @Autowired
    NativeSQL nativeSQL;

    public List<Books> readBooks() {
        nativeSQL.createConnection();
        List<Books> booksList = nativeSQL.getBooks();
        nativeSQL.closeConnection();
        return booksList;
    }
}