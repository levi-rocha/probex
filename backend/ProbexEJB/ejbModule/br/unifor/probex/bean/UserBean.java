package br.unifor.probex.bean;

import java.util.ArrayList;
import java.util.Collection;

import br.unifor.probex.entity.User;

public class UserBean implements UserBeanRemote {

	private static ArrayList<User> userList = new ArrayList<User>();

	static {
		User u1 = new User();
		u1.setId((long) userList.size());
		u1.setUsername("user");
		u1.setPassword("user");
		u1.setEmail("user@user.com");
		userList.add(u1);
	}

	public UserBean() {
	}

	@Override
	public Collection<User> getUsers() {
		return this.userList;
	}

	@Override
	public String postUser(User user) {
		user.setId((long) this.userList.size());
		if (this.userList.add(user))
			return user.getUsername() + " registered succesfully!";
		else
			return "could not register user";
	}

	@Override
	public String deleteUser(Long id) {
		User user = findUserById(id);
		if (this.userList.remove(user))
			return user.getUsername() + " removed succesfully!";
		else
			return "could not delete user";
	}

	@Override
	public String putUser(User user) {
		User old = this.findUserById(user.getId());
		old.setEmail(user.getEmail());
		old.setPassword(user.getPassword());
		old.setUsername(user.getUsername());
		return user.getUsername() + " updated succesfully!";
	}

	public User findUserById(Long id) {
		User user = null;
		for (User u : this.userList) {
			if (u.getId().equals(id))
				user = u;
		}
		return user;
	}

}
