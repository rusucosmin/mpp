package ro.ubb.bookstore.common.service;

import ro.ubb.bookstore.common.model.Book;

/**
 * Created by cosmin on 29/03/2017.
 */
public interface IBookService extends ICRUDService<Integer, Book>  {
    String CREATE = "createBook";
    String READ = "readBook";
    String READ_ALL = "readAllBooks";
    String UPDATE = "updateBook";
    String DELETE = "deleteBook";
}

