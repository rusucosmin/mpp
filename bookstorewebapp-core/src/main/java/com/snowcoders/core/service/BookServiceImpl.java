package com.snowcoders.core.service;

import com.snowcoders.core.model.Book;
import com.snowcoders.core.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    protected BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(String title, String author, Double price) {
        Book book = new Book(title, author, price);//, new HashSet<Order>(0));
        book = bookRepository.save(book);
        return book;
    }

    @Override
    public Book findOne(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    @Transactional
    public Book updateBook(Long id, String title, String author, Double price) {
        Book book = bookRepository.getOne(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);
        return book;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.delete(id);
    }
}
