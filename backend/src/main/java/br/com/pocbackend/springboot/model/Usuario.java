package br.com.pocbackend.springboot.model;

import java.io.Serializable;


/**
 * The persistent class for the USUARIOS database table.
 * 
 */
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idUsuario;
	private String email;
	private String perfil;
	private String senha;
	private Aluno aluno;

	public Usuario() {
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
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

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}



}