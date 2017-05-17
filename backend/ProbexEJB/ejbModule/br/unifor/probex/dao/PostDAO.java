package br.unifor.probex.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import br.unifor.probex.entity.Post;

@Stateless
public class PostDAO {

	@PersistenceContext
	private EntityManager manager;

	public String insert(Post post) {
		try {
			manager.persist(post);
			return post.getTitle() + " inserted";
		} catch (PersistenceException e) {
			return "could not insert data " + e;
		}
	}

	public List<Post> list() {
		List<Post> posts = manager
				.createQuery("SELECT p FROM Post p LEFT JOIN FETCH p.author LEFT JOIN FETCH p.votes", Post.class)
				.getResultList();
		return posts;
	}

	public Post findById(Long id) {
		Post post = manager.createQuery(
				"select p from Post p left join fetch p.author left join fetch p.votes left join fetch p.comments where p.id = :id",
				Post.class).setParameter("id", id).getSingleResult();
		return post;
	}

	public List<Post> searchKeywords(List<String> keywords) {
		if (keywords.size() <= 0)
			return null;
		List<Post> results = new ArrayList<Post>();
		List<Post> searchResults = manager.createQuery(
				"select p from Post p left join fetch p.author left join fetch p.votes where lower(p.content) like lower(:keyword)",
				Post.class).setParameter("keyword", keywords.get(0)).getResultList();
		keywords.remove(0);
		for (Post p : searchResults) {
			boolean hit = true;
			for (String key : keywords) {
				if (!p.getContent().toLowerCase().contains(key.toLowerCase())) {
					hit = false;
					break;
				}
			}
			if (hit)
				results.add(p);
		}
		return results;
	}

	public String update(Post post) {
		try {
			Post detached = manager.find(Post.class, post.getId());

			if (detached == null)
				return "no post found with id " + post.getId();

			Post managed = manager.merge(detached);

			managed.setTitle(post.getTitle());
			managed.setContent(post.getContent());
			managed.setComments(post.getComments());
			managed.setVotes(post.getVotes());

			return post.getTitle() + " updated";
		} catch (PersistenceException e) {
			return "could not update data " + e;
		}
	}

	public String remove(Long id) {
		try {
			Post post = manager.find(Post.class, id);
			manager.remove(post);
			return post.getTitle() + " removed";
		} catch (PersistenceException e) {
			return "could not remove data " + e;
		}
	}

}
