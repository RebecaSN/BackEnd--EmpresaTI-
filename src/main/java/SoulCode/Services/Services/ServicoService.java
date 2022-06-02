package SoulCode.Services.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Models.Servico;
import SoulCode.Services.Models.StatusServico;
import SoulCode.Services.Repositories.FuncionarioRepository;
import SoulCode.Services.Repositories.ServicoRepository;

@Service
public class ServicoService {
	
	//Injeção de dependencia - chamando o repository de serviços
	@Autowired
	ServicoRepository servicoRepository;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	//findAll - Todos serviços cadastrados
	public List<Servico> mostrarTodosServicos(){
		return servicoRepository.findAll();
	}
	
	//FindById - Busca o serviço pelo ID do serviço
	public Servico mostrarUmServico(Integer idServico) {
		Optional<Servico> servico = servicoRepository.findById(idServico);
		return servico.orElseThrow();
	}
	
	public List<Servico> buscarServicosDoFuncionario(Integer idFuncionario){
		Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
		return servicoRepository.findByFuncionario(funcionario);
	}
	
	public List<Servico> findByDataEntrada(Date data) {
		return servicoRepository.findByDataEntrada(data);
		
	}
	
	public List<Servico> buscarServicoPorIntervaloData(Date data1, Date data2){
		return servicoRepository.findByIntervaloData(data1, data2);
	}
	
	public List<Servico> buscarServicoPeloStatus(String status){
		return servicoRepository.findByStatus(status);
	}
	
	public List<Servico> buscarServicoSemFuncionario(){
		return servicoRepository.findByIdFuncionarioNull();
	}
	
	//Método para cadastro de um novo serviço.
	//no momento do cadastro o status tem que ficar como RECEBIDO e
	//idFuncionario tem que ficar como NULL.
	public Servico inserirServico(Servico servico) {
		servico.setIdServico(null);
		servico.setStatus(StatusServico.RECEBIDO);
		servico.setFuncionario(null);
		return servicoRepository.save(servico);
	}
	
	//Método para atribuir serviço para um funcionario
	//O serviço precisa receber o status de ATRIBUIDO
	public Servico atribuirFuncionario(Integer idServico, Integer idFuncionario ) {
		//Buscamos o funcionario que vai ser atribuido um servico
		Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
		//Buscamos o serviço que será atribuido pelo seu ID
		Servico servico = mostrarUmServico(idServico);
		
		servico.setIdServico(idServico);
		servico.setFuncionario(funcionario.get());
		servico.setStatus(StatusServico.ATRIBUIDO);
		
		return servicoRepository.save(servico);
		
	}
	
	//Método para mudar o status do serviço para CONCLUÍDO
	public Servico concluirServico(Integer idServico) {
		Servico servico = mostrarUmServico(idServico);
		servico.setIdServico(idServico);
		
		if(servico.getFuncionario() != null)
		{		
		servico.setStatus(StatusServico.CONCLUIDO);
		}
		return servicoRepository.save(servico);
	}
	
	//Editar um Serviço
	public Servico editarServico(Servico servico, Integer idFuncionario) {
		mostrarUmServico(servico.getIdServico());
		Funcionario funcionario = funcionarioRepository.getById(idFuncionario);
		servico.setFuncionario(funcionario);
		return servicoRepository.save(servico);
	}
	
	
	//Deletar Serviço pelo ID
	public void excluirServico(Integer idServico) {
		mostrarUmServico(idServico);
		servicoRepository.deleteById(idServico);
	}
	
	
}
