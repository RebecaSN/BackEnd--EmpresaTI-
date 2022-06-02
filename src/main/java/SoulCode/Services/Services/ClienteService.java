package SoulCode.Services.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Cliente;
import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	//Serviços para buscar todos os funcionários cadastrados.
		public List<Cliente> mostrarTodosClientes(){
			return clienteRepository.findAll();
		}

}
