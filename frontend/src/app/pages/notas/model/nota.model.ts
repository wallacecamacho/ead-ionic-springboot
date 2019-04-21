export class INota {
    idNota: string;
    nota: string;
    curso: {
        idCurso: number;
    };
    avaliacao: {
        idAvaliacao: number;
    };
    aluno: {
        idAluno: string;
        matricula: {
            idMatricula: number;
        };
    };

    /**
     * Constructor
     *
     * @param product
     */
    constructor(nota?) {
        nota = nota || {};
        this.avaliacao = nota.avaliacao || { idAvaliacao: undefined };
        this.curso = nota.curso || { idCurso: undefined };
        this.aluno = nota.aluno || { idAluno: undefined, matricula: { idMatricula: undefined } };
        this.idNota = nota.idNota || nota._id || undefined;
        this.nota = nota.nota || undefined;

    }
}
