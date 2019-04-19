package br.com.pocbackend.springboot.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.pocbackend.springboot.model.Aluno;
import br.com.pocbackend.springboot.model.Curso;
import br.com.pocbackend.springboot.model.Matricula;
import br.com.pocbackend.springboot.model.Usuario;
import br.com.pocbackend.springboot.service.mapper.UsuarioMapper;
import br.com.pocbackend.springboot.service.queryconstants.Queries;

@Service
public class UsuarioService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private MatriculaService matriculaService;
	
	@Autowired
	private CursoService cursoService;

	public List<Usuario> retrieveAllUsuarios() {
		List<Usuario> Usuarios = jdbcTemplate.query(Queries.LIST_USUARIOS, new BeanPropertyRowMapper<Usuario>(Usuario.class));
		return Usuarios;
	}

	public Usuario retrieveUsuario(String email) {
		Usuario usuario = null;
		try {
			usuario = (Usuario) jdbcTemplate.queryForObject(Queries.LIST_USUARIO_BY_ID, new Object[] { email },
					new BeanPropertyRowMapper<Usuario>(Usuario.class));
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		return usuario;
	}
	
	public Usuario retrieveUsuarioByEmail(String email) {
		Usuario usuario = null;
		try {
		usuario = (Usuario) jdbcTemplate.queryForObject(Queries.LIST_USUARIO_BY_EMAIL, new Object[] { email },
				new UsuarioMapper());
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		return usuario;
	}
	
	public Usuario insertUsuario(Usuario usuario) {		
		int resultInsert = jdbcTemplate.update(Queries.INSERT_USUARIO, new Object[] { usuario.getEmail(), usuario.getSenha(), usuario.getPerfil(), });
		if(resultInsert > 0) {
			Usuario user = this.retrieveUsuarioByEmail(usuario.getEmail());
			Curso curso = cursoService.retrieveCurso(usuario.getAluno().getCurso().getIdCurso());
			Aluno aluno = usuario.getAluno();
			aluno.setUsuario(user);
			int alunoInsert  = alunoService.insertAluno(aluno);
			aluno = alunoService.retrieveAlunoByEmail(usuario.getEmail());
			usuario.setAluno(aluno);
			usuario.getAluno().setCurso(curso);
			if(alunoInsert > 0 ) {
			Matricula matricula = new Matricula();
			matricula.setAluno(aluno);
			matricula.setDataMatricula(new Date());
			matricula.setCurso(curso);
			int matriculaInsert = matriculaService.insertMatricula(matricula);
			}
		}
		return null;
	}
	
	public int updateUsuario(Usuario usuario) {		
		return jdbcTemplate.update(Queries.UPDATE_USUARIO, new Object[] { usuario.getEmail(), usuario.getSenha(), usuario.getPerfil(), usuario.getIdUsuario() });
	}
	
	public int deleteUsuario(String email) {		
		return jdbcTemplate.update(Queries.DElETE_USUARIO, new Object[] { email });
	}

}