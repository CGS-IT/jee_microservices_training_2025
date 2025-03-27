package at.cgsit.jeemicro.cdi.events;

public class SpecialEvent {

    private String message;

    public SpecialEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
