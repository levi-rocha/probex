package br.unifor.probex.dao;

import br.unifor.probex.entity.Solution;
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
        try {
            return manager.createQuery("select s from Solution s " +
                    "left join fetch s.author left join fetch s.post " +
                    "where s.id = :id", Solution.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundException("Solution not found");
        }

    }

    public Solution insert(Solution solution) throws DatabaseException,
            NotFoundException {
        try {
            solution.setAuthor((User) manager.createQuery("SELECT u FROM " +
                    "User u WHERE u.username = :username")
                    .setParameter("username", solution.getAuthor()
                            .getUsername()).getSingleResult());
            manager.persist(solution);
            manager.flush();
            return solution;
        } catch (NoResultException e) {
            throw new NotFoundException("Author not found");
        } catch (PersistenceException e) {
            throw new DatabaseException("Could not insert solution");
        }
    }

    public Solution update(Solution solution) throws NotFoundException,
            DatabaseException {
        try {
            Solution detached = manager.find(Solution.class, solution.getId());
            Solution managed = manager.merge(detached);
            managed.setContent(solution.getContent());
            return managed;
        } catch (NoResultException e) {
            throw new NotFoundException("Solution not found");
        } catch (PersistenceException e) {
            throw new DatabaseException("Could not update solution");
        }
    }

    public Solution remove(Long id) throws DatabaseException,
            NotFoundException {
        try {
            Solution solution = manager.find(Solution.class, id);
            manager.remove(solution);
            return solution;
        } catch (NoResultException e) {
            throw new NotFoundException("Solution not found");
        } catch (PersistenceException e) {
            throw new DatabaseException("Could not remove solution");
        }
    }

}
