package com.zefuinha.spring_ionic_backend.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
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
	public URI uploadFile(MultipartFile multipartFile, Integer size) {
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

			// O Arquivo deve ser redimencionado?
			if (size > 0) {
				cropImage(file, ext, size);
			}

			// Envia o arquivo
			// @formatter:off
			Map<?, ?> params = ObjectUtils.asMap(
			    "public_id", imagePath + "/" + fileName, 
			    "overwrite", true
			);
			// @formatter:on
			Map<String, String> response = cnClient.uploader().upload(file, params);

			// Apaga o arquivo local depois de ter enviado
			file.delete();

			// Verifica se tudo ocorreu bem
			if (response.isEmpty() || response.get("secure_url").isEmpty()) {
				throw new FileException("Erro eo enviar arquivo");
			}

			String securityUri = response.get("secure_url");
			return URI.create(securityUri);

		} catch (IOException e) {
			throw new FileException("Erro de IO: " + e.getMessage());
		} catch (Exception e) {
			throw new FileException("Credenciais Inválidas. Detelhes: " + e.getMessage());
		}
	}

	/**
	 * Faz o upload de um arquivo no mesmo tamanho enviado
	 * 
	 * @param localFilePath
	 */
	public URI uploadFile(MultipartFile multipartFile) {
		return uploadFile(multipartFile, 0);
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

	/**
	 * Recorta a imagem
	 * 
	 * @param file
	 * @param ext
	 * @param size
	 * @throws IOException
	 */
	private void cropImage(File file, String ext, Integer size) throws IOException {
		// Converte o arquivo para imagem
		BufferedImage image = ImageIO.read(file);

		// Pega o tamanho minimo da imagem
		int min = (image.getHeight() < image.getWidth()) ? image.getHeight() : image.getWidth();

		// Redimenciona
		image = Scalr.crop(
				// Imagem
				image,
				// Posição X
				(image.getWidth() / 2) - (min / 2),
				// Posição X
				(image.getHeight() / 2) - (min / 2),
				// Altura
				min,
				// Largura
				min);
		
		// Redimenciona a imagem
		image = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, size);

		// Gravando no arquivo novamente
		ImageIO.write(image, ext, file);
	}

}
