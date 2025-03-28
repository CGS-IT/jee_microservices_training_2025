package at.cgsit.kurs.exceptionmapper;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        ValidationErrorResponse response = new ValidationErrorResponse(
            "Validation failed", "ConstraintViolationException"
        );

        exception.getConstraintViolations().forEach(violation -> {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            response.addError(field, message);
        });

        return Response.status(422)
                       .type(MediaType.APPLICATION_JSON)
                       .entity(response)
                       .build();
    }
}
