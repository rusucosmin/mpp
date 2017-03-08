package repository;

import model.BaseEntity;
import model.Book;
import model.validators.BookValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cosmin on 3/8/17.
 */
public class InMemoryRepositoryTest {
    Repository<Integer, Book> inMemoryRepository;
    Book book1, book2, book3, book4;

    @Before
    public void setUp() {
        inMemoryRepository = new InMemoryRepository<>(new BookValidator());

        book1 = new Book(1, "Mark Manson", "Models", 2010, 69);
        book2 = new Book(2, "The Game", "Neil Strauss", 2008, 100);
        book3 = new Book(3, "The Truth", "Neil Strauss", 2016, 2);

        inMemoryRepository.save(book1);
        inMemoryRepository.save(book2);
        inMemoryRepository.save(book3);
    }

    @Test
    public void findOne() throws Exception {
        assertEquals(inMemoryRepository.findOne(1).get(), book1);
        assertEquals(inMemoryRepository.findOne(2).get(), book2);
        assertEquals(inMemoryRepository.findOne(3).get(), book3);
    }

    @Test
    public void findAll() throws Exception {
        ArrayList<Book> all = new ArrayList<>();
        for(Book b : inMemoryRepository.findAll())
            all.add(b);
        all.sort(Comparator.comparingInt(b1->b1.getID()));
        assertEquals(all.size(), 3);
        assertEquals(all.get(0), book1);
        assertEquals(all.get(1), book2);
        assertEquals(all.get(2), book3);
    }

    @Test
    public void save() throws Exception {
        book4 = new Book(4, "Test", "test", 2016, 15);
        inMemoryRepository.save(book4);

        ArrayList<Book> all = new ArrayList<>();
        for(Book b : inMemoryRepository.findAll())
            all.add(b);
        all.sort(Comparator.comparingInt(b1->b1.getID()));

        assertEquals(all.size(), 4);
        assertEquals(all.get(0), book1);
        assertEquals(all.get(1), book2);
        assertEquals(all.get(2), book3);
        assertEquals(all.get(3), book4);
    }

    @Test
    public void delete() throws Exception {
        inMemoryRepository.delete(4);
    }

    @Test
    public void update() throws Exception {
        Book current = new Book(3, "The Truth", "New Neil Strauss", 2016, 50);
        inMemoryRepository.update(current);
        assertEquals(inMemoryRepository.findOne(3).get(), current);
    }

}
