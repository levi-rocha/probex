package br.unifor.restful.resources;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.unifor.entity.Aluno;

@Stateless
@Path("/alunos")
public class AlunoResource {

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

	@GET
	@Produces("application/json")
	public Collection<Aluno> listaAlunos() {
		return this.listaAlunos;
	}

	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public String adicionaAluno(Aluno aluno) {

		aluno.setId((long) this.listaAlunos.size());

		this.listaAlunos.add(aluno);

		return "Aluno cadastrado";
	}

	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public String alteraAluno(Aluno aluno) {
		Aluno alunoEncontrado = this.buscaAlunoPorId(aluno.getId());
		alunoEncontrado.setNome(aluno.getNome());
		alunoEncontrado.setEmail(aluno.getEmail());
		alunoEncontrado.setIdade(aluno.getIdade());
		alunoEncontrado.setCurso(aluno.getCurso());

		return "Aluno alterado";
	}

	@Path("{id}")
	@DELETE
	@Produces("text/plain")
	public String removeAluno(@PathParam("id") Long id) {

		if (listaAlunos.remove(buscaAlunoPorId(id)))
			return "Aluno removido";
		else
			return "Erro ao remover aluno";
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