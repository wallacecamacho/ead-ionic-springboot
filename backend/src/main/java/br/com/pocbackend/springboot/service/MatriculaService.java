package br.com.pocbackend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.pocbackend.springboot.model.Matricula;
import br.com.pocbackend.springboot.service.mapper.MatriculaMapper;
import br.com.pocbackend.springboot.service.queryconstants.Queries;

@Service
public class MatriculaService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Matricula> retrieveAllMatriculas() {
		List<Matricula> alunos = jdbcTemplate.query(Queries.LIST_MATRICULAS, new MatriculaMapper());
		return alunos;
	}

	public Matricula retrieveMatricula(Long idMatricula) {
		return (Matricula) jdbcTemplate.queryForObject(Queries.LIST_MATRICULA_BY_ID, new Object[] { idMatricula },
				new MatriculaMapper());
	}
	
	public List<Matricula> retrieveMatriculaPorCursos(Long idCurso) {
		List<Matricula> alunos = jdbcTemplate.query(Queries.LIST_MATRICULA_BY_ID_CURSO, new Object[] { idCurso },
				new MatriculaMapper());
		return alunos;
	}
	
	public int insertMatricula(Matricula matricula) {		
		return jdbcTemplate.update(Queries.INSERT_MATRICULA, new Object[] { matricula.getDataMatricula(), matricula.getAluno().getIdAluno(), matricula.getCurso().getIdCurso() });
	}


}