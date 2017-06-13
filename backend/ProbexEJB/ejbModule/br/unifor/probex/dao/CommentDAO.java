package br.unifor.probex.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
		if (quantity <= 0 || quantity > 100)
			quantity = 100;
		return manager.createQuery("SELECT c FROM Comment c " +
				"ORDER BY c.date ASC", Comment.class)
				.setMaxResults(quantity).getResultList();
	}

	public Comment findById(Long id) throws NotFoundException {
		try {
            return manager
                    .createQuery("select c from Comment c " +
                                    "left join fetch c.author " +
                                    "left join fetch c.post where c.id = :id",
                            Comment.class)
                    .setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("Comment not found");
		}
	}

	public Comment insert(Comment comment) throws DatabaseException,
			NotFoundException {
		try {
			User author = manager.createQuery("SELECT u " +
					"FROM User u WHERE u.username = :username", User.class)
					.setParameter("username", comment.getAuthor()
							.getUsername()).getSingleResult();
			comment.setAuthor(author);
			manager.persist(comment);
			manager.flush();
			return comment;
		} catch (NoResultException e) {
            throw new NotFoundException("Author not found");
        } catch (PersistenceException e) {
			throw new DatabaseException("Could not insert comment");
		}
	}

	public Comment update(Comment comment) throws NotFoundException,
			DatabaseException {
		try {
			Comment detached = manager.find(Comment.class, comment.getId());
			Comment managed = manager.merge(detached);
			managed.setContent(comment.getContent());
			return managed;
		} catch (NoResultException e) {
            throw new NotFoundException("Comment not found");
        } catch (PersistenceException e) {
			throw new DatabaseException("Could not update comment");
		}
	}

	public Comment remove(Long id) throws NotFoundException, DatabaseException {
		try {
			Comment comment = manager.find(Comment.class, id);
			manager.remove(comment);
			return comment;
		} catch (NoResultException e) {
            throw new NotFoundException("Comment not found");
        } catch (PersistenceException e) {
			throw new DatabaseException("Could not remove comment");
		}
	}

}
