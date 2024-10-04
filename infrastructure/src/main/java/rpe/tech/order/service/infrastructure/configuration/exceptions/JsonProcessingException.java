package rpe.tech.order.service.infrastructure.configuration.exceptions;

import java.io.Serial;

public class JsonProcessingException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6035910345403191072L;

    public JsonProcessingException(final String message) {
        this(message, null);
    }

    public JsonProcessingException(final String message, final Throwable cause) {
        super(message, cause, true, false);
    }
}
