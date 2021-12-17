package hibernatefundamentals;

import hibernatefundamentals.model.User;
import hibernatefundamentals.singletable.OneWayTicket;
import hibernatefundamentals.singletable.ReturnTicket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class MainSessionSimple {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.training");
        EntityManager em = emf.createEntityManager();
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
//        Session session = em.unwrap(Session.class); // works
        Session session = sessionFactory.openSession(); // works

        session.beginTransaction();

        User user = new User();
        user.setName("Sumit");
        session.save(user);

        session.getTransaction().commit();
        session.close();

        /*session = sessionFactory.openSession();
        session.beginTransaction();
        // Exception Removing a detached instance
        session.delete(user);
        session.getTransaction().commit();
        session.close();*/
    }
}
