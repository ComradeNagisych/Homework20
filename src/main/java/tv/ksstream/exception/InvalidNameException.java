package tv.ksstream.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidNameException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Employee's name is invalid";
    public InvalidNameException() {
        super(DEFAULT_MESSAGE);
    }
    public InvalidNameException(String message) {
        super(message);
    }
}