package com.snowcoders.web.controller;

import com.snowcoders.core.model.Book;
import com.snowcoders.core.service.BookService;
import com.snowcoders.web.converter.BookConverter;
import com.snowcoders.web.dto.BookDto;
import com.snowcoders.web.dto.BooksDto;
import com.snowcoders.web.dto.EmptyJsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    protected BookService bookService;

    @Autowired
    protected BookConverter bookConverter;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public BooksDto getAll() {
        return new BooksDto(bookService.findAll());
    }

    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.PUT)
    public Map<String, BookDto> updateBook(
        @PathVariable final Long bookId,
        @RequestBody final Map<String, BookDto> bookDtoMap) {

        BookDto bookDto = bookDtoMap.get("book");
        Book book = bookService.updateBook(bookId, bookDto.getTitle(), bookDto.getAuthor(), bookDto.getPrice());

        Map<String, BookDto> result = new HashMap<>();
        result.put("book", bookConverter.convertModelToDto(book));

        return result;
    }


    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public Map<String, BookDto> createBook(
           @RequestBody final Map<String, BookDto> bookDtoMap) {
        BookDto bookDto = bookDtoMap.get("book");
        Book book = bookService.createBook(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getPrice());

        Map<String, BookDto> result = new HashMap<>();
        result.put("book", bookConverter.convertModelToDto(book));

        return result;
    }

    @RequestMapping(value="/books/{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBook(@PathVariable final Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
