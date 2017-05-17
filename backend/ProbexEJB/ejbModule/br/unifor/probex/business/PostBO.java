package br.unifor.probex.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.unifor.probex.dao.PostDAO;
import br.unifor.probex.entity.Post;

@Stateless
public class PostBO implements PostBORemote {

	@EJB
	private PostDAO postDAO;

	public PostBO() {

	}

	@Override
	public List<Post> listPosts() {
		return this.postDAO.list();
	}

	@Override
	public List<Post> searchKeywords(List<String> keywords) {
		return this.postDAO.searchKeywords(keywords);
	}

	@Override
	public Post findPostById(Long id) {
		return this.postDAO.findById(id);
	}

	@Override
	public String addPost(Post post) {
		return this.postDAO.insert(post);
	}

	@Override
	public String removePost(Long id) {
		return this.postDAO.remove(id);
	}

	@Override
	public String updatePost(Post post) {
		return this.postDAO.update(post);
	}
}