package ro.ubb.bookstore.server.service;

import ro.ubb.bookstore.common.model.Book;
import ro.ubb.bookstore.common.service.IBookService;
import ro.ubb.bookstore.server.repository.Repository;

import java.util.concurrent.ExecutorService;

/**
 * BookService
 *      - class provides a bridge between the repository and the interface
 *      - has the main CRUD operations on BookRepository
 */
public class BookService extends CRUDService<Integer, Book> implements IBookService {
    public BookService(Repository<Integer, Book> bookRepository, ExecutorService executor) {
        super(bookRepository, executor);
    }
}
