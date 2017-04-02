package ro.ubb.bookstore.common.model.validators;

import ro.ubb.bookstore.common.model.Book;
import org.apache.commons.lang3.StringUtils;
import java.util.Calendar;

/**
 * BookValidator - validates a specific Book
 */
public class BookValidator implements Validator<Book> {
    public void validate(Book entity) throws BookException {
        this.validateAuthor(entity);
        this.validateTitle(entity);
        this.validateYear(entity);
        this.validateCnt(entity);
    }

    private void validateAuthor(Book entity) throws BookException {
        if(StringUtils.isEmpty(entity.getAuthor()))
            throw new BookException("Author cannot be null");
    }

    private void validateTitle(Book entity) throws BookException {
        if(StringUtils.isEmpty(entity.getTitle()))
            throw new BookException("Title cannot be null");
    }

    private void validateYear(Book entity) throws BookException {
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);

        if(currentYear < 0 || entity.getYear() > currentYear)
            throw new BookException("Year cannot be in the future");
    }

    private void validateCnt(Book entity) throws BookException {
        if(entity.getCnt() < 0)
            throw new BookException("Cannot have negative book count");
    }
}
