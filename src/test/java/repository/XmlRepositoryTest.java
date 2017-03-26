package repository;

import model.Book;
import model.validators.BookValidator;
import org.apache.commons.collections.IterableMap;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

/**
 * Created by cosmin on 26/03/2017.
 */
public class XmlRepositoryTest {
    private XmlRepository<Integer, Book> repo;

    @Before
    public void setUp() {
        repo = new XmlRepository<Integer, Book>(new BookValidator(), "./data/test/test_books_2.xml");
    }

    @Test
    public void init() throws Exception {
        List<Book> books = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
        assertEquals(books.size(), 2);
        Book book1 = books.get(0);
        Book book2 = books.get(1);
        if(book1.getID() > book2.getID()) {
            Book aux = book1;
            book1 = book2;
            book2 = book1;
        }
        assertEquals(book1.getID(), new Integer(1));
        assertEquals(book1.getTitle(), "models");
        assertEquals(book1.getAuthor(), "mark");
        assertEquals(book1.getYear(), new Integer(2010));
        assertEquals(book1.getCnt(), new Integer(100));

        assertEquals(book2.getID(), new Integer(2));
        assertEquals(book2.getTitle(), "the game");
        assertEquals(book2.getAuthor(), "neil");
        assertEquals(book2.getYear(), new Integer(2011));
        assertEquals(book2.getCnt(), new Integer(2));
    }

    @Test
    public void save() throws Exception {
        repo.save(new Book(1, "book1", "author1", 2017, 5));
        List<Book> books = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
        assertEquals(books.size(), 2);
        Book book1 = books.get(0);
        Book book2 = books.get(1);
        if(book1.getID() > book2.getID()) {
            Book aux = book1;
            book1 = book2;
            book2 = book1;
        }
        assertEquals(book1.getID(), new Integer(1));
        assertEquals(book1.getTitle(), "models");
        assertEquals(book1.getAuthor(), "mark");
        assertEquals(book1.getYear(), new Integer(2010));
        assertEquals(book1.getCnt(), new Integer(100));

        assertEquals(book2.getID(), new Integer(2));
        assertEquals(book2.getTitle(), "the game");
        assertEquals(book2.getAuthor(), "neil");
        assertEquals(book2.getYear(), new Integer(2011));
        assertEquals(book2.getCnt(), new Integer(2));
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

}