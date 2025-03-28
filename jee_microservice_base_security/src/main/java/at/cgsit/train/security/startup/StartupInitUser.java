package at.cgsit.train.security.startup;

import at.cgsit.train.security.jpa.User;
import io.quarkus.runtime.StartupEvent;
import jakarta.annotation.Priority;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;



@Singleton
public class StartupInitUser {

    @Inject
    Logger log;

    @Transactional
    public void loadUsers(@Observes @Priority(1) StartupEvent evt) {
        log.info("StartupInitUser initialization called ");

        // reset and load all test users
        User.deleteAll();
        User.add("admin", "admin", "admin_role");
        User.add("user", "user", "user_role");
    }
}
