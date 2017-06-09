package br.unifor.probex.restful.resources;

import br.unifor.probex.business.ReportBORemote;
import br.unifor.probex.dto.ReportDTO;
import br.unifor.probex.entity.Report;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.NotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/reports")
public class ReportResource {

    @EJB
    private ReportBORemote reportBO;

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findReportById(@PathParam("id") Long id) {
        try {
            ReportDTO data = reportBO.findById(id);
            return Response.ok(data, MediaType.APPLICATION_JSON).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listReports(@QueryParam("q") int quantity) {
        List<ReportDTO> data = reportBO.listReports(quantity);
        return Response.ok(data, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addReport(Report report) {
        try {
            ReportDTO data = reportBO.addReport(report);
            return Response.ok(data, MediaType.APPLICATION_JSON).build();
        } catch (DatabaseException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateReport(Report report) {
        try {
            ReportDTO data = reportBO.updateReport(report);
            return Response.ok(data, MediaType.APPLICATION_JSON).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        } catch (DatabaseException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Path("{id}")
    @DELETE
    @Produces("text/plain")
    public Response removeReport(@PathParam("id") Long id) {
        try {
            ReportDTO data = reportBO.removeReport(id);
            return Response.ok(data, MediaType.APPLICATION_JSON).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        } catch (DatabaseException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }


}
