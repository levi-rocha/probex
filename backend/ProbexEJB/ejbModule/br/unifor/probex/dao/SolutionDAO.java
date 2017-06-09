package br.unifor.probex.dao;

import br.unifor.probex.entity.Solution;
import br.unifor.probex.entity.User;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.NotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless
public class SolutionDAO {

    @PersistenceContext
    private EntityManager manager;

    public List<Solution> list(int quantity) {
        if (quantity <= 0 || quantity > 100)
            quantity = 100;
        return manager.createQuery("SELECT s FROM Solution s " +
                "ORDER BY s.date ASC", Solution.class)
                .setMaxResults(quantity).getResultList();
    }

    public Solution findById(Long id) throws NotFoundException {
        Solution solution = manager
                .createQuery("select s from Solution s left join fetch s" +
                                ".author left join fetch s.post where s.id = " +
                                ":id", Solution.class)
                .setParameter("id", id).getSingleResult();
        if (solution == null)
            throw new NotFoundException(
                    "No solution found with id " + id);
        return solution;
    }

    public Solution insert(Solution solution) throws DatabaseException {
        try {
            solution.setAuthor((User) manager.createQuery("SELECT u FROM " +
                    "User u WHERE u.username = :username")
                    .setParameter("username", solution.getAuthor()
                            .getUsername()).getSingleResult());
            manager.persist(solution);
            manager.flush();
            return solution;
        } catch (PersistenceException e) {
            throw new DatabaseException("Could not insert solution");
        }
    }

    public Solution update(Solution solution) throws NotFoundException,
            DatabaseException {
        try {
            Solution detached = manager.find(Solution.class, solution.getId());
            if (detached == null)
                throw new NotFoundException(
                        "No solution found with id " + solution.getId());
            Solution managed = manager.merge(detached);
            managed.setContent(solution.getContent());
            return managed;
        } catch (PersistenceException e) {
            throw new DatabaseException("Could not update solution");
        }
    }

    public Solution remove(Long id) throws DatabaseException,
            NotFoundException {
        try {
            Solution solution = manager.find(Solution.class, id);
            if (solution == null)
                throw new NotFoundException(
                        "No solution found with id " + id);
            manager.remove(solution);
            return solution;
        } catch (PersistenceException e) {
            throw new DatabaseException("Could not remove solution");
        }
    }

}
