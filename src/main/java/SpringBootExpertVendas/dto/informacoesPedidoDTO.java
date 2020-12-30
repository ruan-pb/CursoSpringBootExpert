package SpringBootExpertVendas.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class informacoesPedidoDTO {
	
	private Integer codigo;
	private String cpf;
	private String nomeDoCliente;
	private String dataPedido;
	private String status;
	private BigDecimal total;
	
	private List<InformacoesItemPedidoDTO> items;
	
	
	
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nomeDoCliente;
	}
	public void setNome(String nome) {
		this.nomeDoCliente = nome;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public List<InformacoesItemPedidoDTO> getItems() {
		return items;
	}
	public void setItems(List<InformacoesItemPedidoDTO> items) {
		this.items = items;
	}
	public String getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	

}
