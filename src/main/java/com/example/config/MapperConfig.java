package com.example.config;

import com.example.model.Book;
import com.example.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;

@org.mapstruct.MapperConfig(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl"
)
@RequiredArgsConstructor
public class MapperConfig {
    private final BookRepository bookRepository;

    @Named("bookFromId")
    public Book bookFromId(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Book not found with id " + id
                ));
    }
}
