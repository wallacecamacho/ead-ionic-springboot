package br.com.pocbackend.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pocbackend.springboot.model.Login;
import br.com.pocbackend.springboot.model.Usuario;
import br.com.pocbackend.springboot.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "LoginController")
@CrossOrigin(value="*")
public class LoginController {

	@Autowired
	private LoginService loginService;


	@ApiOperation("login - post")
	@PostMapping(path="/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Usuario postAluno(@RequestBody Login login) {
		return loginService.verificaLogin(login);
	}
	
}
