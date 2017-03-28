package ro.ubb.bookstore.server.repository;

import ro.ubb.bookstore.server.model.Book;
import ro.ubb.bookstore.server.model.validators.BookValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Created by cosmin on 26/03/2017.
 */
public class XmlRepositoryTestAll {
    Repository<Integer, Book> repo;
    Book book1, book2, book3, book4;

    @Before
    public void setUp() {
        repo = new XmlRepository<>(new BookValidator(), "./data/test/test_books.xml");

        book1 = new Book(1, "Mark Manson", "Models", 2010, 69);
        book2 = new Book(2, "The Game", "Neil Strauss", 2008, 100);
        book3 = new Book(3, "The Truth", "Neil Strauss", 2016, 2);

//        repo.save(book1);
//        repo.save(book2);
//        repo.save(book3);
    }

    @Test
    public void findOne() throws Exception {
        assertEquals(repo.findOne(1).get(), book1);
        assertEquals(repo.findOne(2).get(), book2);
        assertEquals(repo.findOne(3).get(), book3);
    }

    @Test
    public void findAll() throws Exception {
        ArrayList<Book> all = new ArrayList<>();
        for(Book b : repo.findAll())
            all.add(b);
        all.sort(Comparator.comparingInt(b1->b1.getID()));
        assertEquals(all.size(), 3);
        assertEquals(all.get(0), book1);
        assertEquals(all.get(1), book2);
        assertEquals(all.get(2), book3);
    }

    @Test
    public void saveThenDelete() throws Exception {
        book4 = new Book(4, "Test", "test", 2016, 15);
        repo.save(book4);

        ArrayList<Book> all = new ArrayList<>();
        for(Book b : repo.findAll())
            all.add(b);
        all.sort(Comparator.comparingInt(b1->b1.getID()));

        assertEquals(all.size(), 4);
        assertEquals(all.get(0), book1);
        assertEquals(all.get(1), book2);
        assertEquals(all.get(2), book3);
        assertEquals(all.get(3), book4);

        repo.delete(4);

        all.clear();
        for(Book b : repo.findAll())
            all.add(b);
        all.sort(Comparator.comparingInt(b1->b1.getID()));
        assertEquals(all.size(), 3);
        assertEquals(all.get(0), book1);
        assertEquals(all.get(1), book2);
        assertEquals(all.get(2), book3);

    }

    @Test
    public void update() throws Exception {
        Book current = new Book(3, "The Truth", "New Neil Strauss", 2016, 50);
        repo.update(current);
        assertEquals(repo.findOne(3).get(), current);
        repo.update(book3);
        assertEquals(repo.findOne(3).get(), book3);
    }
}