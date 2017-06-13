package br.unifor.probex.business;

import br.unifor.probex.dao.UserDAO;
import br.unifor.probex.dto.UserDetailedDTO;
import br.unifor.probex.dto.UserSimpleDTO;
import br.unifor.probex.entity.User;
import br.unifor.probex.exception.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
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
			List<UserSimpleDTO> dtos = new ArrayList<>();
			if (user != null) {
				UserSimpleDTO dto = UserSimpleDTO.fromUser(user);
                dtos.add(dto);
			}
			return dtos;
		}
		// no username
		List<User> users = userDAO.list(quantity, start);
		List<UserSimpleDTO> dtos = new ArrayList<>();
		for (User u : users) {
			UserSimpleDTO dto = UserSimpleDTO.fromUser(u);
            dtos.add(dto);
		}
		return dtos;
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
            throws NotFoundException, ServerException {
	    String encryptedPass = encryptPassword(password);
		return this.userDAO.findByUsernameAndPassword(username, encryptedPass);
	}

	@Override
	public UserDetailedDTO addUser(User user) throws InvalidUsernameException,
            InvalidPasswordException, ServerException {
        System.out.println("******************************************");
	    System.out.println("fulano encrypted: " + encryptPassword("fulano"));
        System.out.println("beltrano encrypted: " +
                encryptPassword("beltrano"));
        System.out.println("admin encrypted: " + encryptPassword("admin"));
        System.out.println("******************************************");
        validateUsername(user.getUsername());
        validatePassword(user.getPassword());
        user.setPassword(encryptPassword(user.getPassword()));
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
            NotFoundException, ServerException {
        validateUsername(user.getUsername());
        validatePassword(user.getPassword());
        user.setPassword(encryptPassword(user.getPassword()));
        User updated = this.userDAO.update(user);
        if (updated == null)
            throw new DatabaseException("Could not insert user " +
                    user.getUsername());
        return UserDetailedDTO.fromUser(updated);
	}

    private void validateUsername(String username) throws
            InvalidUsernameException {
        if (username == null || username.length() < 3 || username.length() > 20)
            throw new InvalidUsernameException("Username must be between " +
                    "4 and 20 characters");
    }

    private void validatePassword(String password) throws
            InvalidPasswordException {
        if (password == null || password.length() < 3 || password.length() > 20)
            throw new InvalidPasswordException("Password must be between " +
                    "4 and 20 characters");
    }

    private String encryptPassword(String password) throws ServerException {
        try {
            byte[] passBytes = password.getBytes("UTF-8");
            byte[] encryptedBytes = MessageDigest.getInstance("MD5")
                    .digest(passBytes);
            return new String(encryptedBytes,
                    StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new ServerException("Encoding error!");
        }
    }

}