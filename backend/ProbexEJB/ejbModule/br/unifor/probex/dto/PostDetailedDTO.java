package br.unifor.probex.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

import br.unifor.probex.entity.Comment;
import br.unifor.probex.entity.Post;
import br.unifor.probex.entity.User;

public class PostDetailedDTO extends PostSimpleDTO implements Serializable {

	private static final long serialVersionUID = -6083320017667645716L;

	private Long id;
	private String title;
	private String content;
	private Date date;
	private String authorUsername;
	private Set<Long> voteIds;
	private List<CommentDTO> comments;

	public static PostDetailedDTO fromPost(Post post) {
		PostDetailedDTO dto = new PostDetailedDTO();
		dto.setId(post.getId());
		dto.setTitle(post.getTitle());
		dto.setDate(post.getDate());
		dto.setAuthorUsername(post.getAuthor().getUsername());
		dto.setContent(post.getContent());
		Set<Long> voteIds = new HashSet<Long>();
		for (User u : post.getVotes()) {
			voteIds.add(u.getId());
		}
		dto.setVoteIds(voteIds);
		List<CommentDTO> comments = new ArrayList<>();
		for (Comment c : post.getComments()) {
			CommentDTO cdto = CommentDTO.fromComment(c);
			comments.add(cdto);
		}
		dto.setComments(comments);
		return dto;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}

}
