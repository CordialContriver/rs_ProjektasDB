package ProjectDB.Tables;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "testattempts")

public class TestAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User student;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id")
    private Test test;
    private LocalDate dateOfTest;
    @OneToMany(mappedBy = "testAttempt", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Answer> answers;

    public TestAttempt(User student, Test test, LocalDate dateOfTest) {
        this.student = student;
        this.test = test;
        this.dateOfTest = dateOfTest;
    }
}
