package SpringBootExpertVendas.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoDTO {
	

	private Integer cliente;
	private BigDecimal total;
	private List<ItemPedidoDTO> items;

	
	
	
}
