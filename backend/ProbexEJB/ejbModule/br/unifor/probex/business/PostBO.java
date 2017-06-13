package br.unifor.probex.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.unifor.probex.dao.PostDAO;
import br.unifor.probex.dto.PostDetailedDTO;
import br.unifor.probex.dto.PostSimpleDTO;
import br.unifor.probex.dto.VoteDTO;
import br.unifor.probex.entity.Post;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.InvalidPostException;
import br.unifor.probex.exception.InvalidVoteException;
import br.unifor.probex.exception.NotFoundException;

@Stateless
public class PostBO implements PostBORemote {

	@EJB
	private PostDAO postDAO;

	public PostBO() {

	}

	@Override
	public List<PostSimpleDTO> listPosts(int quantity, int start,
										 String criteria, String keywords) {
		String orderBy = PostDAO.LATEST;
		if ("popular".equals(criteria))
			orderBy = PostDAO.POPULAR;
		List<String> keywordList = new ArrayList<>();
		if (keywords != null && !"".equals(keywords)) {
			String[] keys = keywords.split(",");
            Collections.addAll(keywordList, keys);
		}
		List<PostSimpleDTO> dtos = new ArrayList<>();
		for (Post p : postDAO.list(keywordList, orderBy, quantity, start)) {
			dtos.add(PostSimpleDTO.fromPost(p));
		}
		return dtos;
	}

	@Override
	public PostDetailedDTO findPostById(Long id) throws NotFoundException {
        return PostDetailedDTO.fromPost(this.postDAO.findById(id));
	}

	@Override
	public PostDetailedDTO addPost(Post post) throws InvalidPostException,
            NotFoundException {
        if (post.getTitle() == null || post.getTitle().length() < 5)
            throw new InvalidPostException("Post title must be longer than " +
                    "5 characters");
	    if (post.getContent() == null || post.getContent().length() < 5)
	        throw new InvalidPostException("Post content must be longer than " +
                    "5 characters");
		return PostDetailedDTO.fromPost(this.postDAO.insert(post));
	}

	@Override
	public PostDetailedDTO removePost(Long id) throws NotFoundException,
			DatabaseException {
		return PostDetailedDTO.fromPost(this.postDAO.remove(id));
	}

	@Override
	public PostDetailedDTO updatePost(Post post) throws NotFoundException,
            DatabaseException, InvalidPostException {
        if (post.getTitle() == null || post.getTitle().length() < 5)
            throw new InvalidPostException("Post title must be longer than " +
                    "5 characters");
        if (post.getContent() == null || post.getContent().length() < 5)
            throw new InvalidPostException("Post content must be longer than " +
                    "5 characters");
		return PostDetailedDTO.fromPost(this.postDAO.update(post));
	}

	@Override
	public PostDetailedDTO voteOnPost(VoteDTO vote) throws DatabaseException,
			NotFoundException, InvalidVoteException {
		return PostDetailedDTO.fromPost(this.postDAO.voteOnPost(vote));
	}

}