package br.com.pocbackend.springboot.model;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the MATRICULAS database table.
 * 
 */
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataMatricula;
	private Aluno aluno;
	private Curso curso;

	public Matricula() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataMatricula() {
		return this.dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}