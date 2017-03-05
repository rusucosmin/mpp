package model.validators;

import model.Book;
import org.apache.commons.lang3.StringUtils;
import java.util.Calendar;

public class BookValidator implements Validator<Book> {
    public void validate(Book entity) throws ValidatorException {
        this.validateAuthor(entity);
        this.validateTitle(entity);
        this.validateYear(entity);
        this.validateCnt(entity);
    }

    private void validateAuthor(Book entity) throws ValidatorException {
        if(StringUtils.isEmpty(entity.getAuthor()))
            throw new ValidatorException("Author cannot be null");
    }

    private void validateTitle(Book entity) throws ValidatorException {
        if(StringUtils.isEmpty(entity.getTitle()))
            throw new ValidatorException("Title cannot be null");
    }

    private void validateYear(Book entity) throws ValidatorException {
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);

        if(entity.getYear() > currentYear)
            throw new ValidatorException("Year cannot be in the future");
    }

    private void validateCnt(Book entity) throws ValidatorException {
        if(entity.getCnt() < 0)
            throw new ValidatorException("Cannot have negative book count");
    }
}
