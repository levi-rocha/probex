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

import br.unifor.probex.bean.UserBeanRemote;
import br.unifor.probex.entity.User;

@Stateless
@Path("/users")
public class UserResource {

	@EJB
	private UserBeanRemote userBean;

	@GET
	@Produces("application/json")
	public Collection<User> getUsers() {
		return this.userBean.getUsers();
	}

	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public String postUser(User user) {
		return this.userBean.postUser(user);
	}

	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public String putUser(User user) {
		return this.userBean.putUser(user);
	}

	@Path("{id}")
	@DELETE
	@Produces("text/plain")
	public String deleteUser(@PathParam("id") Long id) {
		return this.userBean.deleteUser(id);
	}

}
