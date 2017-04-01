import { Component } from '@angular/core';
import { Aluno } from '../.././models/aluno';
import { AlunoService } from '../.././services/aluno-service';
import { OnInit } from '@angular/core';
import { Router, ROUTER_DIRECTIVES } from '@angular/router'; 

@Component({
	selector: 'aluno-cadastrar',
	templateUrl: 'app/views/alunos/cadastrar.html',
	providers: [ AlunoService ],
	directives: [ ROUTER_DIRECTIVES ]
})
export class AlunoCadastrarComponent implements OnInit {

	private aluno: Aluno;

	constructor(private router: Router, private alunoService: AlunoService) {
	}

	ngOnInit() {
		this.aluno = new Aluno();
	}

	cadastrar() {
		this.alunoService.cadastrar(this.aluno);
		this.router.navigate(['/aluno-listar']);
	}
}