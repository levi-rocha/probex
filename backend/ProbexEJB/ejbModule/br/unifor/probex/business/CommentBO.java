package br.unifor.probex.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.unifor.probex.dao.CommentDAO;
import br.unifor.probex.dto.CommentDTO;
import br.unifor.probex.entity.Comment;

@Stateless
public class CommentBO implements CommentBORemote {

	@EJB
	private CommentDAO commentDAO;

	public CommentBO() {

	}

	public List<Comment> listComments(int quantity) {
		//TODO
		List<Comment> comments = commentBO.listComments(quantity);
		List<CommentDTO> dtos = new ArrayList<CommentDTO>();
		for (Comment c : comments) {
			CommentDTO dto = CommentDTO.fromComment(c);
			dtos.add(dto);
		}
		return dtos;
		return this.commentDAO.list(quantity);
	}

	public List<Comment> listComments() {
		return this.commentDAO.list(0);
	}

	public Comment findCommentById(Long id) {
		return this.commentDAO.findById(id);
	}

	public String addComment(Comment comment) {
		return this.commentDAO.insert(comment);
	}

	public String removeComment(Long id) {
		return this.commentDAO.remove(id);
	}

	public String updateComment(Comment comment) {
		return this.commentDAO.update(comment);
	}

}