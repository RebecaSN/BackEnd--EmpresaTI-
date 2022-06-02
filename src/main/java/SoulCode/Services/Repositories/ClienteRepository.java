package SoulCode.Services.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SoulCode.Services.Models.Cliente;
import SoulCode.Services.Models.Funcionario;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{
	//Optional<Funcionario> findByEmail(String email);
	//Optional<Funcionario> findByNomeAndEmail(String nome, String email);
}
