package SpringBootExpertVendas;




import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import SpringBootExpertVendas.domain.Cliente;
import SpringBootExpertVendas.domain.Pedido;
import SpringBootExpertVendas.domain.repository.ClienteRepository;
import SpringBootExpertVendas.domain.repository.PedidoRepository;

@SpringBootApplication
@RestController
public class SpringBootExpertVendasApplication {

	

	@Bean
    public CommandLineRunner init(@Autowired ClienteRepository clientes,
    		@Autowired PedidoRepository pedidos){
        return args -> {
            System.out.println("Salvando clientes");
            Cliente fulano = new Cliente("fulano");
            clientes.save(fulano);
            
            
            Pedido p = new Pedido();
            
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));
            
            pedidos.save(p);
            
            Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
            System.out.println(cliente);
            System.out.println(cliente.getPedidos());
            
           pedidos.findByCliente(cliente).forEach(System.out::println);
        };
    }
	public static void main(String[] args) {
		SpringApplication.run(SpringBootExpertVendasApplication.class, args);
	}
  
}
