package at.cgsit.jeemicro.cdi.req_scope_producer;

import org.jboss.logging.Logger;

public class ReqScopedPBImplB implements ReqScopedPBInterface {

    private static Logger log = Logger.getLogger(ReqScopedPBImplB.class);

    @Override
    public String echo(String input) {
        log.info("ReqScopedPBImplB Implementierung B");
        return input + " -- from PBImplB";
    }

}
