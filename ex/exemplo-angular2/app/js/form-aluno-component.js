System.register(['angular2/core', './model/curso', './model/aluno', 'angular2/common', './validators/email-validator', "./service/aluno-service"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, curso_1, aluno_1, common_1, email_validator_1, aluno_service_1;
    var FormAlunoComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (curso_1_1) {
                curso_1 = curso_1_1;
            },
            function (aluno_1_1) {
                aluno_1 = aluno_1_1;
            },
            function (common_1_1) {
                common_1 = common_1_1;
            },
            function (email_validator_1_1) {
                email_validator_1 = email_validator_1_1;
            },
            function (aluno_service_1_1) {
                aluno_service_1 = aluno_service_1_1;
            }],
        execute: function() {
            FormAlunoComponent = (function () {
                function FormAlunoComponent(fb, alunoService) {
                    this.fb = fb;
                    this.alunoService = alunoService;
                    this.sucesso = false;
                    this.aluno = new aluno_1.Aluno();
                    this.cursos = [
                        new curso_1.Curso('angular2', 'Angular 2'),
                        new curso_1.Curso('javaee', 'Java EE')
                    ];
                    this.buildForm(fb);
                }
                FormAlunoComponent.prototype.ngOnInit = function () {
                    this.aluno = new aluno_1.Aluno();
                    this.cursos = [
                        new curso_1.Curso('angular2', 'Angular 2'),
                        new curso_1.Curso('javaee', 'Java EE')
                    ];
                    this.buildForm(this.fb);
                    this.idEditar = -1;
                    this.alunos = this.alunoService.listarTodos();
                };
                FormAlunoComponent.prototype.buildForm = function (fb) {
                    this.alunoForm = fb.group({
                        nome: ['', common_1.Validators.required],
                        email: ['', common_1.Validators.compose([common_1.Validators.required, email_validator_1.EmailValidator.validate])],
                        curso: ['', common_1.Validators.required]
                    });
                };
                FormAlunoComponent.prototype.cadastrar = function () {
                    this.alunoService.cadastrar(this.aluno);
                    this.aluno = new aluno_1.Aluno();
                };
                FormAlunoComponent.prototype.editar = function (id) {
                    this.idEditar = id;
                    this.aluno = new aluno_1.Aluno(this.alunos[id].nome, this.alunos[id].email, this.alunos[id].idade, this.alunos[id].curso);
                };
                FormAlunoComponent.prototype.atualizar = function () {
                    this.alunoService.atualizar(this.idEditar, this.aluno);
                    this.aluno = new aluno_1.Aluno();
                    this.idEditar = -1;
                };
                FormAlunoComponent.prototype.excluir = function (id) {
                    this.alunoService.excluir(id);
                    this.idEditar = -1;
                };
                FormAlunoComponent = __decorate([
                    core_1.Component({
                        selector: 'form-aluno',
                        templateUrl: 'app/view/form-aluno.html',
                        providers: [aluno_service_1.AlunoService]
                    }), 
                    __metadata('design:paramtypes', [common_1.FormBuilder, aluno_service_1.AlunoService])
                ], FormAlunoComponent);
                return FormAlunoComponent;
            })();
            exports_1("FormAlunoComponent", FormAlunoComponent);
        }
    }
});
//# sourceMappingURL=form-aluno-component.js.map