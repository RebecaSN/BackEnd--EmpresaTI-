package SoulCode.Services.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Orcamento;
import SoulCode.Services.Models.Servico;
import SoulCode.Services.Models.StatusOrcamento;
import SoulCode.Services.Models.StatusServico;
import SoulCode.Services.Repositories.OrcamentoRepository;
import SoulCode.Services.Repositories.ServicoRepository;

@Service
public class OrcamentoService {
	
	@Autowired
	OrcamentoRepository orcamentoRepository;
	
	@Autowired
	ServicoRepository servicoRepository;
	
	public List<Orcamento> mostrarTodosOrcamentos(){
		return orcamentoRepository.findAll();
	}
	
	//FindById - Busca o orçamento pelo seu ID
	public Orcamento mostrarUmOrcamento(Integer idOrcamento) {
		Optional<Orcamento> orcamento = orcamentoRepository.findById(idOrcamento);
		return orcamento.orElseThrow();
	}
		
	//Método para buscar orcamentos pelo status
	public List<Orcamento> mostrarOrcamentosPeloStatus(String status){
		return orcamentoRepository.findByStatus(status);
	}
	
	public Orcamento inserirOrcamento(Orcamento orcamento, Integer idServico) {
		orcamento.setIdOrcamento(idServico);
		orcamento.setStatus(StatusOrcamento.EMITIDO);
		orcamentoRepository.save(orcamento);
		Servico servico = servicoRepository.getById(idServico);
		servico.setStatus(StatusServico.RECEBIDO);
		servico.setOrcamento(orcamento);
		servicoRepository.save(servico);
		return orcamento;
	}
	
	//Servico para pagamento de um orçamento: Liquidar Orçamento.
	//Modificar o Status de um orçamento para QUITADO
	public Orcamento quitarOrcamento(Integer idOrcamento){
		Orcamento orcamento = mostrarUmOrcamento(idOrcamento);
		orcamento.setStatus(StatusOrcamento.QUITADO);
		return orcamentoRepository.save(orcamento);
	}
	
	//Deletar Orçamento
	public void excluirOrcamento(Integer idOrcamento) {
		Servico servico = servicoRepository.getById(idOrcamento);
		servico.setOrcamento(null);
		servico.setStatus(StatusServico.ARQUIVADO);
		servicoRepository.save(servico);
		orcamentoRepository.deleteById(idOrcamento);
	}
	
	//Serviço para alteraçãp dos dados de um Orçamento.
	public Orcamento editarOrcamento(Orcamento orcamento, Integer idOrcamento) {
		mostrarUmOrcamento(orcamento.getIdOrcamento());
		return orcamentoRepository.save(orcamento);
	}
	
	
	
	
	
	
	
	
}
