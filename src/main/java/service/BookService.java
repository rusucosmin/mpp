package service;

import model.Book;
import repository.Repository;

/**
 * Created by cosmin on 3/5/17.
 */
public class BookService {
    // TODO
    private Repository<Integer, Book> bookRepository;
    public BookService(Repository<Integer, Book> bookRepository) {
        this.bookRepository = bookRepository;
    }
}
