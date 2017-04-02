package ro.ubb.c04remotingjdbctemplate.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.c04remotingjdbctemplate.common.Book;
import ro.ubb.c04remotingjdbctemplate.common.BookService;

import java.util.List;

/**
 * @author radu.
 */
public class BookServiceClient {

    @Autowired
    private BookService bookService;

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }


}
