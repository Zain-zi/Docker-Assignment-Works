package com.example.AuthenticationService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    Optional<User> findAllByEmailAndAndHashedPassword(String email, String hashedPassword) {
        if (email.equals("hala@uniemail.com") && hashedPassword.equals("6ebe76c9fb411be97b3b0d48b791a7c9")) {
            return Optional.of(new User(3, "Hala", "Mohammad", "hala@uniemail.com", hashedPassword, "student"));
        }
        return Optional.empty();
    }
}
