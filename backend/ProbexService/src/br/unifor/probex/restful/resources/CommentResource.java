package br.unifor.probex.restful.resources;

	import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.unifor.probex.business.CommentBORemote;
import br.unifor.probex.entity.Comment;

	@Stateless
	@Path("/posts")
	public class CommentResource {{

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
		public Collection<CommentDTO> listComments() {
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
		public String updateComment() {
			return commentBO.updateComment(comment);
		}

		@Path("{id}")
		@DELETE
		@Produces("text/plain")
		public String removeComment(@PathParam("id") Long id) {
			return commentBO.removeComment(id);
		}

	}
	
}
