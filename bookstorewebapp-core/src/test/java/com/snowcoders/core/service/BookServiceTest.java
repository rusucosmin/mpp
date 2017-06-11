package com.snowcoders.core.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.snowcoders.core.ITConfig;
import com.snowcoders.core.model.Book;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 * Created by cosmin on 05/06/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ITConfig.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF/dbtest/db-data.xml")
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    public void findAll() throws Exception {
        List<Book> books = bookService.findAll();
        assertEquals("There should be two books", 2, books.size());
        Book book1 = books.get(0);
        assertEquals("Book1 id should be 1", book1.getId(), 1l);
        assertEquals("Book1 author", book1.getAuthor(), "Mark Manson");
        assertEquals("Book1 title", book1.getTitle(), "Models");
        assertEquals("Book1 price", book1.getPrice(), 1.0);

        Book book2 = books.get(1);
        assertEquals("Book2 id should be 2", book2.getId(), 2l);
        assertEquals("Book2 author", book2.getAuthor(), "Neil Strauss");
        assertEquals("Book2 title", book2.getTitle(), "The Game");
        assertEquals("Book2 price", book2.getPrice(), 2.0);
    }

    @Ignore
    @Test
    public void createBook() {
        Book book = bookService.createBook("Bible", "God", 3.0);
        List<Book> books = bookService.findAll();
        assertEquals("There should be three books", 3, books.size());
        assertEquals("Book id should increment", book.getId(), 3l);
        assertEquals("Book Title", book.getTitle(), "Bible");
        assertEquals("Book Author", book.getAuthor(), "God");
        assertEquals("Book price", book.getPrice(), 3.0);
    }

    @Test
    public void deleteBook() {
        bookService.deleteBook(2l);
        List<Book> books = bookService.findAll();
        assertEquals("There should be one book", 1, books.size());
        Book book = books.get(0);
        assertEquals("Book id should be 1", book.getId(), 1l);
        assertEquals("Book author", book.getAuthor(), "Mark Manson");
        assertEquals("Book title", book.getTitle(), "Models");
        assertEquals("Book price", book.getPrice(), 1.0);
    }

    @Test
    public void updateBook() {
        Book book = bookService.updateBook(1l, "Models", "Manson Mark", 1.0);
        assertEquals("Book id should be 1", book.getId(), 1l);
        assertEquals("Book author", book.getAuthor(), "Manson Mark");
        assertEquals("Book title", book.getTitle(), "Models");
        assertEquals("Book price", book.getPrice(), 1.0);
    }
}