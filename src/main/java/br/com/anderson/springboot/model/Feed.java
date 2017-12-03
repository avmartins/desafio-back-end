package br.com.anderson.springboot.model;

import java.util.ArrayList;

/**
 * @author Anderson Virginio Martins
 * @version 1.0
 * 
 * Bean para objeto Feed
 *
 */
public class Feed {

    ArrayList<Item> itens = new ArrayList<Item>();
    
    public ArrayList<Item> getItens() {
		return itens;
	}
	public void setItens(ArrayList<Item> itens) {
		this.itens = itens;
	}
	
}