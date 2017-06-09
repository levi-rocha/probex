package br.unifor.probex.business;

import br.unifor.probex.dao.SolutionDAO;
import br.unifor.probex.dto.SolutionDTO;
import br.unifor.probex.entity.Solution;
import br.unifor.probex.exception.DatabaseException;
import br.unifor.probex.exception.InvalidSolutionException;
import br.unifor.probex.exception.NotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SolutionBO implements SolutionBORemote {

    @EJB
    private SolutionDAO solutionDAO;

    public SolutionBO() {

    }

    @Override
    public List<SolutionDTO> listSolutions(int quantity) {
        List<SolutionDTO> dtos = new ArrayList<>();
        for (Solution s : solutionDAO.list(quantity)) {
            dtos.add(SolutionDTO.fromSolution(s));
        }
        return dtos;
    }

    @Override
    public SolutionDTO findSolutionById(Long id) throws NotFoundException {
        return SolutionDTO.fromSolution(solutionDAO.findById(id));
    }

    @Override
    public SolutionDTO addSolution(Solution solution) throws DatabaseException,
            InvalidSolutionException {
        if (solution.getContent() == null || solution.getContent().length() < 5)
            throw new InvalidSolutionException("Solution content must be " +
                    "longer than 5");
        return SolutionDTO.fromSolution(solutionDAO.insert(solution));
    }

    @Override
    public SolutionDTO removeSolution(Long id) throws DatabaseException,
            NotFoundException {
        return SolutionDTO.fromSolution(solutionDAO.remove(id));
    }

    @Override
    public SolutionDTO updateSolution(Solution solution) throws
            NotFoundException, DatabaseException, InvalidSolutionException {
        if (solution.getContent() == null || solution.getContent().length() < 5)
            throw new InvalidSolutionException("Solution content must be " +
                    "longer than 5");
        return SolutionDTO.fromSolution(solutionDAO.update(solution));
    }
}
