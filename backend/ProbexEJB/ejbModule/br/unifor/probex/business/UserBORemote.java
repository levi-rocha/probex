package br.unifor.probex.business;

import java.util.List;

import javax.ejb.Remote;

import br.unifor.probex.dto.UserDetailedDTO;
import br.unifor.probex.dto.UserSimpleDTO;
import br.unifor.probex.entity.User;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.InvalidPasswordException;
import br.unifor.probex.exception.InvalidUsernameException;
import br.unifor.probex.exception.NotFoundException;

@Remote
public interface UserBORemote {

	List<UserSimpleDTO> listUsers(int quantity, int start,
								  String username)
			throws NotFoundException;

	UserDetailedDTO findUserById(Long id) throws NotFoundException;

	User validateUserPassword(String username, String password)
			throws NotFoundException;

	UserDetailedDTO addUser(User user) throws InvalidUsernameException,
			InvalidPasswordException, DatabaseException;

	UserSimpleDTO removeUser(Long id) throws DatabaseException,
			NotFoundException;

	UserDetailedDTO updateUser(User user) throws
			InvalidUsernameException, InvalidPasswordException,
			DatabaseException, NotFoundException;
}
