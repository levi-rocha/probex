package br.unifor.probex.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permissions")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Permission implements Serializable {

	private static final long serialVersionUID = -6331748582983397498L;

	public Permission() {

	}

	public Permission(String id) {
		this.id = Long.valueOf(id);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false,unique = true)
	private String name;

	/* getters and setters */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
