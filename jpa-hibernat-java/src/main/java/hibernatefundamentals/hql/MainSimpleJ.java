package hibernatefundamentals.hql;

import hibernatefundamentals.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.List;

public class MainSimpleJ {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.training");
        EntityManager em = emf.createEntityManager();
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
//        Session session = em.unwrap(Session.class); // works
//        Session session = sessionFactory.openSession(); // works
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

//        Query selectQuery = session.createQuery("select u from User u");
//        Query selectQuery = em.createQuery("from User u order by u.name ");
        System.out.println("Typed Query");
        TypedQuery<User> selectQuery = em.createQuery("from User u order by u.name", User.class);
        List<User> list = selectQuery.getResultList();

        list.forEach(s -> System.out.println(s.getName()));

        transaction.commit();
        em.close();

    }


}
