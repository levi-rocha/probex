package br.unifor.probex.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import br.unifor.probex.entity.Comment;
import br.unifor.probex.entity.User;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.NotFoundException;

@Stateless
public class CommentDAO {

	@PersistenceContext
	private EntityManager manager;

	public List<Comment> list(int quantity) {
		if (quantity > 0) {
			return manager.createQuery("SELECT c FROM Comment c " +
					"ORDER BY c.date ASC", Comment.class)
					.setMaxResults(quantity).getResultList();
		} else {
			return manager.createQuery("SELECT c FROM Comment c " +
					"ORDER BY c.date ASC", Comment.class).getResultList();
		}
	}

	public Comment findById(Long id) throws NotFoundException {
		Comment comment = manager
				.createQuery("select c from Comment c " +
								"left join fetch c.author " +
								"left join fetch c.post where c.id = :id",
						Comment.class)
				.setParameter("id", id).getSingleResult();
		if (comment == null)
			throw new NotFoundException(
					"No comment found with id " + id);
		return comment;
	}

	public Comment insert(Comment comment) throws DatabaseException,
			NotFoundException {
		try {
			User author = manager.createQuery("SELECT u " +
					"FROM User u WHERE u.username = :username", User.class)
					.setParameter("username", comment.getAuthor()
							.getUsername()).getSingleResult();
			if (author == null)
				throw new NotFoundException(
						"No user found with username " + comment.getAuthor()
								.getUsername());
			comment.setAuthor(author);
			manager.persist(comment);
			manager.flush();
			return comment;
		} catch (PersistenceException e) {
			throw new DatabaseException("Could not insert comment");
		}
	}

	public Comment update(Comment comment) throws NotFoundException,
			DatabaseException {
		try {
			Comment detached = manager.find(Comment.class, comment.getId());
			if (detached == null)
				throw new NotFoundException(
						"No comment found with id " + comment.getId());
			Comment managed = manager.merge(detached);
			managed.setContent(comment.getContent());
			return managed;
		} catch (PersistenceException e) {
			throw new DatabaseException("Could not update comment");
		}
	}

	public Comment remove(Long id) throws NotFoundException, DatabaseException {
		try {
			Comment comment = manager.find(Comment.class, id);
			if (comment == null)
				throw new NotFoundException(
						"No comment found with id " + id);
			manager.remove(comment);
			return comment;
		} catch (PersistenceException e) {
			throw new DatabaseException("Could not remove comment");
		}
	}

}
