export class IUser {
    idUsuario: number;
    email: string;
    perfil: string;
    senha: string;
    aluno: {
        idAluno: number,
        nome: string,
        cpf: string,
        telefone: string,
        estado: string,
        municipio: string,
        endereco: string;
        curso: {
            idCurso: number;
        };
    };


    /**
     * Constructor
     *
     * @param product
     */
    constructor(usuario?) {
        usuario = usuario || {};
        this.aluno = usuario.aluno ||
        { idAluno: undefined, nome: undefined,
            cpf: undefined, telefone: undefined, estado: undefined,
            municipio: undefined, endereco: undefined,
            curso: { }
        };
        this.aluno.curso = usuario.aluno || { idCurso: undefined };
        this.idUsuario = usuario.idUsuario || usuario._id || undefined;
        this.email = usuario.email || undefined;
        this.perfil = usuario.perfil || undefined;
        this.senha = usuario.senha || undefined;

    }
}
