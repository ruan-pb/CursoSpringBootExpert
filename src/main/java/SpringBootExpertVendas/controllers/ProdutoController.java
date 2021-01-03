package SpringBootExpertVendas.controllers;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import javax.validation.Valid;

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

import SpringBootExpertVendas.domain.Produto;
import SpringBootExpertVendas.domain.repository.ProdutoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	private ProdutoRepository produtoBD;
	
	
	
	@PostMapping
	@ResponseStatus(CREATED)
	@ApiOperation("Produto salvo com sucesso")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Produto salvo com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	public Produto save (@RequestBody @Valid Produto produto) {
		return produtoBD.save(produto);
	}
	
	@PutMapping("{}")
	@ResponseStatus(NO_CONTENT)
	@ApiOperation("Atualização do Produto")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Produto atualizado com sucesso"),
		@ApiResponse(code = 400, message = "Erro na atualização")
	})
	public void update(@PathVariable @ApiParam("Id do Produto")Integer id , @Valid @RequestBody Produto produto) {
		produtoBD.findById(id).map(p -> {
			produto.setId(p.getId());
			produtoBD.save(produto);
			return produto;
		}).orElseThrow(() -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND, "produto não encontrado"));

	}
	@DeleteMapping("{id}")
	@ApiOperation("remover Produto")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Prodtu removico com sucesso"),
		@ApiResponse(code = 400, message = "Erro ao remover produto")
	})
	public void delete(@PathVariable @ApiParam("Id do Produto")Integer id) {
		produtoBD.findById(id)
		.map(p -> {
			produtoBD.delete(p);
			return Void.TYPE;
		}).orElseThrow(() -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND, "produto não encontrado"));
	}
	
	@GetMapping("{id}")
	@ApiOperation("Buscar produto pelo Id")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Produto recuparado com Sucesso"),
		@ApiResponse(code = 404, message = "Produto não encontrado com ID informado")
	})
	public void getbyId(@PathVariable @ApiParam("Id do Produto")Integer id) {
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
