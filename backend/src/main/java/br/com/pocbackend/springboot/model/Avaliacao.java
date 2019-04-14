package br.com.pocbackend.springboot.model;

import java.io.Serializable;


/**
 * The persistent class for the AVALIACOES database table.
 * 
 */
public class Avaliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idAvaliacao;
	private String dataAvaliacao;
	private String nomeAvaliacao;
	private Curso curso;

	public Avaliacao() {
	}

	public Long getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(Long idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public String getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(String dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
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