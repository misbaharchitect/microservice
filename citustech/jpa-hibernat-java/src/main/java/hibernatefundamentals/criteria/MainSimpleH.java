package hibernatefundamentals.criteria;

import hibernatefundamentals.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
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
        /*Query selectQuery = session.createQuery("from User u") */
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        // Root lets us access properties. eg. root.get("title")
        Root<User> root = query.from(User.class);
        CriteriaQuery<User> select = query.select(root);

        Query<User> query1 = session.createQuery(select);
        // query1.getResultList with JPA
        List<User> list = query1.list();

        list.forEach(s -> System.out.println(s.getName()));

        session.getTransaction().commit();
        session.close();

    }

}
