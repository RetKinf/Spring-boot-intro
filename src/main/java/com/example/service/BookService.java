package com.example.service;

import com.example.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book requestDto);

    List<Book> findAll();

    Book findById(Long id);
}
