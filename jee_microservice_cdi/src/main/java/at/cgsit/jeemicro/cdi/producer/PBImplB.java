package at.cgsit.jeemicro.cdi.producer;

import org.jboss.logging.Logger;

public class PBImplB implements PBInterface {

    private static Logger log = Logger.getLogger(PBImplB.class);

    @Override
    public String echo(String input) {
        log.info("PBImplB Implementierung B");
        return input + " -- from PBImplB";
    }

}
