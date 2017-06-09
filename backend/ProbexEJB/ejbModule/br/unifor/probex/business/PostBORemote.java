package br.unifor.probex.business;

import java.util.List;

import javax.ejb.Remote;

import br.unifor.probex.dto.PostDetailedDTO;
import br.unifor.probex.dto.PostSimpleDTO;
import br.unifor.probex.dto.VoteDTO;
import br.unifor.probex.entity.Post;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.InvalidPostException;
import br.unifor.probex.exception.InvalidVoteException;
import br.unifor.probex.exception.NotFoundException;

@Remote
public interface PostBORemote {

	List<PostSimpleDTO> listPosts(int quantity, int start, String criteria,
								  String keywords);

	PostDetailedDTO addPost(Post post) throws InvalidPostException, NotFoundException;

	PostDetailedDTO findPostById(Long id) throws NotFoundException;

	PostDetailedDTO removePost(Long id) throws NotFoundException, DatabaseException;

	PostDetailedDTO updatePost(Post post) throws NotFoundException, DatabaseException, InvalidPostException;

	PostDetailedDTO voteOnPost(VoteDTO vote) throws DatabaseException, NotFoundException, InvalidVoteException;
}
