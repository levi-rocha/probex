System.register(['angular2/core', '../model/aluno'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, aluno_1;
    var AlunoService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (aluno_1_1) {
                aluno_1 = aluno_1_1;
            }],
        execute: function() {
            AlunoService = (function () {
                function AlunoService() {
                    this.alunos = [
                        new aluno_1.Aluno('Fulano', 'fulano@email.com'),
                        new aluno_1.Aluno('Beltrano', 'beltrano@email.com')
                    ];
                }
                AlunoService.prototype.listarTodos = function () {
                    return this.alunos;
                };
                AlunoService.prototype.cadastrar = function (aluno) {
                    this.alunos.push(aluno);
                };
                AlunoService.prototype.atualizar = function (id, aluno) {
                    this.alunos[id] = aluno;
                };
                AlunoService.prototype.excluir = function (id) {
                    this.alunos.splice(id, 1);
                };
                AlunoService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [])
                ], AlunoService);
                return AlunoService;
            })();
            exports_1("AlunoService", AlunoService);
        }
    }
});
//# sourceMappingURL=aluno-service.js.map