package at.cgsit.jeemicro.cdi.interfacebean;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class InterfaceBeanImpl implements InterfaceBean {

    @Override
    public String echo(String input) {
        return "InterfaceBeanImpl: " + input;
    }
}
