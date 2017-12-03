package br.com.anderson.springboot.model;

import java.util.ArrayList;

/**
 * @author Anderson Virginio Martins
 * @version 1.0
 * 
 * Bean para objeto Item
 *
 */
public class Item {

    private String title;
    private String link;
    private ArrayList<Description> descriptions = new ArrayList<Description>();	
    
    public void setTitle(String title) {
         this.title = title;
     }
     
	public ArrayList<Description> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(ArrayList<Description> descriptions) {
		this.descriptions = descriptions;
	}

	public String getTitle() {
         return title;
     }

    public void setLink(String link) {
         this.link = link;
     }
     public String getLink() {
         return link;
     }

    

}