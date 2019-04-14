package br.com.pocbackend.springboot.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.pocbackend.springboot.model.Aluno;
import br.com.pocbackend.springboot.model.Matricula;

public class MatriculaMapper implements RowMapper<Matricula> {
	public Matricula mapRow(ResultSet rs, int rowNum) throws SQLException {
		Aluno aluno = new Aluno();
		aluno.setIdAluno(rs.getLong("id_aluno"));
		aluno.setEndereco(rs.getString("endereco"));
		aluno.setEstado(rs.getString("estado"));
		aluno.setMunicipio(rs.getString("municipio"));
		aluno.setTelefone(rs.getString("telefone"));
		aluno.setCpf(rs.getString("cpf"));
		
		Matricula matricula = new Matricula();
		matricula.setIdMatricula(rs.getLong("id_matricula"));
		matricula.setDataMatricula(rs.getDate("data_matricula"));
		
		matricula.setAluno(aluno);
		aluno.addMatricula(matricula);

		
		return matricula;
	}
}
