package br.unifor.probex.business;

import br.unifor.probex.dao.CommentDAO;
import br.unifor.probex.dto.CommentDTO;
import br.unifor.probex.entity.Comment;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.InvalidCommentException;
import br.unifor.probex.exception.NotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CommentBO implements CommentBORemote {

	@EJB
	private CommentDAO commentDAO;

	public CommentBO() {

	}

	public List<CommentDTO> listComments(int quantity) {
		List<CommentDTO> dtos = new ArrayList<>();
		for (Comment c : this.commentDAO.list(quantity)) {
			dtos.add(CommentDTO.fromComment(c));
		}
		return dtos;
	}

	public CommentDTO findCommentById(Long id) throws NotFoundException {
		return CommentDTO.fromComment(this.commentDAO.findById(id));
	}

	public CommentDTO addComment(Comment comment) throws DatabaseException,
            NotFoundException, InvalidCommentException {
        if (comment.getContent() == null || comment.getContent().length() < 5)
            throw new InvalidCommentException("Comment content must be " +
                    "longer than 5");
		return CommentDTO.fromComment(this.commentDAO.insert(comment));
	}

	public CommentDTO removeComment(Long id) throws NotFoundException,
			DatabaseException {
		return CommentDTO.fromComment(this.commentDAO.remove(id));
	}

	public CommentDTO updateComment(Comment comment) throws NotFoundException,
            DatabaseException, InvalidCommentException {
        if (comment.getContent() == null || comment.getContent().length() < 5)
            throw new InvalidCommentException("Comment content must be " +
                    "longer than 5");
		return CommentDTO.fromComment(this.commentDAO.update(comment));
	}

}