package hello;

import java.util.ArrayList;

//Класс
public class View_magaz {
	
	    private String name;
	    
	    private String description;
	    
	    private String picture;
	    
	    public View_magaz(String name,String description,String picture) {
	    	this.name=name;
	    	this.description=description;
	    	this.picture=picture;
	    }
	    
	    public String getName() {
	        return name;
	    }
	    
	    public String getDescription() {
	        return description;
	    }
	    
	    public String getPicture() {
	        return picture;
	    }
}
