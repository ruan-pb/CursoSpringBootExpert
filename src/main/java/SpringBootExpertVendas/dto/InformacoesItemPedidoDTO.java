package SpringBootExpertVendas.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data

@Builder
public class InformacoesItemPedidoDTO {
	private String descricaoProduto;
	private BigDecimal precoUnitario;
	private Integer quantidades;
	
	
	
	
	public InformacoesItemPedidoDTO() {
		super();
	}
	public InformacoesItemPedidoDTO(String descricaoProduto, BigDecimal precoUnitario, Integer quantidades) {
		super();
		this.descricaoProduto = descricaoProduto;
		this.precoUnitario = precoUnitario;
		this.quantidades = quantidades;
	}
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public Integer getQuantidades() {
		return quantidades;
	}
	public void setQuantidades(Integer quantidades) {
		this.quantidades = quantidades;
	}
	
	

}
