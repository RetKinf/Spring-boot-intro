package com.example.repository;

import com.example.model.Book;
import java.util.Optional;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public Optional<Book> findAll() {
        return Optional.empty();
    }
}
