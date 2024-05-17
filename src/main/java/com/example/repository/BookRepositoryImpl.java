package com.example.repository;

import com.example.exception.DataProcessingException;
import com.example.model.Book;
import jakarta.persistence.EntityManager;
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
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction.isActive() && transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not save book", e);
        }
    }

    @Override
    public List<Book> findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery(QUERY_ALL_BOOKS, Book.class).getResultList();
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return Optional.ofNullable(entityManager.find(Book.class, id));
        }
    }
}
