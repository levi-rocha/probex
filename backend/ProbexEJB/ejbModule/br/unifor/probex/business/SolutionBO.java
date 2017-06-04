package br.unifor.probex.business;

import br.unifor.probex.dao.SolutionDAO;
import br.unifor.probex.entity.Solution;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class SolutionBO implements SolutionBORemote {

    @EJB
    SolutionDAO solutionDAO;

    public SolutionBO() {

    }

    @Override
    public List<Solution> listSolutions() {
        return solutionDAO.list(0);
    }

    @Override
    public List<Solution> listSolutions(int quantity) {
        return solutionDAO.list(quantity);
    }

    @Override
    public Solution findSolutionById(Long id) {
        return solutionDAO.findById(id);
    }

    @Override
    public String addSolution(Solution solution) {
        return solutionDAO.insert(solution);
    }

    @Override
    public String removeSolution(Long id) {
        return solutionDAO.remove(id);
    }

    @Override
    public String updateSolution(Solution solution) {
        return solutionDAO.update(solution);
    }
}
