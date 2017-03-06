package service;

import model.Book;
import repository.Repository;

public class BookService extends CRUDService<Integer, Book> {
    // TODO
    public BookService(Repository<Integer, Book> bookRepository) {
        super(bookRepository);
    }
}
