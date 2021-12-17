package hibernatefundamentals;

import hibernatefundamentals.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainGet {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.training");
        EntityManager em = emf.createEntityManager();
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession(); // works

        Transaction transaction = session.beginTransaction();

        getUser(session, 3);
//        loadUser(session, 3);


        transaction.commit();
        session.close();

    }

    private static void loadUser(Session session, int id) {
        User user = session.load(User.class, id);
        System.out.println("Method Executed");

        User user2 = session.load(User.class, id);
        System.out.println("Method Executed");
        System.out.println("load-user: " + user.getName());
        System.out.println("load-user2: " + user2.getName());
    }

    private static void getUser(Session session, int id) {
        User user = session.get(User.class, id);
        System.out.println("Method Executed");
        System.out.println("get-user: " + user.getName());
        User user2 = session.get(User.class, id);
        System.out.println("get-user2: " + user2.getName());
    }
}
