package br.com.pocbackend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.pocbackend.springboot.model.Avaliacao;
import br.com.pocbackend.springboot.service.mapper.AvaliacaoMapper;
import br.com.pocbackend.springboot.service.queryconstants.Queries;

@Service
public class AvaliacaoService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Avaliacao> retrieveAllAvaliacaos() {
		List<Avaliacao> alunos = jdbcTemplate.query(Queries.LIST_AVALIACOES, new AvaliacaoMapper());
		return alunos;
	}

	public Avaliacao retrieveAvaliacao(Long idAvaliacao) {
		return (Avaliacao) jdbcTemplate.queryForObject(Queries.LIST_AVALIACAO_BY_ID, new Object[] { idAvaliacao },
				new AvaliacaoMapper());
	}
	
	public Avaliacao retrieveAvaliacaoPorCurso(Long idCurso) {
		return (Avaliacao) jdbcTemplate.queryForObject(Queries.LIST_AVALIACAO_BY_ID_CURSO, new Object[] { idCurso },
				new AvaliacaoMapper());
	}
	
	public Avaliacao insertAvaliacao(Avaliacao avaliacao) {
		int result = jdbcTemplate.update(Queries.INSERT_AVALIACAO, new Object[] { avaliacao.getNomeAvaliacao(), avaliacao.getDataAvaliacao(), avaliacao.getCurso().getIdCurso() });
		if(result > 0) {
			return avaliacao;
		}
		return null;
	}
	
	public Avaliacao updateAvaliacao(Avaliacao avaliacao) {		
		int result = jdbcTemplate.update(Queries.UPDATE_AVALIACAO, new Object[] { avaliacao.getNomeAvaliacao(), avaliacao.getDataAvaliacao(), avaliacao.getCurso().getIdCurso(), avaliacao.getIdAvaliacao() });
		if(result > 0) {
			return avaliacao;
		}
		return null;
	}
	
	public int deleteAvaliacao(Long idAvaliacao) {		
		return jdbcTemplate.update(Queries.DElETE_AVALIACAO, new Object[] { idAvaliacao });
	}

}