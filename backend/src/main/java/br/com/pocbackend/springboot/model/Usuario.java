package br.com.pocbackend.springboot.model;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the USUARIOS database table.
 * 
 */
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;
	private String perfil;
	private String senha;
	private List<Aluno> alunos;

	public Usuario() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPerfil() {
		return this.perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Aluno> getAlunos() {
		return this.alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Aluno addAluno(Aluno aluno) {
		getAlunos().add(aluno);
		aluno.setUsuario(this);

		return aluno;
	}

	public Aluno removeAluno(Aluno aluno) {
		getAlunos().remove(aluno);
		aluno.setUsuario(null);

		return aluno;
	}

}