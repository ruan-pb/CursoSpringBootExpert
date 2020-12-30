package SpringBootExpertVendas.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import SpringBootExpertVendas.domain.Cliente;
import SpringBootExpertVendas.domain.ItemPedido;
import SpringBootExpertVendas.domain.Pedido;
import SpringBootExpertVendas.domain.Produto;
import SpringBootExpertVendas.domain.StatusPedido;
import SpringBootExpertVendas.domain.repository.ClienteRepository;
import SpringBootExpertVendas.domain.repository.ItemPedidoRepository;
import SpringBootExpertVendas.domain.repository.PedidoRepository;
import SpringBootExpertVendas.domain.repository.ProdutoRepository;
import SpringBootExpertVendas.dto.ItemPedidoDTO;
import SpringBootExpertVendas.dto.PedidoDTO;
import SpringBootExpertVendas.exception.PedidoNaoEncontradoException;
import SpringBootExpertVendas.exception.RegraDeNegocioException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServicoImpl implements PedidoService{
	
	
	@Autowired
	private final PedidoRepository pedidoBD;
	
	@Autowired
	private final ClienteRepository clienteBD;
	
	private final ProdutoRepository produtoBD;
	
	private final ItemPedidoRepository ItemsPedidoBD;

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente =  dto.getCliente();
		Cliente clientee = clienteBD.findById(idCliente)
                .orElseThrow(() -> new RegraDeNegocioException("Código de cliente inválido."));
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(clientee);
		
		List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        pedidoBD.save(pedido);
        ItemsPedidoBD.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        pedido.setStatus(StatusPedido.REALIZADO);
        return pedido;
		
	}
	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraDeNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoBD
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraDeNegocioException(
                                            "Código de produto inválido: "+ idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }
	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
		return pedidoBD.findByIdFetchItens(id);
	}
    @Override
    @Transactional
    public void atualizaStatus( Integer id, StatusPedido statusPedido ) {
        pedidoBD.
                findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidoBD.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException() );
    }
    

	
	
	
	
	
	
	
	

	

}
