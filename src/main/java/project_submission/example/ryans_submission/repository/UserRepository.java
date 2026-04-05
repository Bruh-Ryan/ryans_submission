package project_submission.example.ryans_submission.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project_submission.example.ryans_submission.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

     @Query("SELECT u FROM Users u WHERE u.email = :email AND u.name = :name")
     Optional<Users> findByEmailAndName(String email, String name);

     Optional<Users> findByEmail(String email);

     Optional<Users> findById(int id);

     List <Users> findByName(String name);

     List <Users> findAllByOrderByNameAsc();


}


