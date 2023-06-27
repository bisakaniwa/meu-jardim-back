package br.com.meujardim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlantaNotFoundException extends RuntimeException {

    public PlantaNotFoundException(String message) {
        super (message);
    }
}
