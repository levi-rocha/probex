System.register([], function(exports_1) {
    var Aluno;
    return {
        setters:[],
        execute: function() {
            Aluno = (function () {
                function Aluno(nome, email, idade, curso) {
                    this.nome = nome;
                    this.email = email;
                    this.idade = idade;
                    this.curso = curso;
                }
                return Aluno;
            })();
            exports_1("Aluno", Aluno);
        }
    }
});
//# sourceMappingURL=aluno.js.map