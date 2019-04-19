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

import br.com.pocbackend.springboot.model.Usuario;
import br.com.pocbackend.springboot.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "UsuarioController")
@CrossOrigin(value="*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@ApiOperation("usuarios")
	@GetMapping(path="/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Usuario> retrieveAlunos() {
		return usuarioService.retrieveAllUsuarios();
	}
	
	@ApiOperation("usuarios - id")
	@GetMapping(path="/usuarios/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Usuario retrieveAluno(@PathVariable String email) {
		return usuarioService.retrieveUsuario(email);
	}
	
	@ApiOperation("usuario - post")
	@PostMapping(path="/usuarios", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Usuario postAluno(@RequestBody Usuario usuario) {
		return usuarioService.insertUsuario(usuario);
	}
	
	@ApiOperation("usuario - put")
	@PutMapping(path="/usuarios", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int putAluno(@RequestBody Usuario usuario) {
		return usuarioService.updateUsuario(usuario);
	}
	
	
	@ApiOperation("usuario - delete")
	@DeleteMapping(path="/usuarios/{email}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int deleteAluno(@PathVariable String email) {
		return usuarioService.deleteUsuario(email);
	}
}
