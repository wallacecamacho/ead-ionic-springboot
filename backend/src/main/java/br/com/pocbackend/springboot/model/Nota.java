package br.com.pocbackend.springboot.model;

import java.io.Serializable;


/**
 * The persistent class for the AVALIACOES database table.
 * 
 */
public class Nota implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idNota;
	private Double nota;
	private Curso curso;
	private Avaliacao avaliacao;
	private Aluno aluno;

	public Nota() {
	}

	public Long getIdNota() {
		return idNota;
	}

	public void setIdNota(Long idNota) {
		this.idNota = idNota;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	



}