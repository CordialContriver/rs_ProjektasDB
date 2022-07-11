package ProjectDB.Repositories;

import ProjectDB.SessionFactoryProvider.SessionFactoryProvider;
import ProjectDB.Tables.Question;
import ProjectDB.Tables.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class QuestionRepository {
    private final SessionFactory sessionFactory;
    public QuestionRepository() {
        sessionFactory = SessionFactoryProvider.getInstance().getSessionFactory();
    }

    public void addNewQuestion(Question newQuestion) {
            Transaction transaction;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.persist(newQuestion);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
