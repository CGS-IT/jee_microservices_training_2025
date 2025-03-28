package at.cgsit.kurs.exceptionmapper;

import at.cgsit.kurs.dto.ErrorResponse;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse extends ErrorResponse {
    public List<FieldError> errors = new ArrayList<>();

    public ValidationErrorResponse(String message, String error) {
        super(message, error);
    }

    public void addError(String field, String message) {
        errors.add(new FieldError(field, message));
    }

    public record FieldError(String field, String message) {}
}
