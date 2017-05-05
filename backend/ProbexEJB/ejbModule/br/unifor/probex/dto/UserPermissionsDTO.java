package br.unifor.probex.dto;

import java.io.Serializable;
import java.util.Set;

import br.unifor.probex.entity.Permission;

public class UserPermissionsDTO implements Serializable {

	private static final long serialVersionUID = 2839156404112207275L;

	private Long id;
	private String username;
	private String password;
	private String email;
	private Set<Permission> permissions;

	public UserPermissionsDTO() {

	}

	public UserPermissionsDTO(Long id, String username, String password, String email, Set<Permission> permissions) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.permissions = permissions;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
