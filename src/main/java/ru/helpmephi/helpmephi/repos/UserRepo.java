package ru.helpmephi.helpmephi.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.helpmephi.helpmephi.entity.User;

public interface UserRepo extends JpaRepository<User,Long> {

    @Query(value = "SELECT u FROM User u where u.username= ?1")
    User findByUsername(String username);
    @Query(value = "SELECT u FROM User u where u.activationCode= ?1")
    User findByActivationCode(String code);
    @Query(value = "SELECT u FROM User u where u.email= ?1")
    User findByEmail(@Param("email") String email);
}
