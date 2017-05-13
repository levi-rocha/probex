package br.unifor.probex.dto;

import java.util.Set;

import br.unifor.probex.entity.Permission;

public class UserDetailedDTO {
	
	private Long id;
	private String username;
	private String password;
	private String email;
	private Set<Permission> permissions;
	private Set<PostSimpleDTO> posts;
	
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

	public Set<PostSimpleDTO> getPosts() {
		return posts;
	}

	public void setPosts(Set<PostSimpleDTO> posts) {
		this.posts = posts;
	}
	
	

}
