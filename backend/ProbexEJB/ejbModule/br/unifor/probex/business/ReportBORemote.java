package br.unifor.probex.business;

import br.unifor.probex.dto.ReportDTO;
import br.unifor.probex.entity.Report;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.NotFoundException;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ReportBORemote {

    List<ReportDTO> listReports(int quantity);

    ReportDTO addReport(Report report) throws DatabaseException, NotFoundException;

    ReportDTO findById(Long id) throws NotFoundException;

    ReportDTO removeReport(Long id) throws DatabaseException, NotFoundException;

    ReportDTO updateReport(Report report) throws NotFoundException, DatabaseException;

}
