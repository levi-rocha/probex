package br.unifor.probex.business;

import java.util.List;

import javax.ejb.Remote;

import br.unifor.probex.dto.CommentDTO;
import br.unifor.probex.entity.Comment;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.InvalidCommentException;
import br.unifor.probex.exception.NotFoundException;

@Remote
public interface CommentBORemote {
	
	List<CommentDTO> listComments(int quantity);

	CommentDTO findCommentById(Long id) throws NotFoundException;

	CommentDTO addComment(Comment comment) throws DatabaseException, NotFoundException, InvalidCommentException;

	CommentDTO removeComment(Long id) throws NotFoundException, DatabaseException;

	CommentDTO updateComment(Comment comment) throws NotFoundException, DatabaseException, InvalidCommentException;
}
