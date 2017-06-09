package br.unifor.probex.business;

import br.unifor.probex.dto.SolutionDTO;
import br.unifor.probex.entity.Solution;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.InvalidSolutionException;
import br.unifor.probex.exception.NotFoundException;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface SolutionBORemote {

    List<SolutionDTO> listSolutions(int quantity);

    SolutionDTO findSolutionById(Long id) throws NotFoundException;

    SolutionDTO addSolution(Solution solution) throws DatabaseException, InvalidSolutionException;

    SolutionDTO removeSolution(Long id) throws DatabaseException,
            NotFoundException;

    SolutionDTO updateSolution(Solution solution) throws NotFoundException,
            DatabaseException, InvalidSolutionException;

}
