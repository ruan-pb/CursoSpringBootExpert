package SpringBootExpertVendas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import SpringBootExpertVendas.interfaceAnimal.Animal;

@Configuration
public class AnimalConfiguration {

	@Bean(name = "cachorro")
	public Animal cachorro() {
		return new Animal() {

			@Override
			public void fazerBarulho() {
				System.out.println("AU AU");
				
			}

	
	
		};
	}
	
	@Bean(name = "gato")
	public Animal gato() {
		return new Animal() {

			@Override
			public void fazerBarulho() {
				System.out.println("MIAU MIAU");
				
			}

	
	
		};
	}
}
