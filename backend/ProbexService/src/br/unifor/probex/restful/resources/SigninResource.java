package br.unifor.probex.restful.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.unifor.probex.business.UserBORemote;
import br.unifor.probex.dto.UserDTO;
import br.unifor.probex.entity.User;

@Stateless
@Path("/signin")
public class SigninResource {

	@EJB
	private UserBORemote userBO;

	@Path("u={username}-p={password}")
	@GET
	@Produces("application/json")
	public UserDTO validateUserPassword(@PathParam("username") String username,
			@PathParam("password") String password) {
		User data = userBO.validateUserPassword(username, password);
		UserDTO user = new UserDTO(data.getId(), data.getUsername(), data.getPassword(), data.getEmail());
		return user;
	}

}
