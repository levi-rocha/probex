package br.unifor.probex.restful.resources;

import br.unifor.probex.business.SolutionBORemote;
import br.unifor.probex.dto.SolutionDTO;
import br.unifor.probex.entity.Solution;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("/solutions")
public class SolutionResource {

    @EJB
    private SolutionBORemote solutionBO;

    @Path("{id}")
    @GET
    @Produces("application/json")
    public SolutionDTO findSolutionById(@PathParam("id") Long id) {
        return SolutionDTO.fromSolution(solutionBO.findSolutionById(id));
    }

    @GET
    @Produces("application/json")
    public List<SolutionDTO> listSolutions(@QueryParam("q") int quantity) {
        return solutionBO.listSolutions(quantity);
    }

    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    public SolutionDTO addSolution(Solution solution) {
        return solutionBO.addSolution(solution);
    }

    @PUT
    @Consumes("application/json")
    @Produces("text/plain")
    public SolutionDTO updateSolution(Solution solution) {
        return solutionBO.updateSolution(solution);
    }

    @Path("{id}")
    @DELETE
    @Produces("text/plain")
    public SolutionDTO removeSolution(@PathParam("id") Long id) {
        return solutionBO.removeSolution(id);
    }

}
