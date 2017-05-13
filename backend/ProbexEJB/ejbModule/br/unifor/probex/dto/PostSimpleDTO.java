package br.unifor.probex.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class PostSimpleDTO implements Serializable {

	private static final long serialVersionUID = 2355904530283938395L;

	private Long id;
	private String title;
	private String authorUsername;
	private int voteCount;
	private Timestamp date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorUsername() {
		return authorUsername;
	}

	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

}
