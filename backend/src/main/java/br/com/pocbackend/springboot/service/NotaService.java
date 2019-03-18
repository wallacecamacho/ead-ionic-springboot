package br.com.pocbackend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.pocbackend.springboot.model.Nota;
import br.com.pocbackend.springboot.service.queryconstants.Queries;

@Component
public class NotaService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Nota> retrieveAllNotas() {
		List<Nota> alunos = jdbcTemplate.query(Queries.LIST_AVALIACOES, new BeanPropertyRowMapper<Nota>(Nota.class));
		return alunos;
	}

	public Nota retrieveNota(Long idNota) {
		return (Nota) jdbcTemplate.queryForObject(Queries.LIST_NOTA_BY_ID, new Object[] { idNota },
				new BeanPropertyRowMapper<Nota>(Nota.class));
	}

}