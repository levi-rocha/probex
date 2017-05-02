package br.unifor.probex.business;

import java.util.Collection;

import javax.ejb.Remote;

import br.unifor.probex.entity.Post;

@Remote
public interface PostBORemote {

	public Collection<Post> listPosts();
	
	public String addPost (Post post);

	public String removePost(Long id);

	public String updatePost(Post post);
}
