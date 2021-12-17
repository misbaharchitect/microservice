package hibernatefundamentals;

import hibernatefundamentals.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainDelete {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.training");
        EntityManager em = emf.createEntityManager();
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession(); // works

        Transaction transaction = session.beginTransaction();
        // update the user id
        deleteUser(session, 5);
//        deleteUserWithLoad(session, 4);

        transaction.commit();
        session.close();

    }

    private static void deleteUser(Session session, int id) {
        User user = session.get(User.class, id);
        System.out.println("Get Method Executed");
        System.out.println(session.contains(user));
        // moved to removed state
        session.delete(user);
        System.out.println(session.contains(user));
        System.out.println("Delete executed");
    }

    private static void deleteUserWithLoad(Session session, int id) {
        User user = session.load(User.class, id);
        System.out.println("load Method Executed");
        session.delete(user);
        System.out.println("Delete executed");
    }

}
