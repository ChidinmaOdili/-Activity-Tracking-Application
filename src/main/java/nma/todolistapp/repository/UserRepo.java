package nma.todolistapp.repository;

import nma.todolistapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

//    User findUserByEmailAndPassword(String email, String password);
@Query(value = "SELECT * FROM users WHERE email = ?1" , nativeQuery = true)
Optional<User> findUserByEmail(String email);
}
