package br.unifor.probex.dao;

import br.unifor.probex.entity.Report;
import br.unifor.probex.entity.User;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.NotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless
public class ReportDAO {

    @PersistenceContext
    private EntityManager manager;

    public Report insert(Report report) throws DatabaseException,
            NotFoundException {
        try {
            report.setAuthor(manager.createQuery("SELECT u FROM User u " +
                    "WHERE u.username = :username", User.class)
                    .setParameter("username", report.getAuthor()
                            .getUsername()).getSingleResult());
            manager.persist(report);
            manager.flush();
            return report;
        } catch (NoResultException e) {
            throw new NotFoundException("Author not found");
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
        try {
            return manager.createQuery("SELECT r from Report r " +
                    "LEFT JOIN FETCH r.author LEFT JOIN FETCH r.post p " +
                    "LEFT JOIN FETCH p.votes WHERE r.id = :id", Report.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundException("Report not found");
        }
    }

    public Report remove(Long id) throws DatabaseException, NotFoundException {
        try {
            Report report = manager.find(Report.class, id);
            manager.remove(report);
            return report;
        } catch (NoResultException e) {
            throw new NotFoundException("Report not found");
        } catch (PersistenceException e) {
            throw new DatabaseException("Could not remove report");
        }
    }

    public Report update(Report report) throws NotFoundException,
            DatabaseException {
        try {
            Report detached = manager.find(Report.class, report.getId());
            Report managed = manager.merge(detached);
            managed.setDescription(report.getDescription());
            return managed;
        } catch (NoResultException e) {
            throw new NotFoundException("Report not found");
        } catch (PersistenceException e) {
            throw new DatabaseException("Could not update report");
        }
    }
}
