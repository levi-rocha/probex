package br.unifor.probex.business;

import br.unifor.probex.dao.ReportDAO;
import br.unifor.probex.entity.Report;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ReportBO implements ReportBORemote{

    @EJB
    private ReportDAO reportDAO;

    public ReportBO() {

    }

    @Override
    public List<Report> listReports() {
        return reportDAO.list(0);
    }

    @Override
    public List<Report> listReports(int quantity) {
        return reportDAO.list(quantity);
    }

    @Override
    public String addReport(Report report) {
        return reportDAO.insert(report);
    }

    @Override
    public Report findById(Long id) {
        return reportDAO.findById(id);
    }

    @Override
    public String removeReport(Long id) {
        return reportDAO.remove(id);
    }

    @Override
    public String updateReport(Report report) {
        return reportDAO.update(report);
    }
}
