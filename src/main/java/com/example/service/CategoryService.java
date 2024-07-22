package com.example.service;

import com.example.dto.book.BookDtoWithoutCategoryIds;
import com.example.dto.category.CategoryDto;
import com.example.dto.category.CreateCategoryRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDto save(CreateCategoryRequestDto requestDto);

    List<CategoryDto> findAll(Pageable pageable);

    List<BookDtoWithoutCategoryIds> findById(Long id);

    void delete(Long id);

    CategoryDto update(Long id, CreateCategoryRequestDto requestDto);
}
