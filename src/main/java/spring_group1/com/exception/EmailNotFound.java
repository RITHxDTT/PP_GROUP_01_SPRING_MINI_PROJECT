package spring_group1.com.exception;

public class EmailNotFound extends RuntimeException {
    public EmailNotFound(String message) {
        super(message);
    }
}
