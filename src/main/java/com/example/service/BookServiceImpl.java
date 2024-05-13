package com.example.service;

import com.example.model.Book;
import com.example.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return List.of(
                bookRepository.findAll()
                        .orElseThrow(() -> new EntityNotFoundException("Book not found")
                        ));
    }
}
