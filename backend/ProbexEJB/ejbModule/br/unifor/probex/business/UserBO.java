package br.unifor.probex.business;

import br.unifor.probex.dao.UserDAO;
import br.unifor.probex.dto.UserDetailedDTO;
import br.unifor.probex.dto.UserSimpleDTO;
import br.unifor.probex.entity.User;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.InvalidPasswordException;
import br.unifor.probex.exception.InvalidUsernameException;
import br.unifor.probex.exception.NotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserBO implements UserBORemote {

	@EJB
	private UserDAO userDAO;

	public UserBO() {

	}

	@Override
	public List<UserSimpleDTO> listUsers(int quantity, int start,
                                         String username)
            throws NotFoundException {
	    // find by username
		if (username != null) {
			User user = userDAO.findByUsername(username);
			List<UserSimpleDTO> data = new ArrayList<>();
			if (user != null) {
				UserSimpleDTO dto = UserSimpleDTO.fromUser(user);
				data.add(dto);
			}
			return data;
		}
		if (start < 0)
			start = 0;
		if (quantity < 0)
			quantity = 0;
		List<User> data = userDAO.list(quantity, start);
		List<UserSimpleDTO> userData = new ArrayList<>();
		for (User u : data) {
			UserSimpleDTO dto = UserSimpleDTO.fromUser(u);
			userData.add(dto);
		}
		return userData;
	}

	@Override
	public UserDetailedDTO findUserById(Long id) throws NotFoundException {
        User user = userDAO.findById(id);
        if (user == null)
            return null;
        return UserDetailedDTO.fromUser(user);
	}

	@Override
	public User validateUserPassword(String username, String password)
            throws NotFoundException {
		return this.userDAO.findByUsernameAndPassword(username, password);
	}

	@Override
	public UserDetailedDTO addUser(User user) throws InvalidUsernameException,
            InvalidPasswordException, DatabaseException {
        if (user.getUsername().length() < 3)
            throw new InvalidUsernameException("Username is too short");
        if (user.getPassword().length() < 3)
            throw new InvalidPasswordException("Password is too short");
	    User inserted = this.userDAO.insert(user);
	    if (inserted == null)
            throw new DatabaseException("Could not insert user " +
                    user.getUsername());
	    return UserDetailedDTO.fromUser(inserted);
	}

	@Override
	public UserSimpleDTO removeUser(Long id) throws DatabaseException,
            NotFoundException {
		User user = this.userDAO.remove(id);
		return UserSimpleDTO.fromUser(user);
	}

	@Override
	public UserDetailedDTO updateUser(User user) throws
            InvalidUsernameException, InvalidPasswordException,
            DatabaseException, NotFoundException {
        if (user.getUsername().length() < 3)
            throw new InvalidUsernameException("Username is too short");
        if (user.getPassword().length() < 3)
            throw new InvalidPasswordException("Password is too short");
        User updated = this.userDAO.update(user);
        if (updated == null)
            throw new DatabaseException("Could not insert user " +
                    user.getUsername());
        return UserDetailedDTO.fromUser(updated);
	}

}