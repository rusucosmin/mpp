package ro.ubb.c04remotingjdbctemplate.common;

import java.util.List;

/**
 * @author radu.
 */
public interface BookService {
    List<Book> getAllBooks();
    void addBook(Book b);
    void deleteBook(Integer id);
    void updateBook(Book b);
}
