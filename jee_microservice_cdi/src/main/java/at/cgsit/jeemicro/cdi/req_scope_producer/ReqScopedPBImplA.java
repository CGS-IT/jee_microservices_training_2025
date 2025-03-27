package at.cgsit.jeemicro.cdi.req_scope_producer;

import org.jboss.logging.Logger;

public class ReqScopedPBImplA implements ReqScopedPBInterface {

    private static Logger log = Logger.getLogger(ReqScopedPBImplA.class);

    @Override
    public String echo(String input) {
        log.info("ReqScopedPBImplA Implementierung A");
        return input + " -- from PBImplA";
    }

}
