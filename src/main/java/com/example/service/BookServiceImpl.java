package com.example.service;

import com.example.dto.book.BookDto;
import com.example.dto.book.CreateBookRequestDto;
import com.example.exception.EntityNotFoundException;
import com.example.mapper.BookMapper;
import com.example.model.Book;
import com.example.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        return bookMapper.toDto(
                bookRepository.save(bookMapper.toModel(requestDto))
        );
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(bookRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Not found book with id " + id)
        ));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto requestDto) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found book with id " + id)
        );
        bookMapper.updateBookFromDto(requestDto, book);
        return bookMapper.toDto(bookRepository.save(book));
    }
}
