package SoulCode.Services.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import SoulCode.Services.Services.FuncionarioService;
import SoulCode.Services.Utils.UploadFile;

@CrossOrigin
@RequestMapping("servicos")
@RestController
public class UploadFileController {
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@PostMapping("/funcionario/addFoto/{idFuncionario}")
	public ResponseEntity<Void> enviarDados(
			@PathVariable Integer idFuncionario, MultipartFile foto, 
			@RequestParam("nome") String nome){
		String fileName = nome;
		String uploadDir = "C:/Users/eduar/Desktop/SoulCode/Projeto Renato e Tatiana 2/projeto2/frontend/src/assets/imagens";
		String nomeMaisCaminho = "assets/imagens/" + nome;
		
		funcionarioService.salvarFoto(idFuncionario, nomeMaisCaminho);
		
		try {
			UploadFile.salvarArquivo(uploadDir, fileName, foto);
		} catch (Exception e) {
			System.out.println("O arquivo não foi enviado." + e);
		}
		 System.out.println("Arquivo enviado: "+ nomeMaisCaminho);
		 return ResponseEntity.ok().build();
		}
	
	
	
	
	

}

















