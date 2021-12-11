package pw2021.backend.Flatly.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends Exception {
    public UnprocessableEntityException(String msg) {
        super(msg);
    }
    public UnprocessableEntityException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
