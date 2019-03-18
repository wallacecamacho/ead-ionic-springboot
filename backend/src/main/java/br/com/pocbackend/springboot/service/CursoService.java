package br.com.pocbackend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.pocbackend.springboot.model.Curso;
import br.com.pocbackend.springboot.service.queryconstants.Queries;

@Component
public class CursoService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Curso> retrieveAllCursos() {
		List<Curso> alunos = jdbcTemplate.query(Queries.LIST_CURSOS, new BeanPropertyRowMapper<Curso>(Curso.class));
		return alunos;
	}

	public Curso retrieveCurso(Long idCurso) {
		return (Curso) jdbcTemplate.queryForObject(Queries.LIST_CURSO_BY_ID, new Object[] { idCurso },
				new BeanPropertyRowMapper<Curso>(Curso.class));
	}

}