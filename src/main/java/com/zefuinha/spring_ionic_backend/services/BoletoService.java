package com.zefuinha.spring_ionic_backend.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.zefuinha.spring_ionic_backend.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	private final static int VENCIMENTO = 7;

	public void preencherPagamento(PagamentoComBoleto pagto, Date criadoEm) {
		// Cria um calendário somando os dias de vencimento
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(criadoEm);
		calendar.add(Calendar.DAY_OF_MONTH, VENCIMENTO);

		pagto.setDataVencimento(calendar.getTime());
	}

}
