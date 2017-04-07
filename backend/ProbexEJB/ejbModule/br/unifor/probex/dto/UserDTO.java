package br.unifor.probex.dto;

public class UserDTO {
	
	private Long id;
	private String username;
	private String password;
	private String email;	
	
	public UserDTO(Long id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	
	

}
