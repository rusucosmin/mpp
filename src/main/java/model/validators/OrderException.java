package model.validators;

/**
 * Created by cosmin on 23/03/2017.
 */
public class OrderException extends ValidatorException {
    public OrderException(String message) {
        super(message);
    }

    public OrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderException(Throwable cause) {
        super(cause);
    }
}
