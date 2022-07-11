package ProjectDB.Services;

import ProjectDB.Repositories.QuestionRepository;
import ProjectDB.Tables.Question;

public class QuestionService {
    private final QuestionRepository questionRepository;
    public QuestionService(){ questionRepository = new QuestionRepository(); }

    public void addNewQuestion(Question newQuestion) {
        questionRepository.addNewQuestion(newQuestion);
    }
}
