package service;

import model.Book;
import repository.Repository;

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
