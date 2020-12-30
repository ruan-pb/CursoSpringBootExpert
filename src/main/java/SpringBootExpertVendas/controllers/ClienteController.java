package SpringBootExpertVendas.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import SpringBootExpertVendas.domain.Cliente;
import SpringBootExpertVendas.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteBD;

	@GetMapping("{id}")	 
	public Cliente getClienteById(@PathVariable Integer id){
		return clienteBD.findById(id)
                .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
		

	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save (@RequestBody @Valid Cliente cliente) {
		return clienteBD.save(cliente);
		
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id){
		 clienteBD.findById(id)
         .map( cliente -> {
             clienteBD.delete(cliente );
             return cliente;
         })
         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                 "Cliente não encontrado") );
		
		
	
		
		
	}
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @Valid @RequestBody Cliente cliente) {
		
		clienteBD.findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clienteBD.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cliente não encontrado") );
		}
	
	@GetMapping
    public List<Cliente> find( Cliente filtro ){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                            ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        return clienteBD.findAll(example);
    }
	
	

}
