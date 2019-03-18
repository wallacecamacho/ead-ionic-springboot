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

}