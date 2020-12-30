package SpringBootExpertVendas.service;

import java.util.Optional;

import SpringBootExpertVendas.domain.Pedido;
import SpringBootExpertVendas.domain.StatusPedido;
import SpringBootExpertVendas.dto.PedidoDTO;

public interface PedidoService {
	
	Pedido salvar (PedidoDTO dto);
	
	Optional<Pedido> obterPedidoCompleto(Integer id);
	
	void atualizaStatus(Integer id, StatusPedido statusPedido);

}
