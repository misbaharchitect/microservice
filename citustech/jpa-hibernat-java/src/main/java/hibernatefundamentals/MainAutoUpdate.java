package hibernatefundamentals;

import hibernatefundamentals.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainAutoUpdate {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.training");
        EntityManager em = emf.createEntityManager();
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession(); // works

        Transaction transaction = session.beginTransaction();

//        updateUser(session, 3);
        updateUserLoad(session, 3);

        transaction.commit();
        session.close();

    }

    private static void updateUser(Session session, int id) {
        User user = session.get(User.class, id);
        System.out.println("Get Method Executed");
        user.setName("New Name");
    }

    private static void updateUserLoad(Session session, int id) {
        User user = session.get(User.class, id);
        System.out.println("Load Method Executed");
        user.setName("Another Name");
    }
}
