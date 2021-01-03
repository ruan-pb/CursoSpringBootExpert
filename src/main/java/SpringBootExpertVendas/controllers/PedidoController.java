package SpringBootExpertVendas.controllers;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.server.ResponseStatusException;

import SpringBootExpertVendas.domain.ItemPedido;
import SpringBootExpertVendas.domain.Pedido;
import SpringBootExpertVendas.domain.StatusPedido;
import SpringBootExpertVendas.dto.AtualizacaoStatusPedidoDTO;
import SpringBootExpertVendas.dto.InformacoesItemPedidoDTO;
import SpringBootExpertVendas.dto.PedidoDTO;
import SpringBootExpertVendas.dto.informacoesPedidoDTO;
import SpringBootExpertVendas.service.PedidoServicoImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired
	private PedidoServicoImpl service;

	public PedidoController(PedidoServicoImpl service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody @Valid PedidoDTO dto) {
		Pedido pedidoSalvo = service.salvar(dto);
		return pedidoSalvo.getId();
	}

	@GetMapping("{id}")
	@ApiOperation("Buscar Pedido pelo ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Pedido encontrado com sucesso"),
		@ApiResponse(code = 404, message = "Pedido não encontrado pelo ID informado")
	})
	public informacoesPedidoDTO getById(@PathVariable @ApiParam("Id do Produto")Integer id) {
		return service.obterPedidoCompleto(id).map(p -> converter(p))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

	}

	private informacoesPedidoDTO converter(Pedido pedido) {
		return informacoesPedidoDTO.builder().codigo(pedido.getId())
				.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
				.cpf(pedido.getCliente().getCpf()).nomeDoCliente(pedido.getCliente().getNome()).total(pedido.getTotal())
				.status(pedido.getStatus().name()).items(converter(pedido.getItens())).build();

	}

	private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens) {
		if (CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		return itens.stream()
				.map(item -> InformacoesItemPedidoDTO.builder().descricaoProduto(item.getProduto().getDescricao())
						.precoUnitario(item.getProduto().getPreco()).quantidades(item.getQuantidade()).build())
				.collect(Collectors.toList());

	}
	
	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Atualizar Pedido")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Pedido Atualizado com Sucesso"),
		@ApiResponse(code = 400, message = "Erro na atualização do pedido")
	})
	public void updateStatus(@PathVariable Integer id ,@RequestBody AtualizacaoStatusPedidoDTO dto) {
		String novoStatus = dto.getNovoStatus();
		service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
		
	}
}
