package hibernatefundamentals;

import hibernatefundamentals.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainSaveOrUpdate {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.training");
        EntityManager em = emf.createEntityManager();
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
//        Session session = em.unwrap(Session.class); // works
        Session session = sessionFactory.openSession(); // works

        session.beginTransaction();

        User detachedEntity = session.get(User.class, 1);
        User transientEntity = new User();
        transientEntity.setName("123");


        session.getTransaction().commit();
        session.close();
        //Second session
        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();

        System.out.println(session2.contains(detachedEntity));
        session2.saveOrUpdate(transientEntity);
        session2.saveOrUpdate(detachedEntity);
        detachedEntity.setName("5555");

        System.out.println("Method invoked");
        System.out.println(session2.contains(detachedEntity));

        session2.getTransaction().commit();
        session2.close();
    }
}
