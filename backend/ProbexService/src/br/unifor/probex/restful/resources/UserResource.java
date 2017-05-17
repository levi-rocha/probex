package br.unifor.probex.restful.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.unifor.probex.business.UserBORemote;
import br.unifor.probex.dto.UserDetailedDTO;
import br.unifor.probex.dto.UserSimpleDTO;
import br.unifor.probex.entity.User;

@Stateless
@Path("/users")
public class UserResource {

	@EJB
	private UserBORemote userBO;

	@Path("{id}")
	@GET
	@Produces("application/json")
	public UserDetailedDTO findUserById(@PathParam("id") Long id) {
		User user = userBO.findUserById(id);
		UserDetailedDTO dto = UserDetailedDTO.fromUser(user);
		return dto;
	}

	@GET
	@Produces("application/json")
	@SuppressWarnings("rawtypes")
	public List listUsers(@QueryParam("q") int quantity, @QueryParam("u") String username, @QueryParam("s") int start) {
		if (username != null) {
			User user = userBO.findUserByUsername(username);
			UserDetailedDTO dto = UserDetailedDTO.fromUser(user);
			List<UserDetailedDTO> data = new ArrayList<UserDetailedDTO>();
			data.add(dto);
			return data;
		}
		List<User> data = userBO.listUsers();
		if (quantity > 0) {
			List<User> temp = new ArrayList<User>();
			if (start < 0)
				start = 0;
			for (int i = start; i < start + quantity; i++) {
				if (data.size() < i + 1) {
					break;
				}
				temp.add(data.get(i));
			}
			data = temp;
		}
		List<UserSimpleDTO> userData = new ArrayList<UserSimpleDTO>();
		for (User u : data) {
			UserSimpleDTO dto = UserSimpleDTO.fromUser(u);
			userData.add(dto);
		}
		return userData;
	}

	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public String addUser(User user) {
		return userBO.addUser(user);
	}

	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public String updateUser(User user) {
		return userBO.updateUser(user);
	}

	@Path("{id}")
	@DELETE
	@Produces("text/plain")
	public String removeUser(@PathParam("id") Long id) {
		return userBO.removeUser(id);
	}

}
