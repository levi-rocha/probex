package br.unifor.probex.restful.resources;

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

import br.unifor.probex.business.CommentBORemote;
import br.unifor.probex.entity.Comment;

@Stateless
@Path("/comments")
public class CommentResource {

	@EJB
	private CommentBORemote commentBO;

	@Path("{id}")
	@GET
	@Produces("application/json")
	public Comment findCommentById(@PathParam("id") Long id) {
		return commentBO.findCommentById(id);
	}

	@GET
	@Produces("application/json")
	public Collection<Comment> listComments() {
		return commentBO.listComments();
	}

	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public String addComment(Comment comment) {
		return commentBO.addComment(comment);
	}

	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public String updateComment(Comment comment) {
		return commentBO.updateComment(comment);
	}

	@Path("{id}")
	@DELETE
	@Produces("text/plain")
	public String removeComment(@PathParam("id") Long id) {
		return commentBO.removeComment(id);
	}

}
