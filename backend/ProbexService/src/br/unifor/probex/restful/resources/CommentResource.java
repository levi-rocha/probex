package br.unifor.probex.restful.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

import br.unifor.probex.business.CommentBORemote;
import br.unifor.probex.dto.CommentDTO;
import br.unifor.probex.entity.Comment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Stateless
@Path("/comments")
public class CommentResource {

	@EJB
	private CommentBORemote commentBO;

	@Path("{id}")
	@GET
	@Produces("application/json")
	public CommentDTO findCommentById(@PathParam("id") Long id) {
		return commentBO.findCommentById(id);
	}

	@GET
	@Produces("application/json")
	public List<CommentDTO> listComments(@QueryParam("q") int quantity) {
		return commentBO.listComments(quantity);
	}

	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public CommentDTO addComment(Comment comment) {
		return commentBO.addComment(comment);
	}

	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public CommentDTO updateComment(Comment comment) {
		return commentBO.updateComment(comment);
	}

	@Path("{id}")
	@DELETE
	@Produces("text/plain")
	public CommentDTO removeComment(@PathParam("id") Long id) {
		return commentBO.removeComment(id);
	}

}
