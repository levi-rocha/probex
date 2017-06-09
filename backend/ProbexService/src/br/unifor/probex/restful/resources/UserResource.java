package br.unifor.probex.restful.resources;

import br.unifor.probex.business.UserBORemote;
import br.unifor.probex.dto.UserDetailedDTO;
import br.unifor.probex.dto.UserSimpleDTO;
import br.unifor.probex.entity.User;
import br.unifor.probex.exception.*;
import br.unifor.probex.exception.NotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/users")
public class UserResource {

	@EJB
	private UserBORemote userBO;

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findUserById(@PathParam("id") Long id)  {
		try {
			UserDetailedDTO data = userBO.findUserById(id);
			return Response.ok(data, MediaType.APPLICATION_JSON).build();
		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listUsers(@QueryParam("q") int quantity,
                                         @QueryParam("u") String username,
                                         @QueryParam("s") int start) {
        try {
            List<UserSimpleDTO> data = userBO.listUsers(
                    quantity, start, username);
            return Response.ok(data, MediaType.APPLICATION_JSON).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
        try {
            UserDetailedDTO data = userBO.addUser(user);
            return Response.ok(data, MediaType.APPLICATION_JSON).build();
        } catch (InvalidArgumentException e) {
            return Response.status(422).entity(e.getMessage()).build();
        } catch (DatabaseException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(User user) {
        try {
            UserDetailedDTO data = userBO.updateUser(user);
            return Response.ok(data, MediaType.APPLICATION_JSON).build();
        } catch (InvalidArgumentException e) {
            return Response.status(422).entity(e.getMessage()).build();
        } catch (DatabaseException e) {
            return Response.serverError().entity(e.getMessage()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

	@Path("{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeUser(@PathParam("id") Long id) {
        try {
            UserSimpleDTO data = userBO.removeUser(id);
            return Response.ok(data, MediaType.APPLICATION_JSON).build();
        } catch (DatabaseException e) {
            return Response.serverError().entity(e.getMessage()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

}
