package SoulCode.Services.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Repositories.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	//Injeção de dependência
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	//Serviços para buscar todos os funcionários cadastrados.
	public List<Funcionario> mostrarTodosFuncionarios(){
		return funcionarioRepository.findAll();
	}
	
	//findById - busca em funcionário especifico pelo seu ID.
	public Funcionario mostrarUmFuncionario(Integer idFuncionario) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
		return funcionario.orElseThrow();
	}
	
	//findByEmail
	public Funcionario mostrarFuncionarioPeloEmail(String email) {
		Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(email);
				return funcionario.orElseThrow();
	}
	
	//findByNomeAndEmail
	public Funcionario mostrarFuncionarioPeloNomeEEmail(String nome, String email) {
		Optional<Funcionario> funcionario = funcionarioRepository.findByNomeAndEmail(nome, email);
		return funcionario.orElseThrow();
	}
	
	//save - insere um novo registo na tabela do DB
	public Funcionario inserirFuncionario(Funcionario funcionario) {
		//Por precaução limpar o campo ID do funcionário.
		funcionario.setIdFuncionario(null);
		return funcionarioRepository.save(funcionario);
	}
	
	
	//editar um funcionario cadastrado
	public Funcionario editarFuncionario(Funcionario funcionario) {
		mostrarUmFuncionario(funcionario.getIdFuncionario());
		return funcionarioRepository.save(funcionario);
	}
	
	//Deletar um funcionário pelo seu id
	public void excluirFuncionario(Integer idFuncionario) {
		mostrarUmFuncionario(idFuncionario);
		funcionarioRepository.deleteById(idFuncionario);
	}
	
	public Funcionario salvarFoto(Integer idFuncionario, String caminhoFoto) {
		Funcionario funcionario = mostrarUmFuncionario(idFuncionario);
		funcionario.setFoto(caminhoFoto);
		return funcionarioRepository.save(funcionario);
	}
	
	
	
	
}
