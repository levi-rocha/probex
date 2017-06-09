package br.unifor.probex.restful.resources;

import br.unifor.probex.business.SolutionBORemote;
import br.unifor.probex.dto.SolutionDTO;
import br.unifor.probex.entity.Solution;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.InvalidArgumentException;
import br.unifor.probex.exception.NotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/solutions")
public class SolutionResource {

    @EJB
    private SolutionBORemote solutionBO;

    @Path("{id}")
    @GET
    @Produces("application/json")
    public Response findSolutionById(@PathParam("id") Long id) {
        try {
            SolutionDTO data = solutionBO.findSolutionById(id);
            return Response.ok(data, MediaType.APPLICATION_JSON).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces("application/json")
    public Response listSolutions(@QueryParam("q") int quantity) {
            List<SolutionDTO> data = solutionBO.listSolutions(quantity);
            return Response.ok(data, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    public Response addSolution(Solution solution) {
        try {
            SolutionDTO data = solutionBO.addSolution(solution);
            return Response.ok(data, MediaType.APPLICATION_JSON).build();
        } catch (DatabaseException e) {
            return Response.serverError().entity(e.getMessage()).build();
        } catch (InvalidArgumentException e) {
            return Response.status(422).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes("application/json")
    @Produces("text/plain")
    public Response updateSolution(Solution solution) {
        try {
            SolutionDTO data = solutionBO.updateSolution(solution);
            return Response.ok(data, MediaType.APPLICATION_JSON).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        } catch (DatabaseException e) {
            return Response.serverError().entity(e.getMessage()).build();
        } catch (InvalidArgumentException e) {
            return Response.status(422).entity(e.getMessage()).build();
        }
    }

    @Path("{id}")
    @DELETE
    @Produces("text/plain")
    public Response removeSolution(@PathParam("id") Long id) {
        try {
            SolutionDTO data = solutionBO.removeSolution(id);
            return Response.ok(data, MediaType.APPLICATION_JSON).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        } catch (DatabaseException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
