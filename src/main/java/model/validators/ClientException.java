package model.validators;

public class ClientException extends ValidatorException {
    public ClientException(Throwable cause) {
        super(cause);
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
