package br.unifor.probex.business;

import java.util.Collection;

import javax.ejb.Remote;

import br.unifor.probex.dto.UserPermissionsDTO;
import br.unifor.probex.entity.User;

@Remote
public interface UserBORemote {

	public Collection<UserPermissionsDTO> listUsers();

	public User findUserById(Long id);

	public User validateUserPassword(String username, String password);

	public String addUser(User user);

	public String removeUser(Long id);

	public String updateUser(User user);
}
