package ProjectDB.Services;

import ProjectDB.Repositories.QuestionRepository;
import ProjectDB.Repositories.TestAttemptRepository;
import ProjectDB.Tables.Test;
import ProjectDB.Tables.TestAttempt;

import java.util.List;

public class TestAttemptService {
    private final TestAttemptRepository testAttemptRepository;
    public TestAttemptService(){ testAttemptRepository  = new TestAttemptRepository(); }

    public void newTestAttempt(TestAttempt testAttempt) {
        testAttemptRepository.createTestAttempt(testAttempt);
    }

    public List<TestAttempt> getListByTest(Test test) {
        return testAttemptRepository.getAttemptList().stream().filter(testA -> testA.getTest().equals(test)).toList();

//        return testAttemptRepository.getAttemptListByTest(test);
    }

    public TestAttempt getTAByTest(Test test) {
        return testAttemptRepository.getTAByTest(test);
    }



    }
