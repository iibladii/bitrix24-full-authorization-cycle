package hello;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;
    
    private String login;

    private String pass;
    
    private String email;

    private String pol;
    
    private String telephone;
    
    //@OneToMany(mappedBy="Product")
    //private List<Product> product;
    
    //@OneToMany(mappedBy="Product")
    //private List<Product> product;
    
    @ManyToMany(mappedBy="user")
    private List<Product> product;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	//public List<Product> getProduct() {
	//	return product;
	//}
	
	public List<Product> getProduct() {
		return product;
	}
	
}

