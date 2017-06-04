package br.unifor.probex.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.unifor.probex.dao.PostDAO;
import br.unifor.probex.dto.PostDetailedDTO;
import br.unifor.probex.dto.PostSimpleDTO;
import br.unifor.probex.dto.VoteDTO;
import br.unifor.probex.entity.Post;

@Stateless
public class PostBO implements PostBORemote {

	@EJB
	private PostDAO postDAO;

	public PostBO() {

	}

	@Override
	public List<Post> listPosts(int quantity, int start, String criteria,
								String keywords) {
		//TODO
		String orderBy = null;
		if (criteria != null && criteria.equals("popular")) {
			orderBy = PostDAO.POPULAR;
		} else {
			orderBy = PostDAO.LATEST;
		}
		List<Post> data;
		if (keywords != null && !"".equals(keywords)) {
			String[] keys = keywords.split(",");
			List<String> keyList = new ArrayList<String>();
			for (String key : keys) {
				keyList.add(key);
				System.out.println("adding key: " + key);
			}
			if (quantity > 0) {
				data = postBO.searchKeywords(keyList, orderBy, quantity +
						start);
			} else {
				data = postBO.searchKeywords(keyList, orderBy);
			}
		} else {
			if (quantity > 0) {
				data = postBO.listPosts(orderBy, quantity + start);
			} else {
				data = postBO.listPosts(orderBy);
			}
		}

		if (quantity > 0) {
			List<Post> temp = new ArrayList<Post>();
			for (int i = start; i < start + quantity; i++) {
				if (data.size() < i + 1) {
					break;
				}
				temp.add(data.get(i));
			}
			data = temp;
		}
		List<PostSimpleDTO> dtos = new ArrayList<PostSimpleDTO>();
		for (Post p : data) {
			dtos.add(PostSimpleDTO.fromPost(p));
		}
		return dtos;
	}

	@Override
	public List<Post> searchKeywords(List<String> keywords, String orderBy, int quantity) {
		return this.postDAO.searchKeywords(keywords, orderBy, quantity);
	}

	@Override
	public Post findPostById(Long id) {
		Post post = this.postDAO.findById(id);
		PostDetailedDTO dto = PostDetailedDTO.fromPost(post);
		return dto;
	}

	@Override
	public Post addPost(Post post) {
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

	@Override
	public String voteOnPost(VoteDTO vote) {
		return this.postDAO.voteOnPost(vote);
	}

}