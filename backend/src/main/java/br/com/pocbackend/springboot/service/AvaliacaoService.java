package br.com.pocbackend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.pocbackend.springboot.model.Avaliacao;
import br.com.pocbackend.springboot.service.queryconstants.Queries;

@Component
public class AvaliacaoService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Avaliacao> retrieveAllAvaliacaos() {
		List<Avaliacao> alunos = jdbcTemplate.query(Queries.LIST_AVALIACOES, new BeanPropertyRowMapper<Avaliacao>(Avaliacao.class));
		return alunos;
	}

	public Avaliacao retrieveAvaliacao(Long idAvaliacao) {
		return (Avaliacao) jdbcTemplate.queryForObject(Queries.LIST_AVALIACAO_BY_ID, new Object[] { idAvaliacao },
				new BeanPropertyRowMapper<Avaliacao>(Avaliacao.class));
	}
	
	public int insertAvaliacao(Avaliacao avaliacao) {		
		return jdbcTemplate.update(Queries.INSERT_AVALIACAO, new Object[] { avaliacao.getNomeAvaliacao(), avaliacao.getData(), avaliacao.getCurso().getIdCurso() });
	}
	
	public int updateAvaliacao(Avaliacao avaliacao) {		
		return jdbcTemplate.update(Queries.UPDATE_AVALIACAO, new Object[] { avaliacao.getNomeAvaliacao(), avaliacao.getData(), avaliacao.getCurso().getIdCurso(), avaliacao.getIdAvaliacao() });
	}
	
	public int deleteAvaliacao(Long idAvaliacao) {		
		return jdbcTemplate.update(Queries.DElETE_AVALIACAO, new Object[] { idAvaliacao });
	}

}