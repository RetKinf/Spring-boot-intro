package com.example.service;

import com.example.model.Book;
import com.example.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return book;
    }

    @Override
    public List<Book> findAll() {
        return List.copyOf(bookRepository.findAll());
    }

    @Override
    public Book findById(Long id) {
        return null;
    }
}
