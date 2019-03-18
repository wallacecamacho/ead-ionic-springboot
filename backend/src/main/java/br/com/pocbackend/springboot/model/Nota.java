package br.com.pocbackend.springboot.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the AVALIACOES database table.
 * 
 */
public class Nota implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idNota;
	private Double nota;
	@JsonIgnore
	private Curso curso;
	@JsonIgnore
	private Avaliacao avaliacao;

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



}