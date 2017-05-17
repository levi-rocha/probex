package br.unifor.probex.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import br.unifor.probex.entity.Comment;

@Stateless
public class CommentDAO {

	@PersistenceContext
	private EntityManager manager;

	public List<Comment> list() {
		List<Comment> comments = manager.createQuery("SELECT c FROM Comment c", Comment.class).getResultList();
		return comments;
	}

	public Comment findById(Long id) {
		Comment comment = manager
				.createQuery("select c from Comment c left join fetch c.author left join fetch c.post where c.id = :id",
						Comment.class)
				.setParameter("id", id).getSingleResult();
		return comment;
	}

	public String insert(Comment comment) {
		try {
			manager.persist(comment);
			return comment.getContent() + " inserted";
		} catch (PersistenceException e) {
			return "could not insert data " + e;
		}
	}

	public String update(Comment comment) {
		try {
			Comment detached = manager.find(Comment.class, comment.getId());

			if (detached == null)
				return "no comment found with id " + comment.getId();

			Comment managed = manager.merge(detached);

			managed.setContent(comment.getContent());

			return comment.getContent() + " updated";
		} catch (PersistenceException e) {
			return "could not update data " + e;
		}
	}

	public String remove(Long id) {
		try {
			Comment comment = manager.find(Comment.class, id);
			manager.remove(comment);
			return comment.getContent() + " removed";
		} catch (PersistenceException e) {
			return "could not remove data " + e;
		}
	}

}
