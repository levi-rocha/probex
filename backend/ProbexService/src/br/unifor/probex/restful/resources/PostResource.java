package br.unifor.probex.restful.resources;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.unifor.probex.business.PostBORemote;
import br.unifor.probex.dao.PostDAO;
import br.unifor.probex.dto.PostDetailedDTO;
import br.unifor.probex.dto.PostSimpleDTO;
import br.unifor.probex.dto.VoteDTO;
import br.unifor.probex.entity.Post;

@Stateless
@Path("/posts")
public class PostResource {

	@EJB
	private PostBORemote postBO;

	@Path("{id}")
	@GET
	@Produces("application/json")
	public PostDetailedDTO findPostById(@PathParam("id") Long id) {
		return postBO.findPostById(id);
	}

	@GET
	@Produces("application/json")
	public List<PostSimpleDTO> listPosts(@QueryParam("q") int quantity, @QueryParam("c") String criteria,
			@QueryParam("s") int start, @QueryParam("k") String keywords) {
		return postBO.listPosts(quantity, start, criteria, keywords);
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public PostDetailedDTO addPost(Post post) {
		return postBO.addPost(post);
	}

	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public PostDetailedDTO updatePost(Post post) {
		return postBO.updatePost(post);
	}

	@Path("{id}")
	@DELETE
	@Produces("text/plain")
	public PostDetailedDTO removePost(@PathParam("id") Long id) {
		return postBO.removePost(id);
	}

	@Path("/vote")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public PostDetailedDTO voteOnPost(VoteDTO vote) {
		return postBO.voteOnPost(vote);
	}

}
