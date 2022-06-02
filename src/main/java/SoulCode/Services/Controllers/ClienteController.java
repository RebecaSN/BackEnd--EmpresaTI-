package SoulCode.Services.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SoulCode.Services.Models.Cliente;
import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Services.ClienteService;

@CrossOrigin
@RestController
@RequestMapping("servicos")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@GetMapping("/cliente")
	public List<Cliente> mostrarTodosClientes(){
		List<Cliente> clientes = clienteService.mostrarTodosClientes();
		return clientes;
	}

}
