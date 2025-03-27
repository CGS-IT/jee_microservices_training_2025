package at.cgsit.jeemicro.cdi.buildprofile;

import org.jboss.logging.Logger;

public class TracerImplTwo implements MyTracer {
    private static Logger log = Logger.getLogger(MyTracer.class);

    public String echo(String input) {
        log.info("TracerImplTwo Implementierung");
        return input + " -- from TracerImplTwo";
    }
}
