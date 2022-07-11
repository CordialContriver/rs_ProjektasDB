package ProjectDB.Repositories;

import ProjectDB.SessionFactoryProvider.SessionFactoryProvider;
import ProjectDB.Tables.Test;
import ProjectDB.Tables.User;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class TestRepository {
    private final SessionFactory sessionFactory;

    public TestRepository() {
        sessionFactory = SessionFactoryProvider.getInstance().getSessionFactory();
    }

    public void createTest(Test test) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(test);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTest(Test test) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(test);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Test> getTestList() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Test", Test.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Test> getTestListByTeacher(User user) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Test where User = :user",
                    Test.class).setParameter("user", user).list();
        } catch (NoResultException e) {
            System.out.println("No result exception");
            return Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}

//    public List<Test> getTestListByTeacher(User user) {
//        try (Session session = sessionFactory.openSession()) {
//
//            return session.createQuery("from User where username = :username",
//                    User.class).setParameter("username", username).getSingleResult();
//
//        } catch (NoResultException e){
//            System.out.println("No result exception");
//            return null;
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
