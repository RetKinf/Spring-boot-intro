package com.example.service;

import com.example.dto.book.BookDto;
import com.example.dto.book.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    void delete(Long id);

    BookDto update(Long id, CreateBookRequestDto requestDto);
}
