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

import br.com.pocbackend.springboot.model.Avaliacao;
import br.com.pocbackend.springboot.service.AvaliacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "AvaliacaoController")
@CrossOrigin(origins = "*")
public class AvaliacaoController {

	@Autowired
	private AvaliacaoService AvaliacaoService;

	@ApiOperation("Avaliacaos")
	@GetMapping(path = "/avaliacoes", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Avaliacao>retrieveAvaliacaos() {
		return AvaliacaoService.retrieveAllAvaliacaos();
	}

	@ApiOperation("Avaliacaos - id")
	@GetMapping(path="/avaliacoes/{idAvaliacao}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Avaliacao retrieveAvaliacao(@PathVariable Long idAvaliacao) {
		return AvaliacaoService.retrieveAvaliacao(idAvaliacao);
	}
	
	@ApiOperation("Avaliacao - post")
	@PostMapping(path="/avaliacoes", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Avaliacao postAvaliacao(@RequestBody Avaliacao avaliacao) {
		return AvaliacaoService.insertAvaliacao(avaliacao);
	}
	
	@ApiOperation("Avaliacao - put")
	@PutMapping(path="/avaliacoes", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Avaliacao putAvaliacao(@RequestBody Avaliacao avaliacao) {
		return AvaliacaoService.updateAvaliacao(avaliacao);
	}
	
	@ApiOperation("Avaliacao - delete")
	@DeleteMapping(path="/avaliacoes", produces = MediaType.APPLICATION_JSON_VALUE)
	public int deleteAvaliacao(@PathVariable Long idAvaliacao) {
		return AvaliacaoService.deleteAvaliacao(idAvaliacao);
	}
	
}
