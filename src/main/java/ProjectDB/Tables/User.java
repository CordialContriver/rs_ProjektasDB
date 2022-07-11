package ProjectDB.Tables;

import ProjectDB.Utilities.UserType;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Table;

import java.util.Set;


//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String salt;
    private String passwordHash;
    private UserType userType;
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Test> tests;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TestAttempt> attempts;

    public User(String name, String surname, String username, String salt, String passwordHash, UserType userType) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.userType = userType;
    }
}
