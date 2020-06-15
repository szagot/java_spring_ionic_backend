package com.zefuinha.spring_ionic_backend.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.zefuinha.spring_ionic_backend.services.exceptions.FileException;

/**
 * Cloudnary: Serviços para imagens
 */

@Service
public class CNService {

	@Autowired
	private Cloudinary cnClient;

	/**
	 * Faz o upload de um arquivo
	 * 
	 * @param localFilePath
	 */
	public URI uploadFile(MultipartFile multipartFile) {
		try {
			// Converte o multipart enviado para um File
			File file = multipartFileToFile(multipartFile);

			// Envia o arquivo
			@SuppressWarnings("unchecked")
			Map<String, String> response = cnClient.uploader().upload(file, ObjectUtils.emptyMap());

			// Verifica se tudo ocorreu bem
			if (response.isEmpty() || response.get("secure_url").isEmpty()) {
				throw new FileException("Erro eo enviar arquivo");
			}

			String securityUri = response.get("secure_url");
			return URI.create(securityUri);

		} catch (IOException e) {
			throw new FileException("Erro de IO: " + e.getMessage());
		} catch (Exception e) {
			throw new FileException("Credenciais Inválidas");
		}
	}

	/**
	 * Converte um MultipartFile em um File
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static File multipartFileToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

}
