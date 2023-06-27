package br.com.meujardim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class OperacaoRestritaException extends RuntimeException {

    public OperacaoRestritaException(String message) {
        super (message);
    }
}
