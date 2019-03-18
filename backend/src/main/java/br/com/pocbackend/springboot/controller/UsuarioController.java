package br.com.pocbackend.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.pocbackend.springboot.model.Usuario;
import br.com.pocbackend.springboot.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "UsuarioController")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@ApiOperation("usuarios")
	@GetMapping(path="/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Usuario> retrieveAlunos() {
		return usuarioService.retrieveAllUsuarios();
	}

	@ApiOperation("usuarios - id")
	@GetMapping(path="/usuarios/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Usuario retrieveAluno(@PathVariable Long idUsuario) {
		return usuarioService.retrieveUsuario(idUsuario);
	}
	
}
