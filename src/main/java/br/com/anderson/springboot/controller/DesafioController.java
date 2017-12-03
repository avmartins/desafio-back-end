package br.com.anderson.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.anderson.springboot.service.DesafioService;

/**
 * @author Anderson Virginio Martins
 * @version 1.0
 * 
 * Classe responsavel por prover o webservice da aplicação para poder gerar o json de resposta
 *
 */
@RestController
public class DesafioController {

	@Autowired
	private DesafioService desafioService;

	@GetMapping("/buscaFeed")
	public String buscaFeed() {

		String jsonInString = "";

		try {

			jsonInString = desafioService.buscaFeed("http://revistaautoesporte.globo.com/rss/ultimas/feed.xml");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonInString;
	}
}
