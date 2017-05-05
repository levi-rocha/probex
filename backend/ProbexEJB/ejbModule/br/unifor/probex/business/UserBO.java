package br.unifor.probex.business;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.unifor.probex.dao.UserDAO;
import br.unifor.probex.dto.UserPermissionsDTO;
import br.unifor.probex.entity.User;

@Stateless
public class UserBO implements UserBORemote {

	@EJB
	private UserDAO userDAO;

	public UserBO() {

	}

	@Override
	public Collection<UserPermissionsDTO> listUsers() {
		return this.userDAO.list();
	}

	@Override
	public User findUserById(Long id) {
		return this.userDAO.findById(id);
	}

	@Override
	public User validateUserPassword(String username, String password) {
		return this.userDAO.findByUsernameAndPassword(username, password);
	}

	@Override
	public String addUser(User user) {
		return this.userDAO.insert(user);
	}

	@Override
	public String removeUser(Long id) {
		return this.userDAO.remove(id);
	}

	@Override
	public String updateUser(User user) {
		return this.userDAO.update(user);
	}
}