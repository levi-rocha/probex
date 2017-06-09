package br.unifor.probex.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import br.unifor.probex.entity.User;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.NotFoundException;

@Stateless
public class UserDAO {

	@PersistenceContext
	private EntityManager manager;

	public User insert(User user) {
			manager.persist(user);
			manager.flush();
			return user;
	}

	public List<User> list(int quantity, int start) {
		if (start < 0)
			start = 0;
		if (quantity <= 0 || quantity > 100)
			quantity = 100;
        return manager.createQuery("SELECT a FROM User a " +
                "LEFT JOIN FETCH a.permission", User.class)
                .setFirstResult(start).setMaxResults(quantity)
                .getResultList();
	}

	public User findByUsernameAndPassword(String username, String password)
            throws NotFoundException {
		Query query = manager.createQuery(
				"SELECT a FROM User a LEFT JOIN FETCH a.permission " +
						"LEFT JOIN FETCH a.posts p LEFT JOIN FETCH " +
						"a.comments LEFT JOIN FETCH p.votes WHERE a.username " +
						"= :username AND a.password = :password", User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		try {
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundException("User not found");
        }
	}

	public User findById(Long id) throws NotFoundException {
	    try {
            return manager.createQuery(
                    "SELECT a FROM User a LEFT JOIN FETCH a.permission " +
                            "LEFT JOIN FETCH a.posts p LEFT JOIN FETCH " +
                            "a.comments LEFT JOIN FETCH p.votes WHERE a.id = :id",
                    User.class).setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundException("User not found");
        }
	}

	public User findByUsername(String username) throws NotFoundException {
        try {
            return manager.createQuery(
                    "SELECT a FROM User a LEFT JOIN FETCH a.permission " +
                            "LEFT JOIN FETCH a.posts p LEFT JOIN FETCH " +
                            "a.comments LEFT JOIN FETCH p.votes " +
                            "WHERE a.username = :username",
                    User.class).setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundException("User not found");
        }
	}

	public User remove(Long id) throws DatabaseException, NotFoundException {
		try {
			User user = manager.find(User.class, id);
			manager.remove(user);
			return user;
		} catch (NoResultException e) {
            throw new NotFoundException("User not found");
        } catch (PersistenceException e) {
			throw new DatabaseException("Could not remove user with id " + id);
		}
	}

	public User update(User user) throws NotFoundException {
        try {
            User detached = manager.find(User.class, user.getId());
            User managed = manager.merge(detached);
            managed.setUsername(user.getUsername());
            managed.setEmail(user.getEmail());
            managed.setPassword(user.getPassword());
            managed.setPermission(user.getPermission());
            return managed;
        } catch (NoResultException e) {
            throw new NotFoundException("User not found");
        }

	}
}