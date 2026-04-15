package ru.max.SchoolDairy.repository.User;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.max.SchoolDairy.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String name);
}
