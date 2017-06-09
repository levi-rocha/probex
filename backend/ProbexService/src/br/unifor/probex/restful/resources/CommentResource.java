package br.unifor.probex.restful.resources;

import br.unifor.probex.business.CommentBORemote;
import br.unifor.probex.dto.CommentDTO;
import br.unifor.probex.entity.Comment;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.InvalidArgumentException;
import br.unifor.probex.exception.NotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/comments")
public class CommentResource {

	@EJB
	private CommentBORemote commentBO;

	@Path("{id}")
	@GET
	@Produces("application/json")
	public Response findCommentById(@PathParam("id") Long id) {
        try {
            CommentDTO data = commentBO.findCommentById(id);
            return Response.ok(data, MediaType.APPLICATION_JSON).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
	}

	@GET
	@Produces("application/json")
	public Response listComments(@QueryParam("q") int quantity) {
		List<CommentDTO> data = commentBO.listComments(quantity);
        return Response.ok(data, MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public Response addComment(Comment comment) {
        try {
            CommentDTO data = commentBO.addComment(comment);
            return Response.ok(data, MediaType.APPLICATION_JSON).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        } catch (DatabaseException e) {
            return Response.serverError().entity(e.getMessage()).build();
        } catch (InvalidArgumentException e) {
            return Response.status(422).entity(e.getMessage()).build();
        }
    }

	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public Response updateComment(Comment comment) {
		try {
			CommentDTO data = commentBO.updateComment(comment);
			return Response.ok(data, MediaType.APPLICATION_JSON).build();
		} catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
		} catch (DatabaseException e) {
            return Response.serverError().entity(e.getMessage()).build();
		} catch (InvalidArgumentException e) {
            return Response.status(422).entity(e.getMessage()).build();
        }
    }

	@Path("{id}")
	@DELETE
	@Produces("text/plain")
	public Response removeComment(@PathParam("id") Long id) {
        try {
            CommentDTO data = commentBO.removeComment(id);
            return Response.ok(data, MediaType.APPLICATION_JSON).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        } catch (DatabaseException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
