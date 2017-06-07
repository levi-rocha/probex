package br.unifor.probex.restful.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.unifor.probex.business.UserBORemote;
import br.unifor.probex.dto.UserDetailedDTO;
import br.unifor.probex.entity.User;
import br.unifor.probex.exception.NotFoundException;

@Stateless
@Path("/signin")
public class SigninResource {

	@EJB
	private UserBORemote userBO;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response validate(User user) {
		try {
			UserDetailedDTO dto = UserDetailedDTO.fromUser(userBO.validateUserPassword(
					user.getUsername(), user.getPassword()));
			return Response.ok(dto, MediaType.APPLICATION_JSON).build();
		} catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

}
