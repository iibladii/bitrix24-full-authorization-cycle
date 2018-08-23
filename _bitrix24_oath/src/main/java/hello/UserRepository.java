package hello;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import hello.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {
	//@Query(value = "insert into User (id,email,login,name,picture) select :id,:email,:login,:name,:picture")
	//@Query(value = "insert into User (email, login, name, picture) VALUES (:email, :login, :name, :picture)", nativeQuery = true)
	//public void updateUser(@Param("email") String email, @Param("login") String login, @Param("name") String name, @Param("picture") String picture);
	
	//@Query(value = "UPDATE User a SET a.name=:name")
    //Integer update();
	
	@Query(value = "SELECT u FROM User u WHERE u.login=:login and u.pass=:pass")
    public User findUser(@Param("login") String login, @Param("pass") String pass);
	
	@Query(value = "SELECT count(u) FROM User u WHERE u.login=:login and u.pass=:pass")
    public Integer findUserCount(@Param("login") String login, @Param("pass") String pass);
	
	@Query(value = "SELECT u FROM User u WHERE u.login=:login")
    public User findUserLogin(@Param("login") String login);
	
	@Query(value = "SELECT count(u) FROM User u WHERE u.login=:login")
    public Integer findUserLoginCount(@Param("login") String login);
	//@Query(value = "SELECT u FROM User u, in(u.product) p WHERE p.id=:id")
    //public User findUserProduct(@Param("id") String id);
}