package br.unifor.probex.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;

import br.unifor.probex.entity.Post;
import br.unifor.probex.entity.User;

@Stateless
public class PostDAO {

	public static final String LATEST = " order by p.date desc";
	public static final String POPULAR = " group by p, a order by count(v) desc)";

	@PersistenceContext
	private EntityManager manager;

	public String insert(Post post) {
		try {
			post.setAuthor((User) manager.createQuery("SELECT u FROM User u WHERE u.username = :username")
					.setParameter("username", post.getAuthor().getUsername())
					.getSingleResult());
			manager.persist(post);
			return post.getTitle() + " inserted";
		} catch (PersistenceException e) {
			return "could not insert data " + e;
		}
	}

	public List<Post> list(String orderBy, int quantity) {
		String query = null;
		if (orderBy == null) {
			query = "SELECT p FROM Post p LEFT JOIN FETCH p.author LEFT JOIN FETCH p.votes";
		} else if (POPULAR.equals(orderBy)) {
			// caso especial onde o HQL e complicado
			List<Long> pids = new ArrayList<Long>();
			// subquery pega os ids dos posts com mais votos, na ordem, com
			// quantidade maxima
			String subquery = "select distinct p.id, count(v.id) from Post p left join p.votes v group by p.id order by count(v.id) desc";
			List<Object[]> list;
			if (quantity > 0) {
				list = manager.createQuery(subquery).setMaxResults(quantity).getResultList();
			} else {
				list = manager.createQuery(subquery).getResultList();
			}
			for (Object[] ob : list) {
				Long id = (Long) ob[0];
				Long count = (Long) ob[1];
				System.out.println("post id = " + id + " | count = " + count);
				pids.add(id);
			}
			// query pega os posts cujos ips foram pegos pelo subquery. porem, o
			// distinct desfaz a ordem
			query = "select distinct p from Post p left join fetch p.author left join fetch p.votes where p.id in :pids";
			List<Post> data = manager.createQuery(query, Post.class).setParameter("pids", pids).getResultList();
			// posts adicionados a um map com seus ids para facilitar
			// localizacao
			HashMap<Long, Post> dataMap = new HashMap<Long, Post>();
			for (Post p : data) {
				dataMap.put(p.getId(), p);
			}
			// lista final de posts preenchida na ordem com os posts do map
			List<Post> posts = new ArrayList<Post>();
			for (Long id : pids) {
				posts.add(dataMap.get(id));
			}
			return posts;
		} else {
			query = "SELECT p FROM Post p LEFT JOIN FETCH p.author a LEFT JOIN FETCH p.votes v" + orderBy;
		}
		if (quantity > 0) {
			return manager.createQuery(query, Post.class).setMaxResults(quantity).getResultList();
		} else {
			return manager.createQuery(query, Post.class).getResultList();
		}

	}

	public Post findById(Long id) {
		Post post = manager.createQuery(
				"select p from Post p left join fetch p.author left join fetch p.votes left join fetch p.comments where p.id = :id",
				Post.class).setParameter("id", id).getSingleResult();
		return post;
	}

	public List<Post> searchKeywords(List<String> keywords, String orderBy, int quantity) {
		List<Post> results = new ArrayList<Post>();
		boolean sortPopular = false;
		for (String keyword : keywords) {
			String query = null;
			if (orderBy == null) {
				query = "select distinct p from Post p left join fetch p.author left join fetch p.votes where lower(p.content) like lower(:keyword)";
			} else if (POPULAR.equals(orderBy)) {
				sortPopular = true;
				query = "select distinct p from Post p left join fetch p.author left join fetch p.votes where lower(p.content) like lower(:keyword)";
			} else {
				query = "select distinct p from Post p left join fetch p.author left join fetch p.votes where lower(p.content) like lower(:keyword)"
						+ orderBy;
			}
			List<Post> searchResults;
			if (quantity > 0 && !sortPopular) {
				searchResults = manager.createQuery(query, Post.class).setParameter("keyword", "%" + keyword + "%")
						.setMaxResults(quantity).getResultList();
			} else {
				searchResults = manager.createQuery(query, Post.class).setParameter("keyword", "%" + keyword + "%")
						.getResultList();
			}
			for (Post p : searchResults) {
				boolean hit = true;
				for (String key : keywords) {
					if (!p.getContent().toLowerCase().contains(key.toLowerCase())) {
						hit = false;
						break;
					}
				}
				if (hit && !results.contains(p))
					results.add(p);
			}
		}
		if (sortPopular) {
			List<Long> ids = new ArrayList<Long>();
			for (Post p : results) {
				ids.add(p.getId());
			}
			// caso especial onde o HQL e complicado
			List<Long> pids = new ArrayList<Long>();
			// subquery pega os ids dos posts com mais votos, na ordem, com
			// quantidade maxima
			String subquery = "select distinct p.id, count(v.id) from Post p left join p.votes v where p.id in :ids group by p.id order by count(v.id) desc";
			List<Object[]> list;
			if (quantity > 0) {
				list = manager.createQuery(subquery).setMaxResults(quantity).setParameter("ids", ids).getResultList();
			} else {
				list = manager.createQuery(subquery).setParameter("ids", ids).getResultList();
			}
			for (Object[] ob : list) {
				Long id = (Long) ob[0];
				Long count = (Long) ob[1];
				System.out.println("post id = " + id + " | count = " + count);
				pids.add(id);
			}
			// query pega os posts cujos ips foram pegos pelo subquery. porem, o
			// distinct desfaz a ordem
			String query = "select distinct p from Post p left join fetch p.author left join fetch p.votes where p.id in :pids";
			List<Post> data = manager.createQuery(query, Post.class).setParameter("pids", pids).getResultList();
			// posts adicionados a um map com seus ids para facilitar
			// localizacao
			HashMap<Long, Post> dataMap = new HashMap<Long, Post>();
			for (Post p : data) {
				dataMap.put(p.getId(), p);
			}
			// lista final de posts preenchida na ordem com os posts do map
			List<Post> posts = new ArrayList<Post>();
			for (Long id : pids) {
				posts.add(dataMap.get(id));
			}
			return posts;
		} else {
			return results;
		}
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
