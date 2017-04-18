package br.unifor.probex.restful.resources;

import java.util.ArrayList;
import java.util.Collection;

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

import br.unifor.probex.business.UserBORemote;
import br.unifor.probex.dto.UserPermissionsDTO;
import br.unifor.probex.entity.User;

@Stateless
@Path("/users")
public class UserResource {

	@EJB
	private UserBORemote userBO;

	@Path("{id}")
	@GET
	@Produces("application/json")
	public User findUserById(@PathParam("id") Long id) {
		return userBO.findUserById(id);
	}

	@GET
	@Produces("application/json")
	public Collection<UserPermissionsDTO> listUsers() {
		Collection<UserPermissionsDTO> data = userBO.listUsers();
		for (UserPermissionsDTO u : data) {
			u.setPassword(null);
		}
		return data;
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
