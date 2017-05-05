package br.unifor.probex.business;

import java.util.Collection;

import javax.ejb.Remote;

import br.unifor.probex.entity.Comment;
import br.unifor.probex.entity.User;

@Remote
public interface CommentBORemote {

	public Collection<Comment> listComments();
	
	public Comment findCommentById(Long id);
	
	public String addComment(Comment comment);

	public String removeComment(Long id);

	public String updateComment(Comment comment);
}
