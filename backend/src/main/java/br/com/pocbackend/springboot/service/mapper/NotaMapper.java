package br.com.pocbackend.springboot.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.pocbackend.springboot.model.Aluno;
import br.com.pocbackend.springboot.model.Avaliacao;
import br.com.pocbackend.springboot.model.Curso;
import br.com.pocbackend.springboot.model.Matricula;
import br.com.pocbackend.springboot.model.Nota;

public class NotaMapper implements RowMapper<Nota> {
	public Nota mapRow(ResultSet rs, int rowNum) throws SQLException {
		Nota nota = new Nota();
		nota.setIdNota(rs.getLong("id_nota"));
		nota.setNota(rs.getDouble("nota"));
		
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setIdAvaliacao(rs.getLong("id_avaliacao"));
		avaliacao.setDataAvaliacao(rs.getString("data_avaliacao"));
		avaliacao.setNomeAvaliacao(rs.getString("nome_avaliacao"));
		avaliacao.setCurso(new Curso());
		avaliacao.getCurso().setIdCurso(rs.getLong("id_curso"));		
		nota.setAvaliacao(avaliacao);
		
		Aluno aluno = new Aluno();
		aluno.setIdAluno(rs.getLong("id_aluno"));
		Matricula matricula = new Matricula();
		matricula.setIdMatricula(rs.getLong("id_matricula"));
		aluno.setNome(rs.getString("nome"));
		aluno.setMatricula(matricula);
		
		Curso curso = new Curso();
		curso.setIdCurso(rs.getLong("id_curso"));
		nota.setCurso(curso);
		aluno.setCurso(curso);
		nota.setAluno(aluno);
		
		

		
		return nota;
	}
}
