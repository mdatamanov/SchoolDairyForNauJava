package ru.max.SchoolDairy.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.max.SchoolDairy.dto.Role;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
public class User {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String username;
    private String password;

    public User() {
    }

    public User(Long id, Role role, String username, String password) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.password = password;
    }
}
