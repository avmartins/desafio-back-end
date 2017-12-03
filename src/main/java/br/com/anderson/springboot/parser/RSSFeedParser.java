package br.com.anderson.springboot.parser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import br.com.anderson.springboot.model.Description;
import br.com.anderson.springboot.model.Feed;
import br.com.anderson.springboot.model.Item;

/**
 * @author Anderson Virginio Martins
 * @version 1.0
 * 
 * Classe principal que faz o parser do feed e transforma em um objeto estrutura em 
 * forma de bean 
 *
 */
public class RSSFeedParser {
	static final String TITLE       = "title";
	static final String DESCRIPTION = "description";
	static final String LINK        = "link";
	static final String ITEM        = "item";

	final URL url;

	public RSSFeedParser(String feedUrl) {
		try {
			this.url = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @author Anderson Virginio Martins
	 * @version 1.0
	 * 
	 * popula bean Feed e Item
	 *
	 */
	public Feed readFeed() {
		Feed feed = new Feed();
		try {
			boolean isFeedHeader = true;
			// Set header values intial to the empty string
			// String description = "";
			String title = "";
			String link = "";
			String desc = "";
			
			Description description = new Description();
			
			ArrayList<Description> descriptions = new ArrayList<Description>();		

			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = read();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// read the XML document
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					String localPart = event.asStartElement().getName().getLocalPart();
					switch (localPart) {
					case ITEM:
						if (isFeedHeader) {
							isFeedHeader = false;
							
						}
						event = eventReader.nextEvent();
						description = new Description();
						break;
					case TITLE:
						title = getCharacterData(event, eventReader);
						break;
					case LINK:
						link = getCharacterData(event, eventReader);
						break;
					case DESCRIPTION:
						desc = getCharacterData(event, eventReader);						
						descriptions = getCharacterDataDescription("<description>" + desc +"</description>");										        
						break;									
					}
				} else if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
						Item item = new Item();
						item.setDescriptions(descriptions);
						item.setLink(link);
						item.setTitle(title);
						feed.getItens().add(item);
						event = eventReader.nextEvent();
						continue;
					}
				}
			}
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
		return feed;
	}

	private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
		String result = "";
		event = eventReader.nextEvent();
		if (event instanceof Characters) {
			result = event.asCharacters().getData();
		}
		return result;
	}
	
	private String getCharacterData(XMLEvent event, XMLEventReader eventReader, String type) throws XMLStreamException {
		String result = "";
		event = eventReader.nextEvent();
		if (event instanceof Characters) {
			result = event.asCharacters().getData();
		}
		return result;
	}
	
	/**
	 * @author Anderson Virginio Martins
	 * @version 1.0
	 * 
	 * popula bean Description
	 *
	 */
	private ArrayList<Description> getCharacterDataDescription(String html) throws XMLStreamException {
		
		ArrayList<Description> descriptions = new ArrayList<Description>();	
		
			// Faz o parse da String e tenta transformá-la em um documento.
	        Document document = Jsoup.parse(html);

	        Elements elements = document.getElementsByTag("description");

	        for(Element element : elements){
	        	for (Node node : element.childNodes()) {
	        		String nodeName = node.nodeName().toString();
	        		
	        		Description d = new  Description();
	        		
	        		if (node instanceof Element) {
	                    Element e = (Element) node;
	                    if (e.tag().getName().equals("div")) {
	                    	
	                    	for (Node node2 : e.childNodes()) {
	                    		//for (Node node3 : node2.childNodes()) {
	                    		if (node2 instanceof Element) {
	                    			Element e2 = (Element) node2;
	                    			if (e2.tag().getName().equals("img")) {
	                    				String content = e2.attr("src");
	            						d.setType("image");
	            						d.getContent().add(content);
	            						descriptions.add(d);
	                    				
	                    			}
	                    			if (e2.tag().getName().equals("ul")) {
	                    				
	                    				for (Node node3 : e2.childNodes()) {
	                    					if (node3 instanceof Element) {
	                    					Element e3 = (Element) node3;
	                    					
	                    					for (Node node4 : e3.childNodes()) {
	                    						if (node4 instanceof Element) {
	                    						Element e4 = (Element) node4;
	                    						
	                    						if (e4.tag().getName().equals("a")) {
	                                				String content = e4.attr("href");
	                        						d.setType("links");
	                        						d.getContent().add(content);
	                        						descriptions.add(d);                            				
	                                			}
	                    						}
												
											}
	                    					}
	                            			
	                    				}
	                    				
	                    			}
	                    		}
	                    		
	                    	}
							
	                    }
	                    
	                    if (e.tag().getName().equals("p")) {
	                    	
	                    	String content = e.text().toUpperCase().trim();
	                    	
	                    	if(!content.equalsIgnoreCase(" ")) {
								d.setType("text");
								d.getContent().add(content);
								descriptions.add(d);
							}	
							
	                    }
	        		} 
	        	}
	        }
		
		return descriptions;
	}

	private InputStream read() {
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
