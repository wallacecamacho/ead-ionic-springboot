export interface IUser {
    id: string;
    nome: string;
    sobreNome: string;
    password: string;
    email: string;
    sexo: string;
    telefoneCelular: string;
    status: string;
    perfil: string;
    endereco: {
        estado: string;
        cidade: string;
        bairro: string;
        logradouro: string;
        numero: number;
        cep: number;
        geo: {
            lat: number;
            lgt: number;
        };
    };
    codigoSms: {
        validado: boolean;
        codigo: string;
    };
    avatar: string;
    createdAt: Date;
    updatedAt: Date;
    lastAccess: Date;
}
