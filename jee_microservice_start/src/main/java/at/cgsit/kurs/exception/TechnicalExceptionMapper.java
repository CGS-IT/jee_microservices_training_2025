package at.cgsit.kurs.exception;

import at.cgsit.kurs.dto.ErrorResponse;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

// @Provider
public class TechnicalExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(TechnicalExceptionMapper.class);

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof NotFoundException) {
            // Let NotFoundExceptionMapper handle it
            return Response.status(Response.Status.NOT_FOUND).build(); // fallback, just in case
        }
        LOG.error("Unhandled exception caught by TechnicalExceptionMapper", exception);

        ErrorResponse response = new ErrorResponse(
                "A technical error occurred. Please contact support.",
                exception.getClass().getSimpleName()
        );

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                       .type(MediaType.APPLICATION_JSON)
                       .entity(response)
                       .build();
    }
}
