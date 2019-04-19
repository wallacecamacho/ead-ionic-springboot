package br.com.pocbackend.springboot.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.pocbackend.springboot.model.Aluno;
import br.com.pocbackend.springboot.model.Matricula;
import br.com.pocbackend.springboot.model.Usuario;

public class UsuarioMapper implements RowMapper<Usuario> {
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		Aluno aluno = new Aluno();
		aluno.setIdAluno(rs.getLong("id_aluno"));
		aluno.setEndereco(rs.getString("endereco"));
		aluno.setEstado(rs.getString("estado"));
		aluno.setMunicipio(rs.getString("municipio"));
		aluno.setTelefone(rs.getString("telefone"));
		aluno.setCpf(rs.getString("cpf"));
		aluno.setNome(rs.getString("nome"));
		
		Matricula matricula = new Matricula();
		matricula.setIdMatricula(rs.getLong("id_matricula"));
		matricula.setDataMatricula(rs.getDate("data_matricula"));
		
		Usuario usuario = new Usuario();
		usuario.setEmail(rs.getString("email"));
		usuario.setIdUsuario(rs.getLong("id_usuario"));
		usuario.setPerfil(rs.getString("perfil"));
		usuario.setSenha(rs.getString("senha"));
		usuario.setAluno(aluno);
		
		aluno.setMatricula(matricula);
		aluno.setUsuario(usuario);

		return usuario;
	}
}
