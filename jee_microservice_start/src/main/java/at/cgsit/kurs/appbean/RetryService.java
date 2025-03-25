package at.cgsit.kurs.appbean;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RetryService {
    private static int attempt = 0;

    public boolean tryOperationWithRetry() {
        attempt++;
        return attempt >= 2;
    }

    public void reset() {
        attempt = 0;
    }

}
