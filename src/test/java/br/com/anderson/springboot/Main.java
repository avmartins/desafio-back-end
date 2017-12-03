package br.com.anderson.springboot;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.anderson.springboot.model.Feed;
import br.com.anderson.springboot.model.JsonRootBean;
import br.com.anderson.springboot.parser.RSSFeedParser;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RSSFeedParser rSSFeedParser = new RSSFeedParser("http://revistaautoesporte.globo.com/rss/ultimas/feed.xml");
		Feed feed = rSSFeedParser.readFeed();
		
		JsonRootBean jsonRootBean = new JsonRootBean();
		jsonRootBean.setFeed(feed);
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			//Converte objeto Json e salva no diretorio
			mapper.writeValue(new File("feed.json"), feed);

			//Converte objeto JSON para String
			System.out.println(mapper.writeValueAsString(feed));

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
			
		
		
		
		
	}

}
