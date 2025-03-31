package at.cgsit.kurs.resource;

import at.cgsit.kurs.model.ErrorEntity;
import at.cgsit.kurs.repository.ErrorEntityRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/error-entity")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ErrorEntityResource {

    @Inject
    ErrorEntityRepository repository;

    @POST
    @Transactional
    public Response create(ErrorEntity entity) {
        repository.save(entity);
        return Response.status(Response.Status.CREATED).build();
    }
}
