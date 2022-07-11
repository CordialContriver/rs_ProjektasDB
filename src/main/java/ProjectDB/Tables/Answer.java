package ProjectDB.Tables;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "answers")

public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "testattempt_id")
    private TestAttempt testAttempt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Question question;
    private String answerChoice;

    public Answer(TestAttempt testAttempt, Question question, String answerChoice) {
        this.testAttempt = testAttempt;
        this.question = question;
        this.answerChoice = answerChoice;
    }
}
