package ro.ubb.bookstore.server.service;

import ro.ubb.bookstore.server.model.Book;
import ro.ubb.bookstore.server.repository.Repository;

/**
 * BookService
 *      - class provides a bridge between the repository and the interface
 *      - has the main CRUD operations on BookRepository
 */
public class BookService extends CRUDService<Integer, Book> {
    public BookService(Repository<Integer, Book> bookRepository) {
        super(bookRepository);
    }
}
