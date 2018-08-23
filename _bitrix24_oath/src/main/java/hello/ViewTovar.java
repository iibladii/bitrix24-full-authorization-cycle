package hello;

import java.util.ArrayList;

//Класс
public class ViewTovar {
	
	private int tovarNumber;
    
    private int price;
    
    private String picture;
    
    private String product_name;
    
    private String category_name;
    
    private String remark;
    
    private String username;
    
    private String email;
    
    private String telephome;
	    
	    public ViewTovar(
	    		Integer tovarNumber, 
	    		Integer price, 
	    		String picture,
	    		String product_name, 
	    		String category_name, 
	    		String remark, 
	    		String username, 
	    		String email, 
	    		String telephome)
	    {
	    	this.tovarNumber=tovarNumber;
	    	this.price=price;
	    	this.picture=picture;
	    	this.product_name=product_name;
	    	this.category_name=category_name;
	    	this.remark=remark;
	    	this.username=username;
	    	this.email=email;
	    	this.telephome=telephome;
	    }
	    
	    public Integer getTovarNumber() {
	        return tovarNumber;
	    }
	    
	    public Integer getPrice() {
	        return price;
	    }
	    
	    public String getPicture() {
	        return picture;
	    }
	    
	    public String getProduct_name() {
	        return product_name;
	    }
	    
	    public String getCategory_name() {
	        return category_name;
	    }
	    
	    public String getRemark() {
	        return remark;
	    }
	    
	    public String getUsername() {
	        return username;
	    }
	    
	    public String getEmail() {
	        return email;
	    }
	    
	    public String getTelephome() {
	        return telephome;
	    }
}
