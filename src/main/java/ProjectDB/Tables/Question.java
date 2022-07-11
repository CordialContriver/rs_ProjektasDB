package ProjectDB.Tables;

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
@Table(name = "questions")

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id")
    private Test test;
    private String questionText;
    private String varA;
    private String varB;
    private String varC;
    private String correctChoice;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Answer> answer;

    public Question(Test test, String questionText, String varA, String varB, String varC, String correctChoice) {
        this.test = test;
        this.questionText = questionText;
        this.varA = varA;
        this.varB = varB;
        this.varC = varC;
        this.correctChoice = correctChoice;
    }

    public Question(String questionText, String varA, String varB, String varC, String correctChoice) {
        this.questionText = questionText;
        this.varA = varA;
        this.varB = varB;
        this.varC = varC;
        this.correctChoice = correctChoice;
    }
}

