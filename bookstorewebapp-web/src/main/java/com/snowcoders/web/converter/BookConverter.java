package com.snowcoders.web.converter;

import com.snowcoders.core.model.Book;
import com.snowcoders.web.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookConverter extends BaseConverter<Book, BookDto> {
    @Override
    public Book convertDtoToModel(BookDto dto) {
        Book book = Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .price(dto.getPrice())
                .build();
        book.setId(dto.getId());
        return book;
    }

    @Override
    public BookDto convertModelToDto(Book book) {
        BookDto bookDto = BookDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .build();
        bookDto.setId(book.getId());
        return bookDto;
    }
}
