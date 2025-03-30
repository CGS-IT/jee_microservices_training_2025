package at.cgsit.kurs.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;


/**
 *  ExampleResource from the quarkus quickstart guide
 */
@Path("/hello")
public class HelloResource {
  private static final Logger LOG = Logger.getLogger(HelloResource.class);


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
