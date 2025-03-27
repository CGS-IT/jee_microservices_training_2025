package at.cgsit.jeemicro.cdi.logintercept;

import jakarta.enterprise.context.RequestScoped;

import java.util.Locale;
@Logged
@RequestScoped
public class RSBeanInterceptedExample {

    public String echoReverseSub(String input) {
        StringBuilder inputSB = new StringBuilder();
        StringBuilder reverse = inputSB.append(input).reverse();
        return reverse.toString().toUpperCase(Locale.ROOT);
    }

    public String echoReverseEntry(String input) {
        String reverse = echoReverseSub(input);
            // if(true)
            //    throw  new RuntimeException("echoReverseEntry exception");

        return reverse;
    }

}
