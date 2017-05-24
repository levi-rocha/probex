package br.unifor.probex.dto;

import java.io.Serializable;
import java.util.Date;

import br.unifor.probex.entity.Comment;

public class CommentDTO implements Serializable {

	private static final long serialVersionUID = 3069238842078018772L;

	private Long id;
	private String content;
	private Date date;
	private String authorUsername;

	public static CommentDTO fromComment(Comment comment) {
		CommentDTO dto = new CommentDTO();
		dto.setId(comment.getId());
		dto.setContent(comment.getContent());
		dto.setDate(comment.getDate());
		dto.setAuthorUsername(comment.getAuthor().getUsername());
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
