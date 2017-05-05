package br.unifor.probex.dao;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.unifor.probex.entity.Comment;

@Stateless
public class CommentDAO {

	@PersistenceContext
	private EntityManager manager;

	public Collection<Comment> list() {
		// TODO
		return null;
	}

	public Comment findById(Long id) {
		// TODO
		return null;
	}

	public String insert(Comment comment) {
		// TODO
		return null;
	}

	public String update(Comment comment) {
		// TODO
		return null;
	}

	public String remove(Long id) {
		// TODO
		return null;
	}

}
