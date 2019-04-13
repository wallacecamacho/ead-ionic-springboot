package br.com.pocbackend.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pocbackend.springboot.model.Curso;
import br.com.pocbackend.springboot.service.CursoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "CursoController")
@CrossOrigin(value="*")
public class CursoController {

	@Autowired
	private CursoService CursoService;

	@ApiOperation("Cursos")
	@GetMapping(path = "/cursos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Curso>retrieveCursos() {
		return CursoService.retrieveAllCursos();
	}

	@ApiOperation("Cursos - id")
	@GetMapping(path="/cursos/{idCurso}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Curso retrieveCurso(@PathVariable Long idCurso) {
		return CursoService.retrieveCurso(idCurso);
	}
	
	@ApiOperation("Curso - post")
	@PostMapping(path="/cursos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Curso postCurso(@RequestBody Curso curso) {
		return CursoService.insertCurso(curso);
	}
	
	@ApiOperation("Curso - put")
	@PutMapping(path="/cursos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Curso putCurso(@RequestBody Curso curso) {
		return CursoService.updateCurso(curso);
	}

	@ApiOperation("Curso - delete")
	@DeleteMapping(path="/cursos/{idCurso}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public int deleteCurso(@PathVariable Long idCurso) {
		return CursoService.deleteCurso(idCurso);
	}
	
}
