package SpringBootExpertVendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import SpringBootExpertVendas.domain.Cliente;
import SpringBootExpertVendas.domain.repository.ClienteRepository;

@SpringBootApplication
public class SpringBootExpertVendasApplication {

	
	@Bean
	public CommandLineRunner commandLineRunner(@Autowired ClienteRepository clientes) {
		return args ->{
			Cliente c = new Cliente(null, "Ciclano");
			clientes.save(c);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExpertVendasApplication.class, args);
	}
  
}
