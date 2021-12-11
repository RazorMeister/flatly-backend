package pw2021.backend.Flatly.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {
    public BadRequestException(String msg) { super(msg); }
    public BadRequestException(Throwable cause) { super(cause.getMessage(), cause); }
}
