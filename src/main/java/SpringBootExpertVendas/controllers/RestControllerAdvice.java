package SpringBootExpertVendas.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import SpringBootExpertVendas.exception.PedidoNaoEncontradoException;
import SpringBootExpertVendas.exception.RegraDeNegocioException;

@ControllerAdvice
public class RestControllerAdvice {
	
	@ExceptionHandler(RegraDeNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleRegrasNegocioException(RegraDeNegocioException ex) {
		String mensagemErro = ex.getMessage();
		return new ApiErrors(mensagemErro);
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException ex) {
		return new ApiErrors(ex.getMessage());
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
		List<String>errors = ex.getBindingResult().getAllErrors().stream().map(erro ->erro.getDefaultMessage()).collect(Collectors.toList());
	
	return new ApiErrors(errors);
	}
	
	

}
