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
        Report report = reportBO.findById(id);
        return ReportDTO.fromReport(report);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReportDTO> listReports(@QueryParam("q") int quantity) {
        List<ReportDTO> dtos = new ArrayList<ReportDTO>();
        List<Report> data = reportBO.listReports(quantity);
        for (Report report : data) {
            dtos.add(ReportDTO.fromReport(report));
        }
        return dtos;
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
