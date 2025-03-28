package at.cgsit.jeemicro.application.startup;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.runtime.Startup;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@Startup
@ApplicationScoped
public class MyStartupBean {

    @Inject
    Logger log;

    @PostConstruct
    void onStartup() {
        log.info(" StartupBean initialized at app startup");
        // further starup logic
    }

    @PreDestroy
    void onShutdown() {
        log.info(" StartupBean shutdown at app shutdown");
        // further shutdown logic
    }

}



