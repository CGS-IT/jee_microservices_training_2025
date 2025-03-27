package at.cgsit.jeemicro.cdi.buildprofile;

import org.jboss.logging.Logger;

public class TracerImplTwo implements Tracer {
    private static Logger log = Logger.getLogger(Tracer.class);

    public String echo(String input) {
        log.info("TracerImplTwo Implementierung");
        return input + " -- from TracerImplTwo";
    }
}
