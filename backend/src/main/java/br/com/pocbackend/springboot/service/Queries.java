package br.com.pocbackend.springboot.service;

public interface Queries {
	
	
	String LIST_ALUNOS =" SELECT * FROM ALUNO";
	String LIST_ALUNOS_BY_ID =" SELECT * FROM ALUNO WHERE ID_ALUNO = ?";

}
