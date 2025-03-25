package at.cgsit.kurs.resource;

import at.cgsit.kurs.model.TestEntity;
import at.cgsit.kurs.repository.TestEntityRepository;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import at.cgsit.kurs.dto.TestDTO;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.logging.Logger;

import java.util.List;

@Path("test")
public class TestResource {
  private static final Logger LOG = Logger.getLogger(TestResource.class);

  @Inject
  TestEntityRepository testEntityRepo;

  @GET
  @Path("/count")
  @Produces(MediaType.TEXT_PLAIN)
  public String countTestResources() {
    Long l = testEntityRepo.countTestEntities();
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
  public TestDTO readObjectById( @PathParam("id") Integer id) {

    LOG.infov("input {0} , objectOutput {1}", id, "");

    TestEntity testEntity = testEntityRepo.readTestEntityById(id);

    TestDTO dto = new TestDTO();
    dto.setId(testEntity.getId().longValue());
    dto.setVersionNumber(testEntity.getVersionNo());
    dto.setName(testEntity.getName());
    dto.setVorname("");
    return dto;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createTestEntity (
      @Parameter(description = "TestEntity to create", required = true)
      @Valid TestDTO dto
  ) {
    LOG.infov("Creating new TestEntity with name: {0}", dto.getName());

    try {
      // Create and populate TestEntity from DTO values
      TestEntity newEntity = new TestEntity();
      newEntity.setName(dto.getName());

      // insert. newEntity will be updated with ID
      testEntityRepo.insertTestEntity(newEntity);
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

  @PUT
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateTestEntity(
      @Parameter(description = "ID of the TestEntity to update", required = true)
      @PathParam("id") Long id,
      @Parameter(description = "Updated TestEntity data", required = true)
      TestDTO dto
  ) {
    LOG.infov("Updating TestEntity with ID: {0}", id);

    if (dto == null || dto.getName() == null || dto.getName().isEmpty()) {
      LOG.warn("Invalid input in request body");
      return Response.status(Response.Status.BAD_REQUEST)
          .entity("Name field cannot be empty")
          .build();
    }

    try {
      // Fetch existing entity
      TestEntity existingEntity = testEntityRepo.findById(id);
      if (existingEntity == null) {
        LOG.warnv("TestEntity with ID {0} not found", id);
        return Response.status(Response.Status.NOT_FOUND)
            .entity("Entity not found")
            .build();
      }

      // Update fields
      existingEntity.setName(dto.getName());
      // Optional: update other fields like vorname
      // existingEntity.setVorname(dto.getVorname());

      // Persist the changes
      testEntityRepo.updateTestEntity(existingEntity);

      // Build response DTO
      TestDTO responseDto = new TestDTO();
      responseDto.setId(existingEntity.getId().longValue());
      responseDto.setVersionNumber(existingEntity.getVersionNo());
      responseDto.setName(existingEntity.getName());
      responseDto.setVorname(""); // Fill as needed

      return Response.ok(responseDto).build();

    } catch (Exception e) {
      LOG.error("Error updating TestEntity", e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Error updating entity")
          .build();
    }
  }


  // http://localhost:8080/parameter/queryParameter?qp=inputText&qp2=text2
  @GET
  @Path("/find")
  @Produces(MediaType.TEXT_PLAIN)
  public String queryParameter(
      @QueryParam("name") String name,
      @QueryParam("exact") Boolean exact
  ){
    LOG.infov("log QueryParam: {0} exact {1}", name, exact);

    // simple query and or enhanced query with like OR exact match
    // List<TestEntity> byName = this.testEntityRepo.findByName(name);
    List<TestEntity> byName = this.testEntityRepo.findByName(name, exact);

    return "Anzahl TestEntities: " + byName.size();
  }

  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteTestEntity(
      @Parameter(description = "ID of the TestEntity to delete", required = true)
      @PathParam("id") Long id
  ) {
    LOG.infov("Deleting TestEntity with ID: {0}", id);

    // may also delete with testEntityRepo.deleteById(id); // faster
    try {
      TestEntity entity = testEntityRepo.findById(id);
      if (entity == null) {
        LOG.warnv("TestEntity with ID {0} not found", id);
        return Response.status(Response.Status.NOT_FOUND)
            .entity("Entity not found")
            .build();
      }

      testEntityRepo.deleteTestEntity(entity);

      return Response.noContent().build(); // 204 No Content

    } catch (Exception e) {
      LOG.error("Error deleting TestEntity", e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("Error deleting entity")
          .build();
    }
  }


}