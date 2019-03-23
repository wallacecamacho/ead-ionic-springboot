package br.com.pocbackend.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pocbackend.springboot.model.Matricula;
import br.com.pocbackend.springboot.service.MatriculaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "MatriculaController")
@CrossOrigin(origins = "*")
public class MatriculaController {

	@Autowired
	private MatriculaService matriculaService;

	@ApiOperation("Matriculas")
	@GetMapping(path = "/matriculas", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Matricula>retrieveMatriculas() {
		return matriculaService.retrieveAllMatriculas();
	}

	@ApiOperation("Matriculas - id")
	@GetMapping(path="/matriculas/{idMatricula}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Matricula retrieveMatricula(@PathVariable Long idMatricula) {
		return matriculaService.retrieveMatricula(idMatricula);
	}
	
	@ApiOperation("Matricula - post")
	@PostMapping(path="/matriculas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int postMatricula(@RequestBody Matricula matricula) {
		return matriculaService.insertMatricula(matricula);
	}
	
}
