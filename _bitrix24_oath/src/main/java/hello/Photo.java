package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Photo {
    private String title;
    
    private String url;
    
    private String thumbnailUrl;
    
    public Photo(String title,String url,String thumbnailUrl) {
    	this.title=title;
    	this.url=url;
    	this.thumbnailUrl=thumbnailUrl;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    
}

