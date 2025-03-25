package at.cgsit.kurs.appbean;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {
  public String greet(String name) {
    return "Hello, " + name + "!";
  }
}
