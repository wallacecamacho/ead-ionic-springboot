package br.com.pocbackend.springboot.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.pocbackend.springboot.model.Avaliacao;
import br.com.pocbackend.springboot.model.Curso;

public class AvaliacaoMapper implements RowMapper<Avaliacao> {
	public Avaliacao mapRow(ResultSet rs, int rowNum) throws SQLException {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setIdAvaliacao(rs.getLong("id_avaliacao"));
		avaliacao.setDataAvaliacao(rs.getString("data_avaliacao"));
		avaliacao.setNomeAvaliacao(rs.getString("nome_avaliacao"));
		avaliacao.setCurso(new Curso());
		avaliacao.getCurso().setIdCurso(rs.getLong("id_curso"));
		
		return avaliacao;
	}
}
