package br.unifor.probex.dto;

import java.io.Serializable;
import java.util.Date;

import br.unifor.probex.entity.Post;

public class PostSimpleDTO implements Serializable {

	private static final long serialVersionUID = 2355904530283938395L;

	private Long id;
	private String title;
	private String authorUsername;
	private int voteCount;
	private Date date;

	public static PostSimpleDTO fromPost(Post post) {
		PostSimpleDTO dto = new PostSimpleDTO();
		dto.setId(post.getId());
		dto.setTitle(post.getTitle());
		dto.setDate(post.getDate());
		dto.setAuthorUsername(post.getAuthor().getUsername());
		dto.setVoteCount(post.getVotes().size());
		return dto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
