package spring_group1.com.exception;

public class DuplicateName extends RuntimeException {
    public DuplicateName(String message) {
        super(message);
    }
}
