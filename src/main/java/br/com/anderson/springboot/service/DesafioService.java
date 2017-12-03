package br.com.anderson.springboot.service;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import br.com.anderson.springboot.model.Feed;
import br.com.anderson.springboot.parser.RSSFeedParser;

/**
 * @author Anderson Virginio Martins
 * @version 1.0
 * 
 * Classe responsavel por prover os serviçoes para a aplicação
 *
 */
@Component
public class DesafioService {

	public String buscaFeed(String url) {
		   RSSFeedParser rSSFeedParser = new RSSFeedParser(url);
		   Feed feed = rSSFeedParser.readFeed();
		   
		   String jsonInString = "";
			
		   try {
				
				ObjectMapper mapper = new ObjectMapper();
				//Converte objeto Json e salva no diretorio
				mapper.writeValue(new File("feed.json"), feed);
	
				//Converte objeto JSON para String
				jsonInString = mapper.writeValueAsString(feed);			
	
		  } catch (JsonGenerationException e) {
				e.printStackTrace();
		  } catch (JsonMappingException e) {
				e.printStackTrace();
		  } catch (IOException e) {
				e.printStackTrace();
			}


		return jsonInString;
	}
}