package SoulCode.Services.Controllers;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Models.Servico;
import SoulCode.Services.Services.ServicoService;

@CrossOrigin //Permitir acesso na porta 8080 do BackEnd
@RestController
@RequestMapping("servicos")
public class ServicoController {
	
	@Autowired
	ServicoService servicoService;
	
	//Mapeamento para mostrar todos os serviçoes
	@GetMapping("/servico")
	public List<Servico> mostrarTodosServicos(){
		List<Servico> servicos = servicoService.mostrarTodosServicos();
		return servicos;
	}
	@GetMapping("/servico/{idServico}")
	public ResponseEntity<Servico> mostrarUmServico(@PathVariable Integer idServico){
		Servico servico = servicoService.mostrarUmServico(idServico);
		return ResponseEntity.ok().body(servico);
	}
	
	@GetMapping("/servicoFuncionario/{idFuncionario}")
	public List<Servico> buscarServicosDoFuncionario(@PathVariable Integer idFuncionario){
		List<Servico> servicos = servicoService.buscarServicosDoFuncionario(idFuncionario);
		return servicos;
	}
	@GetMapping("/servicoData")
	public List<Servico> mostrarServicosPelaData(@RequestParam ("data") @DateTimeFormat(
			iso = DateTimeFormat.ISO.DATE)Date data){
		List<Servico> servicos = servicoService.findByDataEntrada(data);
		return servicos;
	}
	
	@GetMapping("/servicoIntervaloData")
	public List<Servico> buscarServicoPorIntervaloData(@RequestParam("data1") @DateTimeFormat(
			iso = DateTimeFormat.ISO.DATE) Date data1, @RequestParam("data2") @DateTimeFormat(
					iso = DateTimeFormat.ISO.DATE) Date data2 ){
		List<Servico> servicos = servicoService.buscarServicoPorIntervaloData(data1, data2);
		return servicos;
	}
	
	@GetMapping("/servicoStatus")
	public List<Servico> buscarServicoPeloStatus(@RequestParam("status") String status){
		List<Servico> servicos = servicoService.buscarServicoPeloStatus(status);
		return servicos;
	}
	@GetMapping("/servicoSemFuncionario")
	public List<Servico> buscarServicoSemFuncionario(){
		List<Servico> servicos = servicoService.buscarServicoSemFuncionario();
		return servicos;
	}
	
	@PostMapping("/servico")
	public ResponseEntity<Servico> inserirServico(@RequestBody Servico servico){
		servico = servicoService.inserirServico(servico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(servico.getIdServico()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/atribuirServico/{idServico}/{idFuncionario}")
	public ResponseEntity<Servico> atribuirFuncionario(@PathVariable Integer idServico, 
			@PathVariable Integer idFuncionario){
		Servico servico = servicoService.atribuirFuncionario(idServico, idFuncionario);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/concluirServico/{idServico}")
	public ResponseEntity<Servico> concluirServico(@PathVariable Integer idServico){
		servicoService.concluirServico(idServico);
		return ResponseEntity.noContent().build(); 
	}
	
	@PutMapping("/servico/{idServico}/{idFuncionario}")
	public ResponseEntity<Servico> editarServico(@PathVariable Integer idServico,
			@PathVariable Integer idFuncionario,@RequestBody Servico servico){
		servico.setIdServico(idServico);
		servico = servicoService.editarServico(servico,idFuncionario);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/servico/{idServico}")
	public ResponseEntity<Void> excluirServico(@PathVariable Integer idServico){
		servicoService.excluirServico(idServico);
		return ResponseEntity.noContent().build();
	}

	
	
	
	
	
	
	

}
