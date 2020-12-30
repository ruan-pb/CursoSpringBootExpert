package SpringBootExpertVendas.controllers;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

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
import SpringBootExpertVendas.domain.Produto;
import SpringBootExpertVendas.domain.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	private ProdutoRepository produtoBD;
	
	
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Produto save (@RequestBody Produto produto) {
		return produtoBD.save(produto);
	}
	
	@PutMapping("{}")
	@ResponseStatus(NO_CONTENT)
	public void update(@PathVariable Integer id ,@RequestBody Produto produto) {
		produtoBD.findById(id).map(p -> {
			produto.setId(p.getId());
			produtoBD.save(produto);
			return produto;
		}).orElseThrow(() -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND, "produto não encontrado"));

	}
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		produtoBD.findById(id)
		.map(p -> {
			produtoBD.delete(p);
			return Void.TYPE;
		}).orElseThrow(() -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND, "produto não encontrado"));
	}
	
	@GetMapping("{id}")
	public void getbyId(@PathVariable Integer id) {
		produtoBD.findById(id).orElseThrow(() -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND, "produto não encontrado"));
	}
	
	@GetMapping
    public List<Produto> find( Produto filtro ){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                            ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        return produtoBD.findAll(example);
    }
	
		

}
