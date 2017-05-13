package br.unifor.probex.restful.resources;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
import javax.ws.rs.core.MediaType;

import br.unifor.probex.business.PostBORemote;
import br.unifor.probex.dto.PostSimpleDTO;
import br.unifor.probex.entity.Post;

@Stateless
@Path("/posts")
public class PostResource {

	@EJB
	private PostBORemote postBO;
	
	//TODO user queryparams

	@Path("{id}")
	@GET
	@Produces("application/json")
	public Post findPostById(@PathParam("id") Long id) {
		return postBO.findPostById(id);
	}

	@GET
	@Produces("application/json")
	public Collection<PostSimpleDTO> listPosts() {
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
	public String updatePost(Post post) {
		return postBO.updatePost(post);
	}

	@Path("{id}")
	@DELETE
	@Produces("text/plain")
	public String removePost(@PathParam("id") Long id) {
		return postBO.removePost(id);
	}

}
