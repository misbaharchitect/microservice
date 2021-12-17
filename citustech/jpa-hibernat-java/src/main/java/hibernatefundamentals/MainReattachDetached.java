package hibernatefundamentals;

import hibernatefundamentals.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainReattachDetached {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.training");
        EntityManager em = emf.createEntityManager();
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
//        Session session = em.unwrap(Session.class); // works
        Session session = sessionFactory.openSession(); // works

        session.beginTransaction();

        User user = session.get(User.class, 1);

        session.getTransaction().commit();
        session.close();
        //Second session
        session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println(session.contains(user));
        session.update(user);
        user.setName("xyz");
        System.out.println("Method invoked");
        System.out.println(session.contains(user));

        session.getTransaction().commit();
        session.close();
    }
}
