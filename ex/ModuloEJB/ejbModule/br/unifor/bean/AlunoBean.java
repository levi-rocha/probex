package br.unifor.bean;

import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import br.unifor.entity.Aluno;

/**
 * Session Bean implementation class AlunoBean
 */
@Stateless
public class AlunoBean implements AlunoBeanRemote {

	private static ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();

	static {
		Aluno a1 = new Aluno();
		a1.setId((long) listaAlunos.size());
		a1.setNome("Maria");
		a1.setEmail("maria@mail.com");
		a1.setCurso("Angular 2");
		listaAlunos.add(a1);

		Aluno a2 = new Aluno();
		a2.setId((long) listaAlunos.size());
		a2.setNome("Pedro");
		a2.setEmail("pedro@mail.com");
		a2.setCurso("Java EE");
		listaAlunos.add(a2);

	}

	
	public AlunoBean() {
		
	}

	@Override
	public Collection<Aluno> listaAlunos() {

		return this.listaAlunos;
	}

	@Override
	public String adicionaAluno(Aluno aluno) {

		aluno.setId((long) this.listaAlunos.size());

		if (this.listaAlunos.add(aluno))
			return "Aluno cadastrado";
		else
			return "Erro ao cadastrar aluno";
	}

	@Override
	public String removeAluno(Long id) {

		if (listaAlunos.remove(buscaAlunoPorId(id)))
			return "Aluno removido";
		else
			return "Erro ao remover aluno";
	}

	@Override
	public String alteraAluno(Aluno aluno) {

		Aluno alunoEncontrado = this.buscaAlunoPorId(aluno.getId());

		if (alunoEncontrado != null) {
			alunoEncontrado.setNome(aluno.getNome());
			alunoEncontrado.setEmail(aluno.getEmail());
			alunoEncontrado.setIdade(aluno.getIdade());
			alunoEncontrado.setCurso(aluno.getCurso());
			
			return "Aluno atualizado";
		} else {
			return "Erro ao atualizar aluno";
		}
	}

	private Aluno buscaAlunoPorId(Long id) {

		Aluno alunoEncontrado = null;
		for (Aluno aluno : this.listaAlunos) {
			if (aluno.getId().equals(id))
				alunoEncontrado = aluno;
		}

		return alunoEncontrado;
	}
}