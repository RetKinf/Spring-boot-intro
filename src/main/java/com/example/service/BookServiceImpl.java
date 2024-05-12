package com.example.service;

import com.example.model.Book;
import com.example.repository.BookRepository;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return List.of();
    }
}
