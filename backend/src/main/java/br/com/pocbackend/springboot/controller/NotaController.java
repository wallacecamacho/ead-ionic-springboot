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

import br.com.pocbackend.springboot.model.Nota;
import br.com.pocbackend.springboot.service.NotaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "NotaController")
@CrossOrigin(origins = "*")
public class NotaController {

	@Autowired
	private NotaService NotaService;

	@ApiOperation("Notas")
	@GetMapping(path = "/Notas", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Nota>retrieveNotas() {
		return NotaService.retrieveAllNotas();
	}

	@ApiOperation("Notas - id")
	@GetMapping(path="/Notas/{idNota}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Nota retrieveNota(@PathVariable Long idNota) {
		return NotaService.retrieveNota(idNota);
	}
	
	@ApiOperation("Nota - post")
	@PostMapping(path="/Notas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int postNota(@RequestBody Nota nota) {
		return NotaService.insertNota(nota);
	}
	
	@ApiOperation("Nota - put")
	@PutMapping(path="/Notas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int putNota(@RequestBody Nota nota) {
		return NotaService.updateNota(nota);
	}
	
	@ApiOperation("Nota - delete")
	@DeleteMapping(path="/Notas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deleteNota(@PathVariable Long idNota) {
		return NotaService.deleteNota(idNota);
	}
	
}
