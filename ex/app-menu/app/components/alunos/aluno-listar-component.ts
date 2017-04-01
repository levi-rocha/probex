import { Component } from '@angular/core';
import { Aluno } from '../.././models/aluno';
import { AlunoService } from '../.././services/aluno-service';
import { OnInit } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';

@Component({
	selector: 'aluno-listar',
	templateUrl: 'app/views/alunos/listar.html',
	providers: [ AlunoService ],
	directives: [ ROUTER_DIRECTIVES ]
})
export class AlunoListarComponent implements OnInit {

	private alunos: Aluno[];

	constructor(private alunoService: AlunoService) {
	}

	ngOnInit() {
		this.alunos = this.alunoService.listarTodos();
	}

	excluir(id: number) {
		this.alunoService.excluir(id);
		this.alunos = this.alunoService.listarTodos();
	}
}