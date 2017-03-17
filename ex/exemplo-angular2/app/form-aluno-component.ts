import {Component, OnInit} from 'angular2/core';
import {Curso} from './model/curso';
import {Aluno} from './model/aluno';
import {ControlGroup, FormBuilder, Validators, AbstractControl} from 'angular2/common';
import {EmailValidator} from './validators/email-validator';
import {AlunoService} from "./service/aluno-service";

@Component({
    selector: 'form-aluno',
    templateUrl: 'app/view/form-aluno.html',
    providers: [AlunoService]
})
export class FormAlunoComponent implements OnInit{

    sucesso: boolean = false;

    cursos: Curso[];
    aluno: Aluno;  

    alunoForm: ControlGroup;

    idEditar: number;
    alunos: Aluno[];

    constructor(private fb: FormBuilder, private alunoService: AlunoService) {
          this.aluno = new Aluno();
          this.cursos = [
               new Curso('angular2', 'Angular 2'),
               new Curso('javaee', 'Java EE')
          ];    
          this.buildForm(fb);
     }

    ngOnInit() {
        this.aluno = new Aluno();
        this.cursos = [
            new Curso('angular2', 'Angular 2'),
            new Curso('javaee', 'Java EE')
        ];
        this.buildForm(this.fb);

        this.idEditar = -1;
        this.alunos = this.alunoService.listarTodos();
    }

    buildForm(fb: FormBuilder): void {
        this.alunoForm = fb.group({
            nome:['',Validators.required],
            email: ['',Validators.compose([Validators.required, EmailValidator.validate])],
            curso: ['', Validators.required]
        });
    }

    cadastrar() {
        this.alunoService.cadastrar(this.aluno);
        this.aluno = new Aluno();        
    }

    editar(id: number) {
        this.idEditar = id;
        this.aluno = new Aluno(this.alunos[id].nome, this.alunos[id].email, this.alunos[id].idade, this.alunos[id].curso);
    }

    atualizar() {
        this.alunoService.atualizar(this.idEditar, this.aluno);
        this.aluno = new Aluno();
        this.idEditar = -1;
    }

    excluir(id: number) {
        this.alunoService.excluir(id);
        this.idEditar = -1;
    }

}