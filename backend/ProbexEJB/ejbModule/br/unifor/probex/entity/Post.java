package br.unifor.probex.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "posts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post implements Serializable {

	private static final long serialVersionUID = 8029789922827771935L;

	@PrePersist
	protected void onCreate() {
		date = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false, length = 9999)
	private String content;

	@ManyToOne
	private User author;

	@ManyToMany
	@JoinTable(
			name = "votes",
			joinColumns = @JoinColumn(
					name = "post_id",
					referencedColumnName = "id"
			),
			inverseJoinColumns = @JoinColumn(
					name = "user_id",
					referencedColumnName = "id"
			)
	)
	private Set<User> votes;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	/* getters and setters */

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Set<User> getVotes() {
		return votes;
	}

	public void setVotes(Set<User> votes) {
		this.votes = votes;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
