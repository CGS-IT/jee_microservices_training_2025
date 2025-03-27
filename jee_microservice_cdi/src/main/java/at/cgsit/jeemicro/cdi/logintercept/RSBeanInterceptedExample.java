package at.cgsit.jeemicro.cdi.logintercept;

import jakarta.enterprise.context.RequestScoped;

import java.util.Locale;

@RequestScoped
@Logged
public class RSBeanInterceptedExample {

    public String echoReverse(String input) {
        StringBuilder inputSB = new StringBuilder();
        StringBuilder reverse = inputSB.append(input).reverse();
        return reverse.toString().toUpperCase(Locale.ROOT);
    }

    public String echoReverse2(String input) {
        String reverse = echoReverse(input);
        return reverse;
    }

}
