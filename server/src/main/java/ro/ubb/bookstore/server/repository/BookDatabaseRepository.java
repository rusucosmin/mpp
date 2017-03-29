package ro.ubb.bookstore.server.repository;

import ro.ubb.bookstore.common.model.Book;
import ro.ubb.bookstore.common.model.validators.Validator;
import ro.ubb.bookstore.common.model.validators.ValidatorException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DatabaseRepository implementation for the Repository
 */
public class BookDatabaseRepository extends DatabaseConnection implements Repository<Integer, Book> {
    Validator<Book> validator;

    public BookDatabaseRepository(Validator validator, String DB_URL, String USER, String PASS) {
        super(DB_URL, USER, PASS);
        this.validator = validator;
    }

    /**
     * Method finds a specific entity based on an ID
     * @param id
     *            must be not null.
     * @return
     */
    public Optional<Book> findOne(Integer id) {
        String query = "SELECT id, title, author, year, cnt FROM books WHERE id='" + id.toString() + "';";
        ResultSet rs = executeQuery(query);
        Book ret = null;

        try {
            while(rs.next()) {
                int bookID = rs.getInt("id");
                String author = rs.getString("author");
                String title = rs.getString("title");
                int cnt = rs.getInt("cnt");
                int year = rs.getInt("year");

                ret = new Book(bookID, author, title, year, cnt);
                break;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(ret);
    }


    /**
     * Method returns all the enitites from the current repository
     * @return
     */
    public Iterable<Book> findAll() {
        String query = "SELECT id, title, author, year, cnt FROM books;";
        ResultSet rs = executeQuery(query);

        List<Book> ret = new ArrayList<Book>();

        try {
            while(rs.next()) {
                int bookID = rs.getInt("id");
                String author = rs.getString("author");
                String title = rs.getString("title");
                int cnt = rs.getInt("cnt");
                int year = rs.getInt("year");

                ret.add(new Book(bookID, author, title, year, cnt));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return ret;

    }

    /**
     * Method creates a new record
     * @param b
     *            must not be null.
     * @return
     * @throws ValidatorException
     */
    public Optional<Book> save(Book b) throws ValidatorException {
        if(b == null)
            throw new IllegalArgumentException("book is null, too bad");

        validator.validate(b);

        String query = "INSERT INTO books (id, title, author, year, cnt) " +
            String.format("VALUES (%d, '%s', '%s', %d, %d);", b.getID(), b.getTitle(), b.getAuthor(), b.getYear(), b.getCnt());

        Optional<Book> optBook = findOne(b.getID());
        if(optBook.isPresent()) ///the book is already present
            return optBook;

        executeUpdate(query);

        return Optional.empty();
    }

    /**
     * Method deletes a record
     * @param id
     *            must not be null.
     * @return
     */
    public Optional<Book> delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }

        Optional<Book> ret = findOne(id);

        if(!ret.isPresent())
            return null;

        String query = "DELETE FROM books WHERE id='" + id.toString() + "';";
        executeUpdate(query);

        return ret;
    }

    /**
     * Method updates a record
     * @return
     * @throws ValidatorException
     */
    public Optional<Book> update(Book b) throws ValidatorException {
        if(b == null)
            throw new IllegalArgumentException("book is null, too bad");

        validator.validate(b);

        Optional<Book> ret = findOne(b.getID());

        if(!ret.isPresent())
            return Optional.ofNullable(b);

        String query = String.format("UPDATE books SET id='%d', title='%s', author='%s', year='%d', cnt='%d' WHERE id='%d';", b.getID(), b.getTitle(), b.getAuthor(), b.getYear(), b.getCnt(), b.getID());

        executeUpdate(query);

        return Optional.empty();
    }
}
