package SpringBootExpertVendas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import SpringBootExpertVendas.domain.Cliente;
import SpringBootExpertVendas.domain.repository.ClienteRepository;

@SpringBootApplication
@RestController
public class SpringBootExpertVendasApplication {
	
	/*
	
	 @Bean
	    public CommandLineRunner init(@Autowired ClienteRepository clientes){
	        return args -> {
	            System.out.println("Salvando clientes");
	            clientes.salvar(new Cliente("Dougllas"));
	            clientes.salvar(new Cliente("Outro Cliente"));

	            List<Cliente> todosClientes = clientes.obterTodos();
	            todosClientes.forEach(System.out::println);

	            System.out.println("Atualizando clientes");
	            todosClientes.forEach(c -> {
	                c.setNome(c.getNome() + " atualizado.");
	                clientes.atualizar(c);
	            });

	            todosClientes = clientes.obterTodos();
	            todosClientes.forEach(System.out::println);

	            System.out.println("Buscando clientes");
	            clientes.buscarPorNome("Cli").forEach(System.out::println);

//	            System.out.println("deletando clientes");
//	            clientes.obterTodos().forEach(c -> {
//	                clientes.deletar(c);
//	            });

	            todosClientes = clientes.obterTodos();
	            if(todosClientes.isEmpty()){
	                System.out.println("Nenhum cliente encontrado.");
	            }else{
	                todosClientes.forEach(System.out::println);
	            }
	        };
	    }
*/
	public static void main(String[] args) {
		SpringApplication.run(SpringBootExpertVendasApplication.class, args);
	}
	

}
