package fr.esgi.timebomb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameEmptyException extends Throwable {
    public GameEmptyException(String message) {
        super(message);
    }
}
