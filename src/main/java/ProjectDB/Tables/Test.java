package ProjectDB.Tables;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Table;
import java.util.Set;


@Data
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tests")

public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User creator;
    private String testName;
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Question> questions;
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TestAttempt> attempts;

    public Test(User creator, String testName) {
        this.creator = creator;
        this.testName = testName;
    }

    public Test(User creator, String testName, Set<Question> questions) {
        this.creator = creator;
        this.testName = testName;
        this.questions = questions;
    }
}