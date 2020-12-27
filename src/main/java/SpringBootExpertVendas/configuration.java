package SpringBootExpertVendas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production") //informa que só vai executar esse comando caso o arquivo application.properties execute quando for spring.profiles.active = "production" ou posso usar a notação abaixo
//@production

public class configuration {
	
	
	@Bean(name = "applicationName")
	public String applicationConfig() {
		return "Sistema de vendas";
	}

}
