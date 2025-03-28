package at.cgsit.kurs.resource;

import at.cgsit.kurs.dto.ChildDto;
import at.cgsit.kurs.model.ChildEntity;
import at.cgsit.kurs.model.TestEntity;
import at.cgsit.kurs.repository.TestEntityRepository;
import at.cgsit.kurs.service.EnhancedTestService;
import at.cgsit.kurs.service.TestService;
import at.cgsit.kurs.translator.TestEntityTranslator;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import at.cgsit.kurs.dto.TestDTO;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.logging.Logger;

import java.net.URI;
import java.util.List;

@Path("test")
public class TestResource {
  private static final Logger LOG = Logger.getLogger(TestResource.class);

  @Inject
  EnhancedTestService service;
  // TestService service;

  // TODO move all business logic to service layer
  // direct usage of repository is not recommended here anymore
  @Inject
  TestEntityRepository testEntityRepo;

  @Inject
  TestEntityTranslator translator;

  /**
   * Get all TestDTO objects or filter by name and return a page of results
   * now using aservice layer to fetch data and do business logic
   * @param name name to filter by as query parameter
   * @param page page number
   * @param size page size
   * @return
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(
      summary = "Get all TestDTOs or filter by name",
      description = "Returns a paginated list of TestDTO objects, optionally filtered by name. Uses a service layer for business logic."
  )
  @APIResponses({
      @APIResponse(
          responseCode = "200",
          description = "Page of TestDTO results",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = TestDTO.class)
          )
      ),
      @APIResponse(
          responseCode = "400",
          description = "Invalid request parameters"
      )
  })
  public List<TestDTO> getAllOrByNamePaged(
      @Parameter(description = "Filter by name", example = "Chris")
      @QueryParam("name") String name,

      @Parameter(description = "Page number", example = "0")
      @QueryParam("page") @DefaultValue("0") int page,

      @Parameter(description = "Page size", example = "10")
      @QueryParam("size") @DefaultValue("10") int size)
  {
    LOG.infov("Fetching page {0} with size {1}, filtered by name: {2}", page, size, name);

    // call the business logic layer which also makes the data DTO translation
    return service.findByNamePaged(name, page, size);

  }

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
  @Counted(name = "readObjectById", description = "How many primality checks have been performed.")
  @Timed( name = "readObjectById_timed", description = "A measure of how long it takes to perform the primality test.",
      unit = MetricUnits.MILLISECONDS)
  public TestDTO readObjectById( @PathParam("id") Integer id) {

    LOG.infov("input {0} , objectOutput {1}", id, "");

    TestEntity testEntity = testEntityRepo.readTestEntityById(id);

    return translator.toDTO(testEntity);
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createTestEntity (
      @RequestBody(
          description = "Create a TestDTO",
          required = true,
          content = @Content(
              mediaType = "application/json",
              examples = {
                  @ExampleObject(
                      name = "minimal",
                      summary = "Minimal input with only 'name'",
                      value = "{ \"name\": \"Frank\" }"
                  ),
                  @ExampleObject(
                      name = "full",
                      summary = "Full input with all fields",
                      value = "{\n" +
                          "  \"id\": 1,\n" +
                          "  \"versionNumber\": 1,\n" +
                          "  \"name\": \"Tee\",\n" +
                          "  \"vorname\": \"chris\",\n" +
                          "  \"isOk\": true,\n" +
                          "  \"eventDate\": \"2025-03-26\"\n" +
                          "}"
                  )
              }
          )
      )
      @Valid TestDTO dto
  ) {
    LOG.infov("Creating new TestEntity with name: {0}", dto.getName());

      TestDTO testDTO = service.create(dto);// 🛡️ call the service layer to create the entity

      // Return 201 Created response with DTO
      return Response.status(Response.Status.CREATED)
          .entity( testDTO )
          .build();
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

      return Response
          .ok(translator.toDTO(existingEntity))
          .build();
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

  @POST
  @Path("/{parentId}/child")
  @Transactional
  public Response addChildToParent(
      @PathParam("parentId") Integer parentId,
      ChildDto childInput) {

    TestEntity parent = testEntityRepo.findById(parentId.longValue());
    if (parent == null) {
      return Response.status(Response.Status.NOT_FOUND)
          .entity("Parent TestEntity with ID " + parentId + " not found.")
          .build();
    }

    ChildEntity child = new ChildEntity(childInput.getChildName(), parent);
    parent.addChild(child);

    testEntityRepo.updateTestEntity(parent);

    return Response.created(URI.create("/test/" + parentId + "/child/" + childInput.getId()))
        .entity("Child added successfully.")
        .build();
  }

}