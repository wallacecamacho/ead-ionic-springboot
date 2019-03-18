package br.com.pocbackend.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/alunos")
	public List<Aluno> retrieveAlunos() {
		return alunoService.retrieveAllAlunos();
	}

	@ApiOperation("alunos - id")
	@GetMapping("/alunos/{idAluno}")
	public Aluno retrieveAluno(@PathVariable Long idAluno) {
		return alunoService.retrieveAluno(idAluno);
	}
	
}
