package br.unifor.probex.dao;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

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