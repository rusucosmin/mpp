package model.validators;

/**
 * Created by cosmin on 3/5/17.
 */
public class BookStoreException extends RuntimeException {
    public BookStoreException(String message) {
        super(message);
    }
    public BookStoreException(String message, Throwable cause) {
        super(message, cause);
    }
    public BookStoreException(Throwable cause) {
        super(cause);
    }
}
