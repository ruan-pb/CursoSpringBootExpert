package SpringBootExpertVendas.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import SpringBootExpertVendas.domain.Cliente;
import SpringBootExpertVendas.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteBD;

	@GetMapping("{id}")	 
	@ResponseBody
	public ResponseEntity getClienteById(@PathVariable Integer id){

		Optional<Cliente>cliente = clienteBD.findById(id);
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Cliente> save (@RequestBody Cliente cliente) {
		Cliente clienteSalvo = clienteBD.save(cliente);
		return ResponseEntity.ok(clienteSalvo);		
	}
	
	@DeleteMapping
	@ResponseBody
	public ResponseEntity delete(@PathVariable Integer id){
		Optional<Cliente> clienteEncontrado = clienteBD.findById(id);
		if(clienteEncontrado.isPresent()) {
			clienteBD.delete(clienteEncontrado.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
		
		
	}
	

}
