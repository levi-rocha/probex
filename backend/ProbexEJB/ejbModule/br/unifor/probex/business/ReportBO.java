package br.unifor.probex.business;

import br.unifor.probex.dao.ReportDAO;
import br.unifor.probex.dto.ReportDTO;
import br.unifor.probex.entity.Report;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.NotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ReportBO implements ReportBORemote{

    @EJB
    private ReportDAO reportDAO;

    public ReportBO() {

    }

    @Override
    public List<ReportDTO> listReports(int quantity) {
        List<ReportDTO> dtos = new ArrayList<>();
        for (Report r : reportDAO.list(quantity)) {
            dtos.add(ReportDTO.fromReport(r));
        }
        return dtos;
    }

    @Override
    public ReportDTO addReport(Report report) throws DatabaseException {
        return ReportDTO.fromReport(reportDAO.insert(report));
    }

    @Override
    public ReportDTO findById(Long id) throws NotFoundException {
        return ReportDTO.fromReport(reportDAO.findById(id));
    }

    @Override
    public ReportDTO removeReport(Long id) throws DatabaseException,
            NotFoundException {
        return ReportDTO.fromReport(reportDAO.remove(id));
    }

    @Override
    public ReportDTO updateReport(Report report) throws NotFoundException,
            DatabaseException {
        return ReportDTO.fromReport(reportDAO.update(report));
    }
}
