package br.com.pocbackend.springboot.model;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the AVALIACOES database table.
 * 
 */
public class Avaliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Date data;
	private String nomeAvaliacao;
	private Curso curso;

	public Avaliacao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNomeAvaliacao() {
		return this.nomeAvaliacao;
	}

	public void setNomeAvaliacao(String nomeAvaliacao) {
		this.nomeAvaliacao = nomeAvaliacao;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}