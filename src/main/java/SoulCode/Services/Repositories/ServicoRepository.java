package SoulCode.Services.Repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import SoulCode.Services.Models.Funcionario;
import SoulCode.Services.Models.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Integer>{
	
	List<Servico> findByFuncionario(Optional<Funcionario> funcionario);
	List<Servico> findByDataEntrada(Date data);
	
	@Query(value = "select * from servico where data_entrada between :data1 and :data2", nativeQuery = true)
	List<Servico> findByIntervaloData(Date data1, Date data2);
	
	@Query(value = "select * from servico where status = :status", nativeQuery = true)
	List<Servico> findByStatus(String status);
	
	@Query(value ="select * from servico where id_funcionario is null", nativeQuery = true)
	List<Servico> findByIdFuncionarioNull();
}
