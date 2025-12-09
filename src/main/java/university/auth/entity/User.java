package university.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users") // ✅ Changé de "user" à "users"
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role; // ADMIN / STUDENT / TEACHER
}