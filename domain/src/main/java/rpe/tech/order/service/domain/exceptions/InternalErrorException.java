package rpe.tech.order.service.domain.exceptions;

import java.io.Serial;

public class InternalErrorException extends NoStacktraceException {

    @Serial
    private static final long serialVersionUID = 1647119141558671041L;

    protected InternalErrorException(final String aMessage, final Throwable t) {
        super(aMessage, t);
    }

    public static InternalErrorException with(final String message, final Throwable t) {
        return new InternalErrorException(message, t);
    }
}