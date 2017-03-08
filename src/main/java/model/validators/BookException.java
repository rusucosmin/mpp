package model.validators;

public class BookException extends ValidatorException {
    public BookException(Throwable cause) {
        super(cause);
    }

    public BookException(String message) {
        super(message);
    }

    public BookException(String message, Throwable cause) {
        super(message, cause);
    }
}
