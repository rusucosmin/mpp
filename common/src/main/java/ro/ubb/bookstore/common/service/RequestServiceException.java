package ro.ubb.bookstore.common.service;

public class RequestServiceException extends RuntimeException {
    public RequestServiceException(Throwable cause) {
        super(cause);
    }

    public RequestServiceException(String message) {
        super(message);
    }
}
