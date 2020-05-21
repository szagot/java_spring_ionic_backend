package com.zefuinha.spring_ionic_backend.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	/**
	 * Decodifica um string no padrão URL
	 * 
	 * @param s
	 * @return
	 */
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * Converte ums lista de url (1,2,...,9) em um List<Integer>
	 * 
	 * @param s
	 * @return
	 */
	public static List<Integer> decodeIntList(String s) {
		List<Integer> list = new ArrayList<>();

		// Se a lista informada não estiver vazia
		if (s != null && !s.isEmpty()) {

			// Cria um vetor com cada elemento separado por vírgula
			String[] vet = s.split(",");
			for (int i = 0; i < vet.length; i++) {
				list.add(Integer.parseInt(vet[i]));
			}

		}

		return list;
	}

}
