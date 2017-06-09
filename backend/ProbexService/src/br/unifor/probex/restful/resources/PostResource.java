package br.unifor.probex.restful.resources;

import br.unifor.probex.business.PostBORemote;
import br.unifor.probex.dto.PostDetailedDTO;
import br.unifor.probex.dto.PostSimpleDTO;
import br.unifor.probex.dto.VoteDTO;
import br.unifor.probex.entity.Post;
import br.unifor.probex.exception.*;
import br.unifor.probex.exception.NotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/posts")
public class PostResource {

	@EJB
	private PostBORemote postBO;

	@Path("{id}")
	@GET
	@Produces("application/json")
	public Response findPostById(@PathParam("id") Long id) {
		try {
			PostDetailedDTO data = postBO.findPostById(id);
			return Response.ok(data, MediaType.APPLICATION_JSON).build();
		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(e.getMessage()).build();
		}
	}

	@GET
	@Produces("application/json")
	public Response listPosts(@QueryParam("q") int quantity,
							  @QueryParam("c") String criteria,
							  @QueryParam("s") int start,
							  @QueryParam("k") String keywords) {
		List<PostSimpleDTO> data = postBO.listPosts(
				quantity, start, criteria, keywords);
		return Response.ok(data, MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPost(Post post) {
		try {
			PostDetailedDTO data = postBO.addPost(post);
			return Response.ok(data, MediaType.APPLICATION_JSON).build();
		} catch (InvalidArgumentException e) {
			return Response.status(422).entity(e.getMessage()).build();
		} catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public Response updatePost(Post post) {
		try {
			PostDetailedDTO data = postBO.updatePost(post);
			return Response.ok(data, MediaType.APPLICATION_JSON).build();
		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(e.getMessage()).build();
		} catch (ServerException e) {
			return Response.serverError().entity(e.getMessage()).build();
		} catch (InvalidArgumentException e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

	@Path("{id}")
	@DELETE
	@Produces("text/plain")
	public Response removePost(@PathParam("id") Long id) {
		try {
			PostDetailedDTO data = postBO.removePost(id);
			return Response.ok(data, MediaType.APPLICATION_JSON).build();
		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(e.getMessage()).build();
		} catch (ServerException e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Path("/vote")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response voteOnPost(VoteDTO vote) {
		try {
			PostDetailedDTO data = postBO.voteOnPost(vote);
			return Response.ok(data, MediaType.APPLICATION_JSON).build();
		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(e.getMessage()).build();
		} catch (ServerException e) {
			return Response.serverError().entity(e.getMessage()).build();
		} catch (InvalidArgumentException e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

}
