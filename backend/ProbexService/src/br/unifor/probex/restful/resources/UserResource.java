package br.unifor.probex.restful.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
import javax.ws.rs.QueryParam;

import br.unifor.probex.business.UserBORemote;
import br.unifor.probex.dto.PostSimpleDTO;
import br.unifor.probex.dto.UserSimpleDTO;
import br.unifor.probex.dto.UserDetailedDTO;
import br.unifor.probex.entity.Post;
import br.unifor.probex.entity.User;

@Stateless
@Path("/users")
public class UserResource {

	@EJB
	private UserBORemote userBO;

	@Path("{id}")
	@GET
	@Produces("application/json")
	public User findUserById(@PathParam("id") Long id) {
		User user = userBO.findUserById(id);
		user.setPassword(null);
		return user;
	}

	@GET
	@Produces("application/json")
	public Collection<UserDetailedDTO> listUsers(@QueryParam("q") int quantity, @QueryParam("u") String username,
			@QueryParam("s") int start) {
		if (username != null) {
			User user = userBO.findUserByUsername(username);
			UserDetailedDTO dto = new UserDetailedDTO();
			dto.setId(user.getId());
			dto.setUsername(user.getUsername());
			dto.setEmail(user.getEmail());
			dto.setPermissions(user.getPermissions());
			Set<PostSimpleDTO> posts = new HashSet<PostSimpleDTO>();
			for (Post post : user.getPosts()) {
				PostSimpleDTO pdto = new PostSimpleDTO();
				pdto.setId(post.getId());
				pdto.setTitle(post.getTitle());
				pdto.setAuthorUsername(user.getUsername());
				pdto.setVoteCount(post.getVotes().size());
				posts.add(pdto);
			}
			dto.setPosts(posts);
			Collection<UserDetailedDTO> data = new ArrayList<UserDetailedDTO>();
			data.add(dto);
			return data;
		}
		List<UserSimpleDTO> data = userBO.listUsers();
		if (quantity > 0) {
			List<UserSimpleDTO> temp = new ArrayList<UserSimpleDTO>();
			if (start < 0)
				start = 0;
			for (int i = start; i < start + quantity; i++) {
				if (data.size() < i + 1) {
					break;
				}
				temp.add(data.get(i));
			}
			data = temp;
		}
		List<UserDetailedDTO> userData = new ArrayList<UserDetailedDTO>();
		for (UserSimpleDTO u : data) {
			User user = new User();
			user.setId(u.getId());
			user.setUsername(u.getUsername());
			user.setEmail(u.getEmail());
			user.setPassword(null);
			user.setPermissions(u.getPermissions());
			// user.setPosts(null);
		}
		return userData;
	}

	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public String addUser(User user) {
		return userBO.addUser(user);
	}

	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public String updateUser(User user) {
		return userBO.updateUser(user);
	}

	@Path("{id}")
	@DELETE
	@Produces("text/plain")
	public String removeUser(@PathParam("id") Long id) {
		return userBO.removeUser(id);
	}

}
