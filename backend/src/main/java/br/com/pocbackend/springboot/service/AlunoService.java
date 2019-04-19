package br.com.pocbackend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.pocbackend.springboot.model.Aluno;
import br.com.pocbackend.springboot.model.Nota;
import br.com.pocbackend.springboot.service.mapper.AlunoMapper;
import br.com.pocbackend.springboot.service.queryconstants.Queries;

@Service
public class AlunoService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NotaService notaService;
	

	public List<Aluno> retrieveAllAlunos() {
		List<Aluno> alunos = jdbcTemplate.query(Queries.LIST_ALUNOS, new BeanPropertyRowMapper<Aluno>(Aluno.class));
		return alunos;
	}

	public Aluno retrieveAluno(Long idAluno) {
		return (Aluno) jdbcTemplate.queryForObject(Queries.LIST_ALUNOS_BY_ID, new Object[] { idAluno },
				new BeanPropertyRowMapper<Aluno>(Aluno.class));
	}
	
	public Aluno retrieveAlunoByEmail(String email) {
		Aluno aluno = null;
		try {			
		aluno = (Aluno) jdbcTemplate.queryForObject(Queries.LIST_ALUNOS_BY_EMAIL, new Object[] { email }, new AlunoMapper());
//		if(aluno != null) {
//			List<Nota> notas = notaService.retrieveNotasPorMatricula(aluno.getMatricula().getIdMatricula());
//		}
	} catch(EmptyResultDataAccessException e) {
		return aluno;
	}
	return aluno;
	}
	
	public int insertAluno(Aluno aluno) {		
		return jdbcTemplate.update(Queries.INSERT_ALUNO, new Object[] { aluno.getNome(), aluno.getCpf(), aluno.getEndereco(), aluno.getEstado(), aluno.getMunicipio(), aluno.getTelefone(), aluno.getUsuario().getIdUsuario() });
	}
	
	public int updateAluno(Aluno aluno) {		
		return jdbcTemplate.update(Queries.UPDATE_ALUNO, new Object[] { aluno.getNome(), aluno.getCpf(), aluno.getEndereco(), aluno.getEstado(), aluno.getMunicipio(), aluno.getTelefone(), aluno.getIdAluno() });
	}
	
	public int deleteAluno(Long idAluno) {		
		return jdbcTemplate.update(Queries.DElETE_ALUNO, new Object[] { idAluno });
	}

}