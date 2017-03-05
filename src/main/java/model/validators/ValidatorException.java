package model.validators;

/**
 * Created by cosmin on 3/5/17.
 */
public class ValidatorException extends BookStoreException {
    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
