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
			return user.getUsername() + " inserted succesfully";
		} catch (PersistenceException e) {
			return "could not insert data";
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<User> getAll() {
		return manager.createQuery("select a from User a").getResultList();
	}

	public String remove(Long id) {
		try {
			User user = manager.find(User.class, id);
			manager.remove(user);
			return user.getUsername() + " removed succesfully";
		} catch (PersistenceException e) {
			return "could not remove data";
		}
	}

	public String update(User user) {
		try {
			User detached = manager.find(User.class, user.getId());
			User merged = manager.merge(detached);
			merged.setEmail(user.getEmail());
			merged.setUsername(user.getUsername());
			merged.setPassword(user.getPassword());
			return user.getUsername() + " updated succesfully";
		} catch (PersistenceException e) {
			return "could not update data";
		}
	}

}
