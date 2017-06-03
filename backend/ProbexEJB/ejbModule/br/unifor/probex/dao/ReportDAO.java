package br.unifor.probex.dao;

import br.unifor.probex.entity.Report;
import br.unifor.probex.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless
public class ReportDAO {

    @PersistenceContext
    private EntityManager manager;

    public String insert(Report report) {
        try {
            report.setAuthor(manager.createQuery("SELECT u FROM User u WHERE" +
                    " u.username = :username", User.class)
                    .setParameter("username", report.getAuthor().getUsername())
                    .getSingleResult());
            manager.persist(report);
            return "report inserted";
        } catch (PersistenceException e) {
            return "could not insert report " + e;
        }
    }

    public List<Report> list(int quantity) {
        if (quantity > 0) {
            return manager.createQuery("SELECT r from Report r LEFT JOIN " +
                    "FETCH r.author LEFT JOIN FETCH r.post p LEFT JOIN FETCH " +
                            "p.votes", Report.class)
                    .setMaxResults(quantity).getResultList();
        } else {
            return manager.createQuery("SELECT r from Report r LEFT JOIN " +
                    "FETCH r.author LEFT JOIN FETCH r.post p LEFT JOIN FETCH " +
                    "p.votes", Report.class)
                    .getResultList();
        }
    }

    public Report findById(Long id) {
        Report report = manager.createQuery("SELECT r from Report r LEFT JOIN " +
                "FETCH r.author LEFT JOIN FETCH r.post p LEFT JOIN FETCH p" +
                ".votes WHERE r.id = :id", Report.class)
                .setParameter("id", id).getSingleResult();
        return report;
    }

    public String remove(Long id) {
        try {
            Report report = manager.find(Report.class, id);
            manager.remove(report);
            return "report " + report.getId() + " removed";
        } catch (PersistenceException e) {
            return "could not remove report! " + e;
        }
    }

    public String update(Report report) {
        try {
            Report detached = manager.find(Report.class, report.getId());

            if (detached == null)
                return "no rerport found with id " + report.getId();

            Report managed = manager.merge(detached);

            managed.setDescription(report.getDescription());

            return "report " + report.getId() + " updated";
        } catch (PersistenceException e) {
            return "could not update report! " + e;
        }
    }
}
