package pw2021.backend.Flatly.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends Exception {
    public UnauthorizedException(String msg) {
        super(msg);
    }
    public UnauthorizedException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
