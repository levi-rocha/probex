package br.unifor.probex.bean;

import java.util.Collection;

import javax.ejb.EJB;

import br.unifor.probex.dao.UserDAO;
import br.unifor.probex.entity.User;

public class UserBean implements UserBeanRemote {

	@EJB
	private UserDAO userDAO;

	public UserBean() {
	}

	@Override
	public Collection<User> getUsers() {
		return this.userDAO.getAll();
	}

	@Override
	public String postUser(User user) {
		return this.userDAO.insert(user);
	}

	@Override
	public String deleteUser(Long id) {
		return this.userDAO.remove(id);
	}

	@Override
	public String putUser(User user) {
		return this.userDAO.update(user);
	}

}
