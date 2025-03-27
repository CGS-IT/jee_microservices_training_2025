package at.cgsit.jeemicro.cdi.buildprofile;

import org.jboss.logging.Logger;

public class TracerImpl implements MyTracer {
    private static Logger log = Logger.getLogger(MyTracer.class);

    public String echo(String input) {
        log.info("Tracer Implementierung");
        return input + " -- from Tracer";
    }
}
