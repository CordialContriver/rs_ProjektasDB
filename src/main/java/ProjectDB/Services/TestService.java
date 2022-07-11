package ProjectDB.Services;

import ProjectDB.Repositories.TestRepository;
import ProjectDB.Tables.Answer;
import ProjectDB.Tables.Test;
import ProjectDB.Tables.TestAttempt;
import ProjectDB.Tables.User;

import java.sql.ResultSet;
import java.util.List;

public class TestService {
    private final TestRepository testRepository;
    public TestService() {  testRepository = new TestRepository();    }

    public void createNewTest(Test test) {
        testRepository.createTest(test);
    }

    public void updateTest(Test test) {
        testRepository.updateTest(test);
    }

    public List<Test> getTestList() {
        return testRepository.getTestList();
    }

    public List<Test> getTestListByTeacher(User user) {
 //       return testRepository.getTestList().stream().filter(test -> test.getCreator().equals(user)).toList();
        return testRepository.getTestListByTeacher(user);
    }


    public void seeTestResults(Test test) {
        for (TestAttempt ta : test.getAttempts()) {
            seeAttemptResult(ta);
        }
    }
    public void seeAttemptResult(TestAttempt ta) {
        System.out.printf("%s, %s\n%s.%s, %d%s",
                ta.getTest().getTestName(),
                ta.getDateOfTest(),
                ta.getStudent().getName().charAt(0),
                ta.getStudent().getSurname(),
                sumCorrectAnswers(ta),"%\n"
        );

    }
    private int sumCorrectAnswers(TestAttempt ta) {
        int g =0;
        int all = ta.getAnswers().size();
        for(Answer a : ta.getAnswers()){
            if(a.getQuestion().getCorrectChoice().equals(a.getAnswerChoice())){
                g++;
            }
        }
        return g/all*100;
    }

}
