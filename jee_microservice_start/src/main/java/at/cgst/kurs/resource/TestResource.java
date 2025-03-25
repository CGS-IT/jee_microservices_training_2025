package at.cgst.kurs.resource;

import at.cgst.kurs.model.TestEntity;
import at.cgst.kurs.repository.TestEntityRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import at.cgst.kurs.dto.TestDTO;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.logging.Logger;

@Path("test")
public class TestResource {
  private static final Logger LOG = Logger.getLogger(TestResource.class);

  @Inject
  TestEntityRepository testEntityRepository;

  @GET
  @Path("/count")
  @Produces(MediaType.TEXT_PLAIN)
  public String countTestResources() {
    Long l = testEntityRepository.countTestEntities();
    LOG.infov("Anzahl TestEntities: {0}", l);
    return "Anzahl TestEntities: " + l.toString();
  }

  // http://localhost:8080/test/1
  @Operation( summary = "read a Test DTO Object by ID",
      description = "read a Test DTO Object by ID and return it",
      operationId = "readTestDtoById")
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public TestDTO readObjectById( @PathParam("id") String id) {

    LOG.infov("input {0} , objectOutput {1}", id, "");

    int pId = 0;
    try {
      pId = Integer.parseInt(id);
    } catch (NumberFormatException e) {
      LOG.warnv("invalid input {}", id);
      throw e;
    }

    TestDTO dto = new TestDTO();
    dto.setId(Integer.toUnsignedLong(pId));
    dto.setName("resultName");
    dto.setVorname("resultName");
    dto.setEventDate( new java.util.Date());
    return dto;
  }

  // http://localhost:8080/parameter/queryParameter?qp=inputText&qp2=text2
  @GET
  @Path("/queryParameter")
  @Produces(MediaType.TEXT_PLAIN)
  public String queryParameter(
      @QueryParam("qp") String qp,
      @DefaultValue("1") @QueryParam("qp2") Long qP2
  ){
    LOG.infov("log QueryParam: {0}", qp);
    return "query params ["+ qp + "] und [" + qP2 + "]";
  }

  @POST
  @Path("/{name}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createTestEntity (
      @Parameter(description = "TestEntity to create", required = true)
      @Valid TestDTO dto
  ) {
    LOG.infov("Creating new TestEntity with name: {0}", dto.getName());

    // Validate input
    if (dto.getName() == null || dto.getName().isEmpty()) {
      LOG.warn("Invalid name parameter");
      return Response.status(Response.Status.BAD_REQUEST)
          .entity("Name parameter cannot be empty")
          .build();
    }

    try {
      // Create and populate TestEntity from DTO values
      TestEntity newEntity = new TestEntity();
      newEntity.setName(dto.getName());

      // insert. newEntity will be updated with ID
      testEntityRepository.insertTestEntity(newEntity);
      // Map entity to DTO for response
      TestDTO result = new TestDTO();
      result.setId(newEntity.getId().longValue()); // Assuming ID is generated after persist
      result.setName(newEntity.getName());
      result.setVorname("");

      // Return 201 Created response with DTO
      return Response.status(Response.Status.CREATED)
          .entity( result )
          .build();

    } catch (Exception e) {
      LOG.error("Error creating TestEntity", e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Error creating entity")
          .build();
    }
  }


}