package br.com.pocbackend.springboot.service.queryconstants;

public interface Queries {
	
	
	String LIST_ALUNOS =" SELECT * FROM ALUNO";
	String LIST_ALUNOS_BY_ID =" SELECT * FROM ALUNO WHERE ID_ALUNO = ?";
	String INSERT_ALUNO="INSERT INTO ALUNO(CPF, ENDERECO, ESTADO, MUNICIPIO, TELEFONE) VALUES (?, ?, ?, ?, ?, ?)";
	String UPDATE_ALUNO="UPDATE ALUNO set CPF = ?, ENDERECO = ?, ESTADO = ?, MUNICIPIO = ?, TELEFONE = ? WHERE ID_ALUNO = ?";
	String DElETE_ALUNO="DELETE FROM ALUNO WHERE ID_ALUNO= ?";
	
	
	String LIST_USUARIOS =" SELECT * FROM USUARIO";
	String LIST_USUARIO_BY_EMAIL =" SELECT * FROM USUARIO WHERE EMAIL = ?";
	String INSERT_USUARIO="INSERT INTO USUARIO(EMAIL, SENHA, PERFIL) VALUES (?, ?, ?)";
	String UPDATE_USUARIO="UPDATE USUARIO set EMAIL = ?, SENHA = ?, PERFIL = ? WHERE ID_USUARIO = ?";
	String DElETE_USUARIO="DELETE FROM USUARIO WHERE ID_USUARIO= ?";
	
	
	String LIST_MATRICULAS =" SELECT * FROM MATRICULA";
	String LIST_MATRICULA_BY_ID =" SELECT * FROM MATRICULA WHERE ID_MATRICULA = ?";
	String INSERT_MATRICULA="INSERT INTO MATRICULA(DATA_MATRICULA, ID_ALUNO, ID_CURSO) VALUES (?, ?, ?)";
	
	
	String LIST_CURSOS =" SELECT * FROM CURSO";
	String LIST_CURSO_BY_ID =" SELECT * FROM CURSO WHERE ID_CURSO = ?";
	String LIST_CURSO_BY_CODIGO =" SELECT * FROM CURSO WHERE CODIGO_CURSO = ?";
	String INSERT_CURSO="INSERT INTO CURSO(CODIGO_CURSO, NOME, ANO_SEMESTRE) VALUES (?, ?, ?)";
	String UPDATE_CURSO="UPDATE CURSO set CODIGO_CURSO = ?, NOME = ?, ANO_SEMESTRE = ? WHERE ID_CURSO = ?";
	String DElETE_CURSO="DELETE FROM CURSO WHERE ID_CURSO= ?";
	
	String LIST_AVALIACOES =" SELECT * FROM AVALIACAO";
	String LIST_AVALIACAO_BY_ID =" SELECT * FROM AVALIACAO WHERE ID_AVALIACAO = ?";
	String LIST_AVALIACAO_BY_ID_CURSO =" SELECT * FROM AVALIACAO WHERE ID_CURSO = ?";
	String INSERT_AVALIACAO="INSERT INTO AVALIACAO(NOME_AVALIACAO, DATA_AVALIACAO, ID_CURSO) VALUES (?, ?, ?)";
	String UPDATE_AVALIACAO="UPDATE AVALIACAO set NOME_AVALIACAO = ?, DATA_AVALIACAO = ?, ID_CURSO = ? WHERE ID_AVALIACAO = ?";
	String DElETE_AVALIACAO="DELETE FROM AVALIACAO WHERE ID_AVALIACAO= ?";
	
	String LIST_NOTAS =" SELECT * FROM NOTA";
	String LIST_NOTA_BY_ID =" SELECT * FROM NOTA WHERE ID_NOTA = ?";
	String LIST_NOTA_BY_ID_AVALIACAO =" SELECT * FROM NOTA WHERE ID_AVALIACAO = ?";
	String INSERT_NOTA="INSERT INTO NOTA(NOTA, ID_CURSO, ID_AVALIACAO) VALUES (?, ?, ?)";
	String UPDATE_NOTA="UPDATE NOTA set NOTA = ? WHERE ID_NOTA = ? ";
	String DElETE_NOTA="DELETE FROM NOTA WHERE ID_NOTA= ?";


}
