package br.com.anderson.springboot.model;

/**
 * @author Anderson Virginio Martins
 * @version 1.0
 * 
 * Bean para objeto JsonRootBean
 *
 */public class JsonRootBean {

    private Feed feed;
    public void setFeed(Feed feed) {
         this.feed = feed;
     }
     public Feed getFeed() {
         return feed;
     }

}