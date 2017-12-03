package br.com.anderson.springboot.model;
import java.util.ArrayList;


/**
 * @author Anderson Virginio Martins
 * @version 1.0
 * 
 * Bean para objeto Description
 *
 */
public class Description {

    private String type;
    ArrayList<String> content = new ArrayList<String>();
    
    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setContent(ArrayList<String> content) {
         this.content = content;
     }
     public ArrayList<String> getContent() {
         return content;
     }

}