package br.com.pocbackend.springboot.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the ALUNOS database table.
 * 
 */
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long idAluno;
	private String cpf;
	private String nome;
	private String endereco;
	private String estado;
	private String municipio;
	private String telefone;	
	private Curso curso;
	private Matricula matricula;
	private Nota nota;
	@JsonIgnore
	private Usuario usuario;
	private List<Avaliacao> avaliacoes;


	public Aluno() {
	}
	
	
	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}
	
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Nota getNota() {
		return nota;
	}


	public void setNota(Nota nota) {
		this.nota = nota;
	}


	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}


	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}


	public Avaliacao addAvaliacao(Avaliacao avaliacao) {
		getAvaliacoes().add(avaliacao);
		return avaliacao;
	}

	public Avaliacao removeMatricula(Avaliacao avaliacao) {
		getAvaliacoes().remove(avaliacao);
		return avaliacao;
	}


}