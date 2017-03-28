package ro.ubb.bookstore.server.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    Book book;
    @Before
    public void setUp() {
        book = new Book(1, "Mark Manson", "Models", 2010, 69);
    }

    @Test
    public void getTitle() {
        assertEquals(book.getTitle(), "Models");
    }

    @Test
    public void getAuthor() {
        assertEquals(book.getAuthor(), "Mark Manson");
    }

    @Test
    public void getYear() {
        assertEquals(book.getYear(), new Integer(2010));
    }

    @Test
    public void getCnt() {
        assertEquals(book.getCnt(), new Integer(69));
    }

    @Test
    public void setTitle() {
        book.setTitle("Models v2");
        assertEquals(book.getTitle(), "Models v2");
    }

    @Test
    public void setAuthor() {
        book.setAuthor("Manson Mark");
        assertEquals(book.getAuthor(), "Manson Mark");
    }

    @Test
    public void setYear() {
        book.setYear(2009);
        assertEquals(book.getYear(), new Integer(2009));
    }

    @Test
    public void setCnt() {
        book.setCnt(100);
        assertEquals(book.getCnt(), new Integer(100));
    }

    @Test
    public void getID() {
        assertEquals(book.getID(), new Integer(1));
    }

    @Test
    public void setID() {
        book.setID(2);
        assertEquals(book.getID(), new Integer(2));
    }
}

