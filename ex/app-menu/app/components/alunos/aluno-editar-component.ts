import { Component } from '@angular/core';
import { Aluno } from '../.././models/aluno';
import { AlunoService } from '../.././services/aluno-service';
import { OnInit } from '@angular/core';
import { Router, ActivatedRoute, ROUTER_DIRECTIVES } from '@angular/router'; 

@Component({
	selector: 'aluno-editar',
	templateUrl: 'app/views/alunos/editar.html',
	providers: [ AlunoService ],
	directives: [ ROUTER_DIRECTIVES ]
})
export class AlunoEditarComponent implements OnInit {

	private id: number;
	private aluno: Aluno;

	constructor(
		private route: ActivatedRoute, 
		private router: Router, 
		private alunoService: AlunoService) {
	}

	ngOnInit() {
		this.id = +this.route.snapshot.params['id'];
		this.aluno = this.alunoService.buscarPorId(this.id);
	}

	atualizar() {
		this.alunoService.atualizar(this.id, this.aluno);
		this.router.navigate(['/aluno-listar']);
	}
}