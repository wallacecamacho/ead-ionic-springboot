package br.com.pocbackend.springboot.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the CURSOS database table.
 * 
 */
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idCurso;
	private String codigoCurso;
	private String anoSemestre;
	private String nome;
	@JsonIgnore
	private List<Avaliacao> avaliacoes;
	@JsonIgnore
	private List<Matricula> matriculas;

	public Curso() {
	}
	
	public Long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}

	public String getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public String getAnoSemestre() {
		return this.anoSemestre;
	}

	public void setAnoSemestre(String anoSemestre) {
		this.anoSemestre = anoSemestre;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Avaliacao> getAvaliacoes() {
		return this.avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public Avaliacao addAvaliacoe(Avaliacao avaliacoe) {
		getAvaliacoes().add(avaliacoe);
		avaliacoe.setCurso(this);

		return avaliacoe;
	}

	public Avaliacao removeAvaliacoe(Avaliacao avaliacoe) {
		getAvaliacoes().remove(avaliacoe);
		avaliacoe.setCurso(null);

		return avaliacoe;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setCurso(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setCurso(null);

		return matricula;
	}

}