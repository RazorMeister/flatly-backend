package pw2021.backend.Flatly.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends Exception {
    public ForbiddenException(String msg) {
        super(msg);
    }
    public ForbiddenException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
