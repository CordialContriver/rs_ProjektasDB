package ProjectDB;

import ProjectDB.Services.AnswerService;
import ProjectDB.Services.TestAttemptService;
import ProjectDB.Services.TestService;
import ProjectDB.Services.UserService;
import ProjectDB.Tables.*;

import java.time.LocalDate;
import java.util.List;

import static ProjectDB.Utilities.Methods.*;

public class studentMenu {
private final TestService ts = new TestService();

    public studentMenu(User user, UserService us) {
        boolean repeat = true;
        do {
            System.out.println("""
                    [1] Laikyti testą
                    [2] Peržiūrėti savo rezultatus
                    [3] Vartotojo nustatymai
                    [0] Atsijungti
                            """);
            switch (sc.nextLine()) {
                case "1" -> takeTest(user, ts);
                case "2" -> seeStudentResults(user, ts);
                case "3" -> new accountMenu(user, us);
                case "0" -> {
                    System.out.println("Viso gero");
                    repeat = false;
                }
                default -> System.out.println("Tokio nėra");
            }
        } while (repeat);
    }

    private void seeStudentResults(User user, TestService ts) {
        for(TestAttempt ta: user.getAttempts()){
            ts.seeAttemptResult(ta);
        }
    }

    private void takeTest(User user, TestService ts) {
        Test test = pickTestStudent(ts);
        if (test != null) {
            TestAttemptService tas = new TestAttemptService();
            TestAttempt testAttempt = new TestAttempt(user, test, LocalDate.now());
            tas.newTestAttempt(testAttempt);
            System.out.println(test.getTestName()+" "+LocalDate.now()+"\nStudentas: "+user.getName()+" "+user.getSurname()+"\nAtsakymai [Aa/Bb/Cc]");

            AnswerService as = new AnswerService();
            for (Question question : test.getQuestions()) {
                System.out.println(question.getQuestionText());
                System.out.print("A:"+question.getVarA()+",  B: "+question.getVarB());
                if (question.getVarC() != null) {
                    System.out.print(",  C:"+question.getVarC());
                }
                String answer = stringNotEmpty("").toUpperCase();
                as.createNewAnswer(new Answer(testAttempt, question, answer));
            }
        }
    }

    private Test pickTestStudent(TestService ts) {
        List<Test> testList = ts.getTestList();
        System.out.println();
        int testNr = Integer.parseInt(stringEmpty("Pasirinkte testo nr."))-1;
        try {
            return testList.get(testNr);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Tokio testo nėra.");
            return null;
        }
    }


}
