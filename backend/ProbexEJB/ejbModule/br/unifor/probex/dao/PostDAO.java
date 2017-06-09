package br.unifor.probex.dao;

import br.unifor.probex.dto.VoteDTO;
import br.unifor.probex.entity.Post;
import br.unifor.probex.entity.User;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.InvalidVoteException;
import br.unifor.probex.exception.NotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Stateless
public class PostDAO {

    public static final String LATEST = " order by p.date desc";
    public static final String POPULAR = "popular";

    @PersistenceContext
    private EntityManager manager;
    public Post insert(Post post) throws NotFoundException {
        try {
            post.setAuthor((User) manager.createQuery("SELECT u FROM User u " +
                    "WHERE u.username = :username")
                    .setParameter("username", post.getAuthor().getUsername())
                    .getSingleResult());
            manager.persist(post);
            manager.flush();
            return post;
        } catch (NoResultException e) {
            throw new NotFoundException("Author not found");
        }
    }

    public Post findById(Long id) throws NotFoundException {
        try {
            return manager.createQuery(
                    "select p from Post p left join fetch p.author " +
                            "left join fetch p.votes left join fetch p.comments " +
                            "where p.id = :id", Post.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundException("Post not found");
        }
    }

    private List<Post> getMostPopular(List<Long> ids, int quantity, int start) {
        String query;
        if (ids != null) {
            query = "select distinct p.id, count(v.id) from Post p " +
                    "left join p.votes v where p.id in :ids group by p.id " +
                    "order by count(v.id) desc";
        } else {
            query = "select distinct p.id, count(v.id) from Post p " +
                    "left join p.votes v group by p.id " +
                    "order by count(v.id) desc";
        }
        List<Object[]> list = manager.createQuery(query)
                .setFirstResult(start).setMaxResults(quantity).getResultList();
        List<Long> pids = new ArrayList<>();
        for (Object[] ob : list) {
            Long id = (Long) ob[0];
            pids.add(id);
        }
        // query pega os posts cujos ips foram pegos pelo subquery. porem, o
        // distinct desfaz a ordem
        query = "select distinct p from Post p left join fetch p.author " +
                "left join fetch p.votes where p.id in :pids";
        List<Post> data = manager.createQuery(query, Post.class)
                .setParameter("pids", pids).getResultList();
        // posts adicionados a um map com seus ids para facilitar
        // localizacao
        HashMap<Long, Post> dataMap = new HashMap<>();
        for (Post p : data) {
            dataMap.put(p.getId(), p);
        }
        // lista final de posts preenchida na ordem com os posts do map
        List<Post> posts = new ArrayList<>();
        for (Long id : pids) {
            posts.add(dataMap.get(id));
        }
        return posts;
    }

    public List<Post> list(List<String> keywords, String orderBy, int quantity,
                           int start) {
        if (start < 0)
            start = 0;
        if (quantity <= 0 || quantity > 100)
            quantity = 100;
        boolean sortPopular = false;
        if (POPULAR.equals(orderBy))
            sortPopular = true;
        if (keywords != null && keywords.size() > 0) {
            List<Post> results = new ArrayList<>();
            String keyword = keywords.get(0);
            String query = "select distinct p from Post p " +
                    "left join fetch p.author left join fetch p.votes " +
                    "where lower(p.content) like lower(:keyword)" + LATEST;
            List<Post> searchResults = manager
                    .createQuery(query, Post.class)
                    .setParameter("keyword", "%" + keyword + "%")
                    .getResultList();
            for (Post p : searchResults) {
                boolean hit = true;
                for (String key : keywords) {
                    if (!p.getContent().toLowerCase()
                            .contains(key.toLowerCase())) {
                        hit = false;
                        break;
                    }
                }
                if (hit && !results.contains(p))
                    results.add(p);
            }
            List<Post> hits = new ArrayList<>();
            for (int i = start; i < results.size() && i < quantity+start; i++) {
                hits.add(results.get(i));
            }
            if (sortPopular) {
                List<Long> ids = new ArrayList<>();
                for (Post p : hits) {
                    ids.add(p.getId());
                }
                return getMostPopular(ids, quantity, start);
            } else {
                return hits;
            }
        } else {
            if (sortPopular) {
                return getMostPopular(null, quantity, start);
            } else {
                String query = "select distinct p from Post p " +
                        "left join fetch p.author left join fetch p.votes" +
                        LATEST;
                return manager.createQuery(query, Post.class)
                        .setFirstResult(start).setMaxResults(quantity)
                        .getResultList();
            }
        }
    }

    public Post update(Post post) throws NotFoundException, DatabaseException {
        try {
            Post detached = manager.find(Post.class, post.getId());
            Post managed = manager.merge(detached);
            managed.setTitle(post.getTitle());
            managed.setContent(post.getContent());
            return managed;
        } catch (NoResultException e) {
            throw new NotFoundException("Post not found");
        } catch (PersistenceException e) {
            throw new DatabaseException("Could not update post");
        }
    }

    public Post voteOnPost(VoteDTO vote) throws NotFoundException,
            InvalidVoteException, DatabaseException {
        Post detached;
        try {
            detached = manager.find(Post.class, vote.getPostId());
        } catch (NoResultException e) {
            throw new NotFoundException("Post not found");
        }
        User user;
        try {
            user = manager.createQuery(
                    "SELECT a FROM User a LEFT JOIN FETCH a.permission " +
                            "LEFT JOIN FETCH a.posts LEFT JOIN FETCH " +
                            "a.comments WHERE a.username = :username",
                    User.class).setParameter("username", vote.getUsername())
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundException("User not found");
        }
        try {
            Post managed = manager.merge(detached);
            Set<User> votes = managed.getVotes();
            if (votes.contains(user))
                throw new InvalidVoteException(vote.getUsername() +
                        " has already voted on post " + vote.getPostId());
            votes.add(user);
            managed.setVotes(votes);
            return managed;
        } catch (PersistenceException e) {
            throw new DatabaseException("Could not insert vote");
        }
    }

    public Post remove(Long id) throws NotFoundException, DatabaseException {
        try {
            Post post = manager.find(Post.class, id);
            manager.remove(post);
            manager.flush();
            return post;
        } catch (NoResultException e) {
            throw new NotFoundException("Post not found");
        } catch (PersistenceException e) {
            throw new DatabaseException("Could not remove post");
        }
    }

}
