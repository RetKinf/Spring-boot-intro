package com.example.mapper;

import com.example.config.MapperConfig;
import com.example.dto.book.BookDto;
import com.example.dto.book.CreateBookRequestDto;
import com.example.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);

    void updateBookFromDto(CreateBookRequestDto book, @MappingTarget Book entity);
}
