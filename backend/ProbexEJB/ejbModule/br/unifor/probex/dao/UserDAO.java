package br.unifor.probex.dao;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.unifor.probex.entity.User;

@Stateless
public class UserDAO {

	@PersistenceContext
	private EntityManager manager;

	public String insert(User user) {

		try {
			manager.persist(user);

			return user.getUsername() + " inserted";
		} catch (PersistenceException e) {
			return "could not insert data " + e;
		}

	}

	public Collection<User> list() {

		return manager.createQuery("SELECT a FROM User a").getResultList();
	}

	public User findByUsernameAndPassword(String username, String password) {
		Query query = manager
				.createQuery("SELECT a FROM User a WHERE a.username = :username AND a.password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		try {
			return (User) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public User findById(Long id) {
		return manager.find(User.class, id);
	}

	public String remove(Long id) {

		try {
			User user = manager.find(User.class, id);
			manager.remove(user);
			return user.getUsername() + " removed";
		} catch (PersistenceException e) {
			return "could not remove data " + e;
		}
	}

	public String update(User user) {

		try {
			User detached = manager.find(User.class, user.getId());

			if (detached == null)
				return "no user found with id " + user.getId();

			User managed = manager.merge(detached);

			managed.setUsername(user.getUsername());
			managed.setEmail(user.getEmail());
			managed.setPassword(user.getPassword());

			return user.getUsername() + " updated";
		} catch (PersistenceException e) {
			return "could not update data " + e;
		}
	}
}