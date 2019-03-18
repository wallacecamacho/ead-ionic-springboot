package br.com.pocbackend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.pocbackend.springboot.model.Aluno;

@Component
public class AlunoService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Aluno> retrieveAllAlunos() {
		List<Aluno> alunos = jdbcTemplate.query(Queries.LIST_ALUNOS, new BeanPropertyRowMapper<Aluno>(Aluno.class));
		return alunos;
	}

	public Aluno retrieveAluno(Long idAluno) {
		return (Aluno) jdbcTemplate.queryForObject(Queries.LIST_ALUNOS_BY_ID, new Object[] { idAluno },
				new BeanPropertyRowMapper<Aluno>(Aluno.class));
	}

}