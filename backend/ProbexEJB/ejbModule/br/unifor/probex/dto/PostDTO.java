package br.unifor.probex.dto;

import java.io.Serializable;
import java.util.Set;

public class PostDTO implements Serializable {

	private static final long serialVersionUID = 2355904530283938395L;
	
	private Long id;
	private String title;
	private String content;
	private Long authorId;
	private String authorUsername;
	private Set<Long> voteIds;
	
	public PostDTO() {
		
	}
	
	public PostDTO(Long id, String title, String content, Long authorId, String authorUsername, Set<Long> voteIds) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.authorId = authorId;
		this.authorUsername = authorUsername;
		this.voteIds = voteIds;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorUsername() {
		return authorUsername;
	}

	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
	}

	public Set<Long> getVoteIds() {
		return voteIds;
	}

	public void setVoteIds(Set<Long> voteIds) {
		this.voteIds = voteIds;
	}
	
}
