package hello;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import hello.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MagazRepository extends CrudRepository<Magaz, Long> {
	@Query("SELECT u FROM Magaz u")
    public List<Magaz> findAll();
	
	/*
	@Query("SELECT u FROM User u WHERE u.username=:name")
    public User getUserInfo(@Param("name") String name);
	
	@Query("SELECT Distinct u FROM User u, in(u.user) ur, in(ur.role) r WHERE ur.user=u.id and ur.role=r.id and u.username=:name")
    public List<User> find(@Param("name") String name);
    */
	
	@Query(value = "UPDATE Magaz a SET a.name=:name WHERE a.name=:oldname")
    Integer updateCount(@Param("name") String name,@Param("oldname") String oldname);

	//@Query(value = "UPDATE Magaz a SET a.name=:name WHERE a.name=:oldname")
	//updateMagaz(String name, String description, String picture);
}
