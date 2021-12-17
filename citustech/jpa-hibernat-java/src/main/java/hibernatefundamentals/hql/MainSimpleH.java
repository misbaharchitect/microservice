package hibernatefundamentals.hql;

import hibernatefundamentals.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class MainSimpleH {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.training");
        EntityManager em = emf.createEntityManager();
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
//        Session session = em.unwrap(Session.class); // works
        Session session = sessionFactory.openSession(); // works

        session.beginTransaction();

//        Query selectQuery = session.createQuery("select u from User u");
        Query selectQuery = session.createQuery("from User u " +
                "where u.id > 3 and u.name like '%mit%'" +
                "order by u.name");
        List<User> list = selectQuery.list();

        list.forEach(s -> System.out.println(s.getName()));

        session.getTransaction().commit();
        session.close();

    }


}
