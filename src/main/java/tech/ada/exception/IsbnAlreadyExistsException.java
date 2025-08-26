package tech.ada.exception;

public class IsbnAlreadyExistsException extends RuntimeException {
    public IsbnAlreadyExistsException(String message) {
        super(message);
    }
}
