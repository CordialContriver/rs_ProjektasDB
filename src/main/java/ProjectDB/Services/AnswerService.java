package ProjectDB.Services;

import ProjectDB.Repositories.AnswerRepository;
import ProjectDB.Tables.Answer;
import ProjectDB.Tables.Question;
import ProjectDB.Tables.TestAttempt;

public class AnswerService {
    private final AnswerRepository answerRepository;
    public AnswerService() {
        answerRepository = new AnswerRepository();
    }

    public void createNewAnswer(Answer answer) {
        answerRepository.createNewAnswer(answer);

    }

    /*
    public void createNewAnswer(Answer answer) {
        employerRepository.createEmployer(employer);
    }
     */

}
