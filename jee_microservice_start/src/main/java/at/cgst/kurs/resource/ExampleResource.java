package at.cgst.kurs.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Path("/hello3")
public class ExampleResource {
  private static final Logger LOG = Logger.getLogger(ExampleResource.class);


  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    LOG.infov("input {0} , objectOutput {1}",  1, "");

    return "HALLO2";
  }
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/neu")
  public String helloNeu() {
    return "HALLO NEU";
  }


}
