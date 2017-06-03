package br.unifor.probex.business;

import br.unifor.probex.entity.Report;
import com.sun.org.apache.regexp.internal.RE;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ReportBORemote {

    public List<Report> listReports();

    public List<Report> listReports(int quantity);

    public String addReport(Report report);

    public Report findById(Long id);

    public String removeReport(Long id);

    public String updateReport(Report report);

}
