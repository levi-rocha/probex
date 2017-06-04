package br.unifor.probex.restful.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unifor.probex.business.UserBORemote;
import br.unifor.probex.dto.UserDetailedDTO;
import br.unifor.probex.entity.User;

@Stateless
@Path("/signin")
public class SigninResource {

	@EJB
	private UserBORemote userBO;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserDetailedDTO validate(User user) {
		return userBO.validateUserPassword(user.getUsername(),
				user.getPassword());
	}

}
