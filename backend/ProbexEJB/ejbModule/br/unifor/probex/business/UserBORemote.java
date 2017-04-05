package br.unifor.probex.business;

import java.util.Collection;

import javax.ejb.Remote;

import br.unifor.probex.entity.User;

@Remote
public interface UserBORemote {

	public Collection<User> listUsers();
	
	public User findUserById(Long id);

	public String addUser(User user);

	public String removeUser(Long id);

	public String updateUser(User user);
}
