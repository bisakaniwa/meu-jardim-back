package br.com.meujardim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UniqueUseException extends RuntimeException {
    public UniqueUseException(String message) {
        super(message);
    }
}
