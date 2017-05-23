package com.snowcoders.web.converter;

import com.snowcoders.core.model.Book;
import com.snowcoders.web.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookConverter extends BaseConverter<Book, BookDto> {
    @Override
    public Book convertDtoToModel(BookDto dto) {
        Book book = new Book(dto.getTitle(), dto.getAuthor(), dto.getPrice());//, new HashSet<>());
        book.setId(dto.getId());
        return book;
    }

    @Override
    public BookDto convertModelToDto(Book book) {
        BookDto bookDto = new BookDto(book.getTitle(), book.getAuthor(), book.getPrice());
        bookDto.setId(book.getId());
        return bookDto;
    }
}
