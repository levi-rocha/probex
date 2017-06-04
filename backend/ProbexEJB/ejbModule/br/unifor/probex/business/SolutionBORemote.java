package br.unifor.probex.business;

import br.unifor.probex.entity.Solution;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface SolutionBORemote {

    public List<Solution> listSolutions();

    public List<Solution> listSolutions(int quantity);

    public Solution findSolutionById(Long id);

    public String addSolution(Solution solution);

    public String removeSolution(Long id);

    public String updateSolution(Solution solution);

}
