package br.com.pocbackend.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pocbackend.springboot.model.Aluno;
import br.com.pocbackend.springboot.service.AlunoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "AlunoController")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@ApiOperation("alunos")
	@GetMapping(path = "/alunos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Aluno>retrieveAlunos() {
		return alunoService.retrieveAllAlunos();
	}

	@ApiOperation("alunos - id")
	@GetMapping(path="/alunos/{idAluno}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Aluno retrieveAluno(@PathVariable Long idAluno) {
		return alunoService.retrieveAluno(idAluno);
	}
	
	@ApiOperation("aluno - post")
	@PostMapping(path="/alunos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int postAluno(@RequestBody Aluno aluno) {
		return alunoService.insertAluno(aluno);
	}
	
	@ApiOperation("aluno - put")
	@PutMapping(path="/alunos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int putAluno(@RequestBody Aluno aluno) {
		return alunoService.updateAluno(aluno);
	}
	
	@ApiOperation("aluno - delete")
	@DeleteMapping(path="/alunos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deleteAluno(@PathVariable Long idAluno) {
		return alunoService.deleteAluno(idAluno);
	}
	
}
