package br.unifor.probex.restful.resources;

import br.unifor.probex.business.ReportBORemote;
import br.unifor.probex.dto.ReportDTO;
import br.unifor.probex.entity.Report;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("/reports")
public class ReportResource {

    @EJB
    private ReportBORemote reportBO;

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ReportDTO findReportById(@PathParam("id") Long id) {
        return reportBO.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReportDTO> listReports(@QueryParam("q") int quantity) {
        return reportBO.listReports(quantity);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addReport(Report report) {
        return reportBO.addReport(report);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateReport(Report report) {
        return reportBO.updateReport(report);
    }

    @Path("{id}")
    @DELETE
    @Produces("text/plain")
    public String removeReport(@PathParam("id") Long id) {
        return reportBO.removeReport(id);
    }


}
