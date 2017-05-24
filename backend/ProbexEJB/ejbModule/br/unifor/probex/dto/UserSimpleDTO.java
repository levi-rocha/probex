package br.unifor.probex.dto;

import java.io.Serializable;
import java.util.Set;

import br.unifor.probex.entity.Permission;
import br.unifor.probex.entity.User;

public class UserSimpleDTO implements Serializable {

	private static final long serialVersionUID = 2839156404112207275L;

	private Long id;
	private String username;
	private String email;
	private Set<Permission> permissions;

	public static UserSimpleDTO fromUser(User user) {
		UserSimpleDTO dto = new UserSimpleDTO();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setPermissions(user.getPermissions());
		return dto;
	}

	public UserSimpleDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

}
