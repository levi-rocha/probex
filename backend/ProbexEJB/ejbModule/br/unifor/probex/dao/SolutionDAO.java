package br.unifor.probex.dao;

import br.unifor.probex.entity.Solution;
import br.unifor.probex.entity.User;

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
        if (quantity > 0) {
            return manager.createQuery("SELECT s FROM Solution s ORDER BY s" +
                    ".date ASC", Solution.class).setMaxResults(quantity)
                    .getResultList();
        } else {
            return manager.createQuery("SELECT s FROM Solution s ORDER BY s" +
                    ".date ASC", Solution.class).getResultList();
        }
    }

    public Solution findById(Long id) {
        Solution solution = manager
                .createQuery("select s from Solution s left join fetch s" +
                                ".author left join fetch s.post where s.id = " +
                                ":id",
                        Solution.class)
                .setParameter("id", id).getSingleResult();
        return solution;
    }

    public String insert(Solution solution) {
        try {
            solution.setAuthor((User) manager.createQuery("SELECT u FROM User u WHERE u.username = :username")
                    .setParameter("username", solution.getAuthor().getUsername())
                    .getSingleResult());
            manager.persist(solution);
            return solution.getContent() + " inserted";
        } catch (PersistenceException e) {
            return "could not insert data " + e;
        }
    }

    public String update(Solution solution) {
        try {
            Solution detached = manager.find(Solution.class, solution.getId());

            if (detached == null)
                return "no solution found with id " + solution.getId();

            Solution managed = manager.merge(detached);

            managed.setContent(solution.getContent());

            return solution.getContent() + " updated";
        } catch (PersistenceException e) {
            return "could not update data " + e;
        }
    }

    public String remove(Long id) {
        try {
            Solution solution = manager.find(Solution.class, id);
            manager.remove(solution);
            return solution.getContent() + " removed";
        } catch (PersistenceException e) {
            return "could not remove data " + e;
        }
    }

}
