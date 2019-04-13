package br.com.pocbackend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.pocbackend.springboot.exception.CodigoExistenteException;
import br.com.pocbackend.springboot.model.Curso;
import br.com.pocbackend.springboot.service.queryconstants.Queries;

@Service
public class CursoService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Curso> retrieveAllCursos() {
		List<Curso> alunos = jdbcTemplate.query(Queries.LIST_CURSOS, new BeanPropertyRowMapper<Curso>(Curso.class));
		return alunos;
	}

	public Curso retrieveCurso(Long idCurso) {
		Curso curso = null;
		try {
		curso = (Curso) jdbcTemplate.queryForObject(Queries.LIST_CURSO_BY_ID, new Object[] { idCurso },
				new BeanPropertyRowMapper<Curso>(Curso.class));
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		return curso;
	}
	
	public Curso retrieveCursoByCodigo(String codigo) {
		Curso curso = null;
		try {
		curso = (Curso) jdbcTemplate.queryForObject(Queries.LIST_CURSO_BY_CODIGO, new Object[] { codigo },
				new BeanPropertyRowMapper<Curso>(Curso.class));
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		return curso;
	}
	
	public Curso insertCurso(Curso pCurso) {
		Curso curso = this.retrieveCursoByCodigo(pCurso.getCodigoCurso());
		if(curso != null && curso.getIdCurso() != null) {
			throw new CodigoExistenteException("Código já cadastrado", null);
		}
		int result = jdbcTemplate.update(Queries.INSERT_CURSO, new Object[] { pCurso.getCodigoCurso(), pCurso.getNome(), pCurso.getAnoSemestre() });
		if(result > 0) {
			curso = this.retrieveCursoByCodigo(pCurso.getCodigoCurso());
			return pCurso;
		}
		return null;
		
	}
	
	public Curso updateCurso(Curso pCurso) {	
		
		int result = jdbcTemplate.update(Queries.UPDATE_CURSO, new Object[] { pCurso.getCodigoCurso(), pCurso.getNome(), pCurso.getAnoSemestre(), pCurso.getIdCurso() });
		if(result > 0) {
			return pCurso;
		}
		return null;
	}
	
	public int deleteCurso(Long idCurso) {		
		return jdbcTemplate.update(Queries.DElETE_CURSO, new Object[] { idCurso });
	}

}