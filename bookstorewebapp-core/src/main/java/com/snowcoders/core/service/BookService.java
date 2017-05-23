package com.snowcoders.core.service;

import com.snowcoders.core.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book createBook(String title, String author, Double price);
    Book findOne(Long id);
    Book updateBook(Long id, String title, String author, Double price);
    void deleteBook(Long id);
}
