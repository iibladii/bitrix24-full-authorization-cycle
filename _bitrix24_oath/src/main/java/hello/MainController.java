package hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.susu.knot.test_pr.ru.fos.bitrix24.api.client.OAuthClient;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.client.RestClient;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.AppInfo;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.Task;
import com.susu.knot.test_pr.ru.fos.bitrix24.api.models.auth;

import hello.User;
import hello.UserRepository;

@Controller
@RequestMapping(path="/torg")
public class MainController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MagazRepository magazRepository;
	@Autowired
	private ProductRepository productRepository;
	
	
	@GetMapping(path="/getCode")
	public @ResponseBody String getData(@RequestParam(value="code") String code) throws IOException {
		System.out.println("Code: "+code);
		//Авторизуемся и получим токены
		auth a = OAuthClient.getOAuth4(code, "oauth.bitrix.info", "local.5af45f2824e899.07024753", "JLHwiI0VdoQWJYl4SJSD1irxYQpZLP105JTx1FvVFaX8o3AdE5");
		System.out.println("refresh_token:  "+a.access_token+"  refresh_token:"+a.refresh_token);
		//Запросим информацию о нашем приложении
		String str = RestClient.appInfoStr(a);
		//Выведем в консоль ответ
		System.out.println(str);
		return "success";
	}
	
	/*
	@GetMapping(path="/getData")
	public @ResponseBody String tovarInfo1(HttpServletRequest request) {
		//Массив значений параметров
		ArrayList<String> list = new ArrayList<String>();
		//Получим имена параметров
		Enumeration<String> v = request.getParameterNames();
		//Получим значения параметров и сохраним в list
		for (Enumeration<String> e = v; e.hasMoreElements();)
		{			
			list.add(request.getParameter(e.nextElement()));
		}
		return "success";
	}
	*/
	
	@GetMapping(path="/tovarInfo")
	public @ResponseBody ViewTovar tovarInfo(@RequestParam(value="productNumber") Integer pn) {
		Product p = new Product();
		p = productRepository.findProduct(pn);
		User u = productRepository.findUserProduct(p.getId());
		ViewTovar vt = new ViewTovar(
				p.getId(),
				p.getPrice(),
				p.getPicture(),
				p.getProduct_name(),
				p.getCategory_name(),
				p.getRemark(),
				u.getName(),
				u.getEmail(),
				u.getTelephone()
				);
		return vt;
	}
	
	//Создание нового продукта
	//http://127.0.0.1:8080/torg/setti?userLogin=user&price=228&picture=https://www.google.ru/imgres?imgurl=http%3A%2F%2Fguides.gosuslugi.ru%2Fdownload.html%3Ffile%3D%2F796%2Fall-be-ok.svg%26dl%3D0&imgrefurl=http%3A%2F%2Fguides.gosuslugi.ru%2Fgayd-po-sozdaniyu-interfeysa-epgu%2Fgraficheskie-elementi%2Ficons-result.html&docid=jCVNH63GjjrjxM&tbnid=be7bDyHxSeTf9M%3A&vet=10ahUKEwi7qO-IhMfaAhVBsiwKHVAoDL8QMwi9ASgIMAg..i&w=791&h=800&hl=ru&bih=974&biw=1920&q=%D0%B8%D0%BA%D0%BE%D0%BD%D0%BA%D0%B8&ved=0ahUKEwi7qO-IhMfaAhVBsiwKHVAoDL8QMwi9ASgIMAg&iact=mrc&uact=8&productName=oval&categoryName=picture&remark=mnogoCvetov
	@GetMapping(path="/setti")
	public @ResponseBody String setProduct(
			@RequestParam(value="userLogin") String userLogin, //Логин пользователя добавившего товар
			@RequestParam(value="price") Integer price, 
			@RequestParam(value="picture") String picture, 
			@RequestParam(value="productName") String productName, 
			@RequestParam(value="categoryName") String categoryName,
			@RequestParam(value="remark") String remark)
	{
		User us = userRepository.findUserLogin(userLogin);
		
		Product pr = new Product();
		pr.setPicture(picture);
		pr.setPrice(price);
		pr.setProduct_name(productName);
		pr.setCategory_name(categoryName);
		pr.setRemark(remark);
		pr.setUser(us);
		
		productRepository.save(pr);
		
		return "success";
	}
	
	//Получим список продуктов
	//http://127.0.0.1:8080/torg/getti?product_name=&category_name=
	@GetMapping(path="/getti")
	public @ResponseBody ArrayList<ViewProductList> getProductInfo(@RequestParam(value="product_name") String ti,@RequestParam(value="category_name") String cn) {
		
		ArrayList<ViewProductList> vpl = new ArrayList<ViewProductList>();
		
		Iterable<Product> pr = productRepository.findAll();
		
		Iterator<Product> i1 = pr.iterator();
		while(i1.hasNext()) {
			Product p = (Product) i1.next();
			ViewProductList vpr = new ViewProductList(p.getId(), p.getProduct_name() ,p.getPrice());
			vpl.add(vpr);
		}
		return vpl;
	}
	
	//Получим данные о пользователе по логину
	@GetMapping(path="/getUser")
	public @ResponseBody ViewUser getUser(@RequestParam(value="login") String login, @RequestParam(value="pass") String pass) {
		// This returns a JSON or XML with the users
		User u = userRepository.findUser(login, pass);
		if(userRepository.findUserCount(login, pass)>0) {
			ViewUser vu = new ViewUser(u.getName(), u.getLogin(), u.getEmail(), u.getPol(), u.getTelephone());
			return vu;
		}
		else {
			ViewUser vu = new ViewUser("error", "error", "error", "error", "error");
			return vu;
		}
	}
	
	//Получим список всех пользователей
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
	
	//Получим общедоступную инфу о пользователе
	@GetMapping(path="/getUserInfo")
	public @ResponseBody ViewUser getUserInfo(@RequestParam(value="login") String login) {
		User ur = userRepository.findUserLogin(login);
		ViewUser vu = new ViewUser(ur.getName(), ur.getLogin(), ur.getEmail(), ur.getPol(), ur.getTelephone());
		return vu;
	}
	
	//Создадим нового пользователя
	//http://127.0.0.1:8080/torg/createUser?name=dd&login=user&pass=user&email=email&pol=m&telephone=telephone
	
    @RequestMapping(value= "/createUser")
    public @ResponseBody String createUser(@RequestParam(value="name") String name, @RequestParam(value="login") String login, @RequestParam(value="pass") String pass, @RequestParam(value="email") String email, @RequestParam(value="pol") String pol, @RequestParam(value="telephone") String telephone) {
		//System.out.println(name +"        "+ login);
		
		User ss = userRepository.findUserLogin(login);
		Integer co = userRepository.findUserLoginCount(login);
		if(co==0)
		{
			User u = new User();
			u.setEmail(email);
			u.setName(name);
			u.setLogin(login);
			u.setPass(pass);
			u.setPol(pol);
			u.setTelephone(telephone);
			userRepository.save(u);
			return "success";
		}
		else
			return "user exists";
    }
	
	//Проверка доступности
	@GetMapping(path="/ping")
	public @ResponseBody List<Photo> ping() {
		System.out.println("ping!!");
		List<Photo> p = new ArrayList<Photo>();
		Photo p1 = new Photo("1", "http://placehold.it/600/92c952", "http://placehold.it/150/92c952");
		Photo p2 = new Photo("2", "http://placehold.it/600/771796", "http://placehold.it/150/92c952");
		
		p.add(p1);
		p.add(p2);
		return p;
	}
}
