package br.unifor.probex.business;

import java.util.List;

import javax.ejb.Remote;

import br.unifor.probex.dto.UserSimpleDTO;
import br.unifor.probex.entity.User;

@Remote
public interface UserBORemote {

	public List<UserSimpleDTO> listUsers();

	public User findUserById(Long id);
	
	public User findUserByUsername(String username);

	public User validateUserPassword(String username, String password);

	public String addUser(User user);

	public String removeUser(Long id);

	public String updateUser(User user);
}
