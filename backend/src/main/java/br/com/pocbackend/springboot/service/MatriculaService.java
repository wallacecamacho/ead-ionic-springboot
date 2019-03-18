package br.com.pocbackend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.pocbackend.springboot.model.Matricula;
import br.com.pocbackend.springboot.service.queryconstants.Queries;

@Component
public class MatriculaService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Matricula> retrieveAllMatriculas() {
		List<Matricula> alunos = jdbcTemplate.query(Queries.LIST_MATRICULAS, new BeanPropertyRowMapper<Matricula>(Matricula.class));
		return alunos;
	}

	public Matricula retrieveMatricula(Long idMatricula) {
		return (Matricula) jdbcTemplate.queryForObject(Queries.LIST_MATRICULA_BY_ID, new Object[] { idMatricula },
				new BeanPropertyRowMapper<Matricula>(Matricula.class));
	}
	
	public int insertMatricula(Matricula matricula) {		
		return jdbcTemplate.update(Queries.INSERT_MATRICULA, new Object[] { matricula.getDataMatricula(), matricula.getAluno().getIdAluno(), matricula.getCurso().getIdCurso() });
	}


}