package ro.ubb.bookstore.common.service;

import ro.ubb.bookstore.common.model.Book;

/**
 * Created by cosmin on 29/03/2017.
 */
public interface IBookService extends ICRUDService<Integer, Book>  {
    final static String CREATE = "createBook";
    final static String READ = "readBook";
    final static String READ_ALL = "readAllBooks";
    final static String UPDATE = "updateBook";
    final static String DELETE = "deleteBook";
}
