package com.example.repository;

import com.example.model.Book;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);

    Optional<Book> findAll();
}
