package com.zefuinha.spring_ionic_backend.services;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

/**
 * Cloudnary: Serviços para imagens
 */

@Service
public class CNService {

	private Logger LOG = LoggerFactory.getLogger(CNService.class);

	@Autowired
	private Cloudinary cnClient;

	/**
	 * Faz o upload de um arquivo
	 * 
	 * @param localFilePath
	 */
	public void uploadFile(String localFilePath) {
		try {
			LOG.info("Iniciando Upload");
			Map response = cnClient.uploader().upload(localFilePath, ObjectUtils.emptyMap());
			LOG.info("Finalizado " + response.toString());
		} catch (IOException e) {
			LOG.info("Exceção de entrada e saída: " + e.getMessage());

		} catch (Exception e) {
			LOG.info("Exceção indefinido: " + e.getMessage());
		}
	}

}
