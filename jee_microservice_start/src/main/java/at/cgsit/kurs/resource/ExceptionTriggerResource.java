package at.cgsit.kurs.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;

@Path("/test/error")
public class ExceptionTriggerResource {

    @GET
    @Path("/runtime")
    public String throwRuntime() {
        throw new RuntimeException("Simulated runtime exception");
    }

    @GET
    @Path("/notfound")
    public String throwNotFound() {
        throw new NotFoundException("Simulated 404");
    }
}
