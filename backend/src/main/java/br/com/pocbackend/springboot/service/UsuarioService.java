package br.com.pocbackend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.pocbackend.springboot.model.Usuario;
import br.com.pocbackend.springboot.service.queryconstants.Queries;

@Component
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

}