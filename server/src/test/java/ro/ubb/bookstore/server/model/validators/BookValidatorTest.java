package ro.ubb.bookstore.server.model.validators;

import ro.ubb.bookstore.server.model.Book;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Calendar;

import static org.junit.Assert.*;

public class BookValidatorTest {
    BookValidator bookValidator;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        bookValidator = new BookValidator();
    }
    @Test
    public void validateEmptyAuthor() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("Author cannot be null");
        bookValidator.validate(new Book(1, "", "Models", 2010, 69));
    }

    @Test
    public void validateAuthor() {
        bookValidator.validate(new Book(1, "Mark Manson", "Models", 2010, 69));
    }

    @Test
    public void validateEmptyTitle() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("Title cannot be null");
        bookValidator.validate(new Book(1, "Mark Models", "", 2010, 69));
    }

    @Test
    public void validateTitle()  {
        bookValidator.validate(new Book(1, "Mark Manson", "Models", 2010, 69));
    }

    @Test
    public void validateYear() {
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);

        bookValidator.validate(new Book(1, "Mark Manson", "Models", currentYear, 69));
    }

    @Test
    public void validateFutureYear() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("Year cannot be in the future");
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);

        bookValidator.validate(new Book(1, "Mark Manson", "Models", currentYear + 1, 69));
    }

    @Test
    public void validateNegativeCnt() throws BookException {
        thrown.expect(BookException.class);
        thrown.expectMessage("Cannot have negative book count");

        bookValidator.validate(new Book(1, "Mark Manson", "Models", 2010, -1));
    }

    @Test
    public void validateCnt() {
        bookValidator.validate(new Book(1, "Mark Manson", "Models", 2010, 1));
    }

}
