package SpringBootExpertVendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import SpringBootExpertVendas.interfaceAnimal.Animal;

@SpringBootApplication
@RestController
public class SpringBootExpertVendasApplication {
	
	//adicionar um valor/objeto externo na variavel escolhida
	//@Value("${application.name}") = para pegar o valor do arquivo application.properties
	
	@Autowired
	@Qualifier("applicationName")
	private String applicationName;
	
	
	@Autowired
	@Qualifier("cachorro")//ou gato
	public Animal animal;
	
	
	@Bean
	public CommandLineRunner executar() {
		return args ->{
			this.animal.fazerBarulho();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExpertVendasApplication.class, args);
	}
	
	@GetMapping("/heloo")
	public String helloWord() {
		return applicationName;
	}

}
