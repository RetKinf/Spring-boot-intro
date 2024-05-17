package com.example.repository;

import com.example.model.Book;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {
    public static final String QUERY_ALL_BOOKS = "SELECT b FROM Book b";
    private final EntityManagerFactory entityManagerFactory;
    private EntityTransaction transaction;

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return List.of();
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.empty();
    }
}
