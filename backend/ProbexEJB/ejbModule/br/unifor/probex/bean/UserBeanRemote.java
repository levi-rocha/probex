package br.unifor.probex.bean;

import java.util.Collection;

import javax.ejb.Remote;

import br.unifor.probex.entity.User;

@Remote
public interface UserBeanRemote {

	public Collection<User> getUsers();

	public String postUser(User user);

	public String deleteUser(Long id);

	public String putUser(User user);

}
