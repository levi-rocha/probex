package br.unifor.probex.restful.resources;

	import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.unifor.probex.business.PostBORemote;
import br.unifor.probex.entity.Post;

	@Stateless
	@Path("/posts")
	public class PostResource {{

		@EJB
		private PostBORemote postBO;

		@Path("{id}")
		@GET
		@Produces("application/json")
		public Post findPostById(@PathParam("id") Long id) {
			return postBO.findPostById(id);
		}

		@GET
		@Produces("application/json")
		public Collection<PostDTO> listPosts() {
			return postBO.listPosts();
		}

		@POST
		@Consumes("application/json")
		@Produces("text/plain")
		public String addPost(Post post) {
			return postBO.addPost(post);
		}

		@PUT
		@Consumes("application/json")
		@Produces("text/plain")
		public String updatePost() {
			return postBO.updatePost(post);
		}

		@Path("{id}")
		@DELETE
		@Produces("text/plain")
		public String removePost(@PathParam("id") Long id) {
			return postBO.removePost(id);
		}

	}
	
}
