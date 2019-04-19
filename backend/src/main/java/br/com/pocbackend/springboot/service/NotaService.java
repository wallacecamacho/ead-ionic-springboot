package br.com.pocbackend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.pocbackend.springboot.model.Nota;
import br.com.pocbackend.springboot.service.mapper.NotaMapper;
import br.com.pocbackend.springboot.service.queryconstants.Queries;

@Service
public class NotaService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Nota> retrieveAllNotas() {
		List<Nota> alunos = jdbcTemplate.query(Queries.LIST_NOTAS, new NotaMapper());
		return alunos;
	}
	

	public List<Nota> retrieveAllNotasPorMatricula(Long idMatricula) {
		List<Nota> alunos = jdbcTemplate.query(Queries.LIST_NOTAS_POR_MATRICULA, new Object[] { idMatricula }, new NotaMapper());
		return alunos;
	}

	public Nota retrieveNota(Long idNota) {
		return (Nota) jdbcTemplate.queryForObject(Queries.LIST_NOTA_BY_ID, new Object[] { idNota },
				new BeanPropertyRowMapper<Nota>(Nota.class));
	}
	
	public List<Nota> retrieveNotasPorMatricula(Long idMatricula) {
		List<Nota> notas = jdbcTemplate.query(Queries.LIST_NOTA_BY_ID_MATRICULA, new Object[] { idMatricula },
				new NotaMapper());
		return notas;
	}
	
	public int insertNota(Nota nota) {		
		return jdbcTemplate.update(Queries.INSERT_NOTA, new Object[] { nota.getNota(), nota.getCurso().getIdCurso(), nota.getAvaliacao().getIdAvaliacao(), nota.getAluno().getMatricula().getIdMatricula()  });
	}
	
	public int updateNota(Nota nota) {		
		return jdbcTemplate.update(Queries.UPDATE_NOTA, new Object[] { nota.getNota(), nota.getCurso().getIdCurso(), nota.getAvaliacao().getIdAvaliacao(), nota.getAluno().getMatricula().getIdMatricula(), nota.getIdNota() });
	}
	
	public int deleteNota(Long idNota) {		
		return jdbcTemplate.update(Queries.DElETE_NOTA, new Object[] { idNota });
	}

}