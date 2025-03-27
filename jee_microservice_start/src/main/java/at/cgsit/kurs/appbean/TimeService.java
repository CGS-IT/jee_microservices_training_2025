package at.cgsit.kurs.appbean;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalTime;

@ApplicationScoped
public class TimeService {

    public String getCurrentTime() {
        return LocalTime.now().toString();
    }
}
