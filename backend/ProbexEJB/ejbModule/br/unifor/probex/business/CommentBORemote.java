package br.unifor.probex.business;

import java.util.List;

import javax.ejb.Remote;

import br.unifor.probex.entity.Comment;

@Remote
public interface CommentBORemote {

	public List<Comment> listComments();

	public Comment findCommentById(Long id);

	public String addComment(Comment comment);

	public String removeComment(Long id);

	public String updateComment(Comment comment);
}
