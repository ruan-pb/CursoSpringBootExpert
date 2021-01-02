package SpringBootExpertVendas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import SpringBootExpertVendas.domain.Usuario;
import SpringBootExpertVendas.dto.CredenciaisDTO;
import SpringBootExpertVendas.dto.TokenDTO;
import SpringBootExpertVendas.exception.SenhaInvalidaException;
import SpringBootExpertVendas.security.jwt.JwtService;
import SpringBootExpertVendas.service.UsuarioServiceImpl;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private  UsuarioServiceImpl usuarioService;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@RequestBody @Valid Usuario usuario) {
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return usuarioService.salvar(usuario);
	}
	 @PostMapping("/auth")
	    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
	        try{
	            Usuario usuario = Usuario.builder()
	                    .login(credenciais.getLogin())
	                    .senha(credenciais.getSenha()).build();
	            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
	            String token = jwtService.gerarToken(usuario);
	            return new TokenDTO(usuario.getLogin(), token);
	        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
	            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
	        }
	    }
	

}
