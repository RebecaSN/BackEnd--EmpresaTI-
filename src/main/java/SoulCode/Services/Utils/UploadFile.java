package SoulCode.Services.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
	
	public static void salvarArquivo(String uploadDir, String fileName, MultipartFile file) throws IOException {
		Path uploadPath = Paths.get(uploadDir);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		//Stream - fluxo de dados
		//InputStream - Possibilita a leitura de dodas em byte( Byte por byte)
		try(InputStream inputStream = file.getInputStream()){
			
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream,filePath,StandardCopyOption.REPLACE_EXISTING);
		}
		catch(IOException e) {
			throw new IOException("Não foi possível enviar o seu arquivo.");
		}
	}

}
