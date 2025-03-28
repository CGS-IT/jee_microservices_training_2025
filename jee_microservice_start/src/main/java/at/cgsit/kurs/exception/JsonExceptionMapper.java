package at.cgsit.kurs.exception;

import at.cgsit.kurs.dto.ErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Provider
public class JsonExceptionMapper implements ExceptionMapper<InvalidFormatException> {

    private static final Logger LOG = Logger.getLogger(JsonExceptionMapper.class);

    @Override
    public Response toResponse(InvalidFormatException ex) {
        LOG.errorv("Jackson format error: {0}", ex.getMessage(), ex);

        ErrorResponse error = new ErrorResponse(
            "Invalid JSON format: " + ex.getOriginalMessage(),
            "InvalidFormatException"
        );

        return Response.status(422) // Unprocessable Entity
                       .type(MediaType.APPLICATION_JSON)
                       .entity(error)
                       .build();
    }
}
