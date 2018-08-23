package hello;

import java.util.ArrayList;

//Класс
public class ViewProductList {
	
	    private Integer id;
	    
	    private String productName;
	    
	    private Integer price;
	    
	    public ViewProductList(Integer id,String productName,Integer price) {
	    	this.id=id;
	    	this.productName=productName;
	    	this.price=price;
	    }
	    
	    public Integer getId() {
	        return id;
	    }
	    
	    public String getProductName() {
	        return productName;
	    }
	    
	    public Integer getPrice() {
	        return price;
	    }
}
