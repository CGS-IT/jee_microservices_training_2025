package at.cgsit.train.security;



import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

import static at.cgsit.train.security.definitons.MySecurityRoles.*;

@Path("/api/users")
public class UserResource {

    @Inject
    SecurityIdentity identity;

    @GET
    @RolesAllowed({USER_ROLE, "admin_role", "blocked"})
    @Path("/me")
    public String me(@Context SecurityContext securityContext) {

        // für datenbezogene zusätzliche Berechtigungen
        if (securityContext.isUserInRole("blocked")) {
            throw new ForbiddenException("Access denied for role: blocked");
        }

        identity.getRoles().forEach(role -> {
            System.out.println("Role: " + role);
        });


        System.out.println ("me called with user " + securityContext.getUserPrincipal().getName());
        return securityContext.getUserPrincipal().getName();
    }
}

