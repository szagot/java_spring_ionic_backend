package com.zefuinha.spring_ionic_backend.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${image.path}")
	private String imagePath;

	/**
	 * Faz o upload de um arquivo
	 * 
	 * @param localFilePath
	 */
	@SuppressWarnings("unchecked")
	public URI uploadFile(MultipartFile multipartFile) {
		try {
			// Pega a extenção do arquivo enviado, usando o commons.io
			String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename()).toLowerCase();
			String fileName = FilenameUtils.getBaseName(multipartFile.getOriginalFilename()).toLowerCase();

			// É PNG, WEBP JPG ou GIF?
			if (!"png".equals(ext) && !"webp".equals(ext) && !"jpg".equals(ext) && !"jpeg".equals(ext)
					&& !"gif".equals(ext)) {
				throw new FileException("Envie imagens apenas. Extenções permitidas: JPG, GIF, PNG, WEBP");
			}

			// Converte o multipart enviado para um File
			File file = multipartFileToFile(multipartFile);

			// Envia o arquivo
			// @formatter:off
			Map<?, ?> params = ObjectUtils.asMap(
			    "public_id", imagePath + "/" + fileName, 
			    "overwrite", true
			);
			// @formatter:on
			Map<String, String> response = cnClient.uploader().upload(file, params);

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
