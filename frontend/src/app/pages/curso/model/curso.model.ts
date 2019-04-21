export class ICurso {
    idCurso: string;
    nome: string;
    codigoCurso: string;
    anoSemestre: string;

    /**
     * Constructor
     *
     * @param product
     */
    constructor(curso?) {
        curso = curso || {};
        this.idCurso = curso.idCurso || curso._id || undefined;
        this.nome = curso.nome || undefined;
        this.codigoCurso = curso.codigoCurso || undefined;
        this.anoSemestre = curso.anoSemestre || undefined;
    }
}
