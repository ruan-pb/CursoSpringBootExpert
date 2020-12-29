package SpringBootExpertVendas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	
	@RequestMapping(value = "/hello/{nome}",
			method = RequestMethod.GET
			/*consumes = {"application/json","application/xml"}*
			 * producoes = {"application/json","application/xml"}*/)
			 
	@ResponseBody
	public String helloCliente(@PathVariable("nome")String nomeCliente) {
		return String.format("hello %s ",nomeCliente);
	}
	
	
	
	
	

}
