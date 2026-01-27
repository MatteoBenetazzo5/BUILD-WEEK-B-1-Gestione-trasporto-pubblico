package team3.exceptions;

import java.util.UUID;

public class NotFoundIdException extends RuntimeException {
    public NotFoundIdException(UUID id) {
        super("Il record con id " + id + " non è stato trovato!");
    }
}