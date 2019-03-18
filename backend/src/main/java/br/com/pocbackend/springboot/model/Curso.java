package br.com.pocbackend.springboot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.OneToMany;


/**
 * The persistent class for the CURSOS database table.
 * 
 */
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String anoSemestre;
	private String nome;
	private List<Avaliacao> avaliacoes;
	private List<Matricula> matriculas;

	public Curso() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getAnoSemestre() {
		return this.anoSemestre;
	}

	public void setAnoSemestre(String anoSemestre) {
		this.anoSemestre = anoSemestre;
	}

	//bi-directional many-to-one association to Aluno
	@OneToMany(mappedBy="usuario")

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