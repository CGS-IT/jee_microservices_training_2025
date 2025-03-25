package at.cgsit.kurs.appbean;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AdvancedGreetingService {

    @Inject
    TimeService timeService;

    public String greet(String name) {
        return "Hello, " + name + "! Time is: " + timeService.getCurrentTime();
    }
}
