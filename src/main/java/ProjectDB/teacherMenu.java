package ProjectDB;

import ProjectDB.Services.QuestionService;
import ProjectDB.Services.TestService;
import ProjectDB.Services.UserService;
import ProjectDB.Tables.*;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ProjectDB.Utilities.Methods.*;

public class teacherMenu {
    private final TestService ts = new TestService();

    public teacherMenu(User user, UserService us) {

        boolean repeat = true;
        do {
            System.out.println("""
                    [1] Peržiūrėti testo rezultatus
                    [2] Sukurti testą
                    [3] Vartotojo nustatymai
                    [0] Atsijungti
                    """);
            switch (sc.nextLine()) {
                case "1" -> seeAllTestResults(user, ts);
                case "2" -> createTest(user, ts);
                case "3" -> new accountMenu(user, us);
                case "0" -> {
                    System.out.println("Viso gero");
                    repeat = false;
                }
                default -> System.out.println("Tokio nėra");
            }
        } while (repeat);
    }

    private void seeAllTestResults(User user, TestService ts) {
        Test test = pickTestTeacher(user);
        ts.seeTestResults(test);
    }


    private Test pickTestTeacher(User user) {
//        for (Test test : ts.getTestListByTeacher(user){
        for (Test test : user.getTests()) {

            System.out.printf("[%d] %s\n", test.getId(), test.getTestName());
            System.out.println("Kartų laikytas: "+test.getAttempts().size());
        }
        int id = sc.nextInt();
        sc.nextLine();
        return user.getTests().stream().filter(test1 -> test1.getId() == id).findFirst().get();
    }

    private void createTest(User user, TestService ts) {
        String testName = stringNotEmpty("Testo pavadinimas");
        Test test = new Test(user, testName, new HashSet<Question>());
        ts.createNewTest(test);
        QuestionService qs = new QuestionService();
        while (true) {
            Question newQuestion = createQuestion(test);
            if (newQuestion == null) {
                break;
            }
//            qs.addNewQuestion(newQuestion);

            test.getQuestions().add(newQuestion);
        }
        ts.updateTest(test);
    }

    private Question createQuestion(Test test) {
        System.out.println("""
                    Naujas klausimas: Rašykite klausimo tekstą. [tuščias baigia]
                """);
        String questionText = sc.nextLine();
        if (questionText.equals("")) {
            return null;
        }
        String aA = stringNotEmpty("Atsakymas A");
        String aB = stringNotEmpty("Atsakymas B");
        String aC = stringNull("Atsakymas C (gali būti tuščia)");
        String correctA = stringCorrectAnswer(aC);
        return new Question(test, questionText, aA, aB, aC, correctA);
    }

    private String stringCorrectAnswer(String aC) {
        do {
            String įvestis = stringNotEmpty("Kuris atsakymas teisingas?");
            boolean cNotNull = !(aC == null);
            if (cNotNull && įvestis.equals("C") || įvestis.equals("c") || įvestis.equals("3")) {
                return "C";
            } else if (įvestis.equals("A") || įvestis.equals("a") || įvestis.equals("1")) {
                return "A";
            } else if (įvestis.equals("B") || įvestis.equals("b") || įvestis.equals("2")) {
                return "B";
            } else {
                System.out.println("Pasirinkite [A a 1] / [B b 2]"+(cNotNull ? " / [C c 3]" : ""));
            }
        } while (true);
    }


}