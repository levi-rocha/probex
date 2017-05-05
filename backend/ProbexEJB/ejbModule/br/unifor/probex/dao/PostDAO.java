package br.unifor.probex.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import br.unifor.probex.dto.PostDTO;
import br.unifor.probex.entity.Post;
import br.unifor.probex.entity.User;

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

	public Collection<PostDTO> list() {
		Collection<Post> posts = manager
				.createQuery("SELECT p FROM Post p LEFT JOIN FETCH p.author LEFT JOIN FETCH p.votes", Post.class).getResultList();
		Collection<PostDTO> postDTOs = new HashSet<PostDTO>();
		for (Post p : posts) {
			PostDTO dto = new PostDTO();
			dto.setId(p.getId());
			dto.setTitle(p.getTitle());
			dto.setContent(p.getContent());
			//TODO null check for author-less posts: handle as exception instead
			if (p.getAuthor() != null) {
				dto.setAuthorId(p.getAuthor().getId());
				dto.setAuthorUsername(p.getAuthor().getUsername());
			}
			Set<Long> voteIds = new HashSet<Long>();
			for (User v : p.getVotes()) {
				voteIds.add(v.getId());
			}
			dto.setVoteIds(voteIds);
			postDTOs.add(dto);
		}
		return postDTOs;
	}

	public Post findById(Long id) {
		Post post = manager.createQuery(
				"select p from Post p left join fetch p.author left join fetch p.votes left join fetch p.comments where p.id = :id",
				Post.class).setParameter("id", id).getSingleResult();
		return post;
	}

	public String update(Post post) {
		try {
			Post detached = manager.find(Post.class, post.getId());

			if (detached == null)
				return "no post found with id " + post.getId();

			Post managed = manager.merge(detached);

			managed.setTitle(post.getTitle());
			managed.setContent(post.getContent());
			// TODO: votes, comments

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
