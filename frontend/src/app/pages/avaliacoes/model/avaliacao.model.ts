export class IAvaliacao {
    idAvaliacao: string;
    nomeAvaliacao: string;
    dataAvaliacao: string;
    curso: {
        idCurso: string;
    };

    /**
     * Constructor
     *
     * @param product
     */
    constructor(avaliacao?) {
        avaliacao = avaliacao || {};
        this.curso = avaliacao.curso || { idCurso: undefined };
        this.idAvaliacao = avaliacao.idavaliacao || avaliacao._id || undefined;
        this.nomeAvaliacao = avaliacao.nomeAvaliacao || undefined;
        this.dataAvaliacao = avaliacao.dataAvaliacao || undefined;
    }
}
