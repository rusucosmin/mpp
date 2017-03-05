package service;

import model.Book;
import repository.Repository;

public class BookService {
    // TODO
    private Repository<Integer, Book> bookRepository;
    public BookService(Repository<Integer, Book> bookRepository) {
        this.bookRepository = bookRepository;
    }
}
