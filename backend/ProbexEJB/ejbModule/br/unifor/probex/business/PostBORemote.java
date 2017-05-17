package br.unifor.probex.business;

import java.util.List;

import javax.ejb.Remote;

import br.unifor.probex.entity.Post;

@Remote
public interface PostBORemote {

	public List<Post> listPosts();

	public List<Post> searchKeywords(List<String> keywords);

	public String addPost(Post post);

	public Post findPostById(Long id);

	public String removePost(Long id);

	public String updatePost(Post post);
}
