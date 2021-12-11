package pw2021.backend.Flatly.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {
    public NotFoundException(String msg) {
        super(msg);
    }
    public NotFoundException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
