package at.cgsit.kurs.resource;


import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Optional;

/**
 * ExampleResource from the quarkus quickstart guide
 */
@Path("/configProperty")
public class ConfigPropertyResource {
  private static final Logger LOG = Logger.getLogger(ConfigPropertyResource.class);

  @ConfigProperty(name = "greeting.message", defaultValue = "default-value")
  String message;

  @GET
  // @Path("/")
  @Produces(MediaType.TEXT_PLAIN)
  public String showMessage() {
    LOG.infov("INFO  :: showMessage {0}", message);
    return "Hello: " + message;
  }

  @GET
  @Path("/fromProvider")
  @Produces(MediaType.TEXT_PLAIN)
  public String showMessageFromConfigurationProperty() {
    LOG.infov("INFO  :: showMessage {0}", message);

    // String configParamRead = ConfigProvider.getConfig().getValue("greeting.message", String.class);
    Optional<String> configParamRead = ConfigProvider.getConfig().getOptionalValue("greeting.message", String.class);
    return "Hello: config read via Config Provider " + configParamRead;
  }

}