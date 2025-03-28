package at.cgsit.kurs.exception;

import at.cgsit.kurs.dto.ErrorResponse;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Provider
public class JpaExceptionMapper implements ExceptionMapper<PersistenceException> {
    private static final Logger LOG = Logger.getLogger(JpaExceptionMapper.class);


    @Override
    public Response toResponse(PersistenceException ex) {
        LOG.error("Database exception caught", ex);

        ErrorResponse error = new ErrorResponse(
            "A database error occurred. Please contact support.",
            ex.getClass().getSimpleName()
        );

        return Response.status(Response.Status.BAD_REQUEST)
                       .type(MediaType.APPLICATION_JSON)
                       .entity(error)
                       .build();
    }
}
