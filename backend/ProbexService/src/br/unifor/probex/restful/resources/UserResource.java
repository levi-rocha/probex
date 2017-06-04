package br.unifor.probex.restful.resources;

import br.unifor.probex.business.UserBORemote;
import br.unifor.probex.dto.UserDetailedDTO;
import br.unifor.probex.dto.UserSimpleDTO;
import br.unifor.probex.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.List;

@Stateless
@Path("/users")
public class UserResource {

	@EJB
	private UserBORemote userBO;

	@Path("{id}")
	@GET
	@Produces("application/json")
	public UserDetailedDTO findUserById(@PathParam("id") Long id) {
		return userBO.findUserById(id);
	}

	@GET
	@Produces("application/json")
	public List<UserSimpleDTO> listUsers(@QueryParam("q") int quantity,
                                         @QueryParam("u") String username,
                                         @QueryParam("s") int start) {
		return userBO.listUsers(quantity, start, username);
	}

	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public UserDetailedDTO addUser(User user) {
		return userBO.addUser(user);
	}

	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public UserDetailedDTO updateUser(User user) {
		return userBO.updateUser(user);
	}

	@Path("{id}")
	@DELETE
	@Produces("text/plain")
	public UserSimpleDTO removeUser(@PathParam("id") Long id) {
		return userBO.removeUser(id);
	}

}
