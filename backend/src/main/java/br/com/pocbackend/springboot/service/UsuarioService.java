package br.com.pocbackend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.pocbackend.springboot.model.Usuario;
import br.com.pocbackend.springboot.service.queryconstants.Queries;

@Service
public class UsuarioService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Usuario> retrieveAllUsuarios() {
		List<Usuario> Usuarios = jdbcTemplate.query(Queries.LIST_USUARIOS, new BeanPropertyRowMapper<Usuario>(Usuario.class));
		return Usuarios;
	}

	public Usuario retrieveUsuario(Long idUsuario) {
		return (Usuario) jdbcTemplate.queryForObject(Queries.LIST_USUARIO_BY_EMAIL, new Object[] { idUsuario },
				new BeanPropertyRowMapper<Usuario>(Usuario.class));
	}
	
	public int insertUsuario(Usuario usuario) {		
		return jdbcTemplate.update(Queries.INSERT_USUARIO, new Object[] { usuario.getEmail(), usuario.getSenha(), usuario.getPerfil(), });
	}
	
	public int updateUsuario(Usuario usuario) {		
		return jdbcTemplate.update(Queries.UPDATE_USUARIO, new Object[] { usuario.getEmail(), usuario.getSenha(), usuario.getPerfil(), usuario.getIdUsuario() });
	}
	
	public int deleteUsuario(Long idUsuario) {		
		return jdbcTemplate.update(Queries.DElETE_USUARIO, new Object[] { idUsuario });
	}

}