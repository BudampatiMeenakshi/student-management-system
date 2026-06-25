package student_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import student_management.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsernameAndPassword(
            String username,
            String password
    );
}