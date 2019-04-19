package br.com.pocbackend.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.pocbackend.springboot.model.Aluno;
import br.com.pocbackend.springboot.model.Login;
import br.com.pocbackend.springboot.model.Usuario;

@Service
public class LoginService {

	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private UsuarioService usuarioService;

	public Usuario verificaLogin(Login login) {
		Usuario usuario = null;
		try {
			usuario = usuarioService.retrieveUsuarioByEmail(login.getEmail());
		if(usuario != null && login.getSenha().equals(usuario.getSenha()) && usuario.getPerfil().equals("ALUNO")) {
				Aluno aluno  = alunoService.retrieveAlunoByEmail(usuario.getEmail());
				usuario.setAluno(aluno);
		}
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		return usuario;
	}


}