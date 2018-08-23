package hello;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToMany;

//Класс
public class ViewUser {
	
    private String name;
    
    private String login;
    
    private String email;

    private String pol;
    
    private String telephone;

	public String getName() {
		return name;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getEmail() {
		return email;
	}
    
	public String getPol() {
		return pol;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	    
	public ViewUser(
	    	String name,
	    	String login, 
	    	String email, 
	    	String pol,
	    	String telephone)
	{
	    	this.name=name;
	    	this.login=login;
	    	this.email=email;
	    	this.pol=pol;
	    	this.telephone=telephone;
	    	}

}
