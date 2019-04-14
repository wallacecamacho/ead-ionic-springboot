package br.com.pocbackend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.pocbackend.springboot.model.Aluno;
import br.com.pocbackend.springboot.model.Usuario;
import br.com.pocbackend.springboot.service.queryconstants.Queries;

@Service
public class UsuarioService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AlunoService alunoService;

	public List<Usuario> retrieveAllUsuarios() {
		List<Usuario> Usuarios = jdbcTemplate.query(Queries.LIST_USUARIOS, new BeanPropertyRowMapper<Usuario>(Usuario.class));
		return Usuarios;
	}

	public Usuario retrieveUsuario(Long idUsuario) {
		return (Usuario) jdbcTemplate.queryForObject(Queries.LIST_USUARIO_BY_ID, new Object[] { idUsuario },
				new BeanPropertyRowMapper<Usuario>(Usuario.class));
	}
	
	public Usuario retrieveUsuarioByEmail(String email) {
		return (Usuario) jdbcTemplate.queryForObject(Queries.LIST_USUARIO_BY_EMAIL, new Object[] { email },
				new BeanPropertyRowMapper<Usuario>(Usuario.class));
	}
	
	public Usuario insertUsuario(Usuario usuario) {		
		int resultInsert = jdbcTemplate.update(Queries.INSERT_USUARIO, new Object[] { usuario.getEmail(), usuario.getSenha(), usuario.getPerfil(), });
		if(resultInsert > 0) {
			Usuario user = this.retrieveUsuarioByEmail(usuario.getEmail());
			Aluno aluno = usuario.getAluno();
			aluno.setUsuario(user);
			int alunoInsert  = alunoService.insertAluno(aluno);
		}
		return null;
	}
	
	public int updateUsuario(Usuario usuario) {		
		return jdbcTemplate.update(Queries.UPDATE_USUARIO, new Object[] { usuario.getEmail(), usuario.getSenha(), usuario.getPerfil(), usuario.getIdUsuario() });
	}
	
	public int deleteUsuario(Long idUsuario) {		
		return jdbcTemplate.update(Queries.DElETE_USUARIO, new Object[] { idUsuario });
	}

}