package br.unifor.probex.dao;

import br.unifor.probex.entity.Report;
import br.unifor.probex.entity.User;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.NotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless
public class ReportDAO {

    @PersistenceContext
    private EntityManager manager;

    public Report insert(Report report) throws DatabaseException {
        try {
            report.setAuthor(manager.createQuery("SELECT u FROM User u " +
                    "WHERE u.username = :username", User.class)
                    .setParameter("username", report.getAuthor()
                            .getUsername()).getSingleResult());
            manager.persist(report);
            manager.flush();
            return report;
        } catch (PersistenceException e) {
            throw new DatabaseException("Could not insert report");
        }
    }

    public List<Report> list(int quantity) {
        if (quantity <= 0 || quantity > 100)
            quantity = 100;
        return manager.createQuery("SELECT r from Report r LEFT JOIN " +
                "FETCH r.author LEFT JOIN FETCH r.post p LEFT JOIN FETCH " +
                "p.votes", Report.class)
                .setMaxResults(quantity).getResultList();
    }

    public Report findById(Long id) throws NotFoundException {
        Report report = manager.createQuery("SELECT r from Report r " +
                "LEFT JOIN FETCH r.author LEFT JOIN FETCH r.post p " +
                "LEFT JOIN FETCH p.votes WHERE r.id = :id", Report.class)
                .setParameter("id", id).getSingleResult();
        if (report == null)
            throw new NotFoundException(
                    "No report found with id " + id);
        return report;
    }

    public Report remove(Long id) throws DatabaseException, NotFoundException {
        try {
            Report report = manager.find(Report.class, id);
            if (report == null)
                throw new NotFoundException(
                        "No report found with id " + id);
            manager.remove(report);
            return report;
        } catch (PersistenceException e) {
            throw new DatabaseException("Could not remove report");
        }
    }

    public Report update(Report report) throws NotFoundException,
            DatabaseException {
        try {
            Report detached = manager.find(Report.class, report.getId());
            if (detached == null)
                throw new NotFoundException(
                        "No report found with id " + report.getId());
            Report managed = manager.merge(detached);
            managed.setDescription(report.getDescription());
            return managed;
        } catch (PersistenceException e) {
            throw new DatabaseException("Could not update report");
        }
    }
}
