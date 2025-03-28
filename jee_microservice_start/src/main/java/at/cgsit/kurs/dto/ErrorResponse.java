package at.cgsit.kurs.dto;

public class ErrorResponse {
    public String message;
    public String error;
    public String timestamp;

    public ErrorResponse(String message, String error) {
        this.message = message;
        this.error = error;
        this.timestamp = java.time.ZonedDateTime.now().toString();
    }
}
