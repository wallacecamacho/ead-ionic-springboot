package br.com.pocbackend.springboot.model;

import java.io.Serializable;


/**
 * The persistent class for the USUARIOS database table.
 * 
 */
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;


	public Login() {
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}

}