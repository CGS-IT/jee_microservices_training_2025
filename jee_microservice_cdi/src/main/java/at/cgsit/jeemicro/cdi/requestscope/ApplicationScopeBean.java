package at.cgsit.jeemicro.cdi.requestscope;

import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ApplicationScopeBean {

    public Integer counter = 0;

    public Integer getCounter() {
        this.counter++;
        return counter;
    }
}
