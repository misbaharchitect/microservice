package hibernatefundamentals.criteria;

import hibernatefundamentals.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MainSimpleJ {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.training");
        EntityManager em = emf.createEntityManager();
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

//        Query selectQuery = session.createQuery("select u from User u");
        /*Query selectQuery = session.createQuery("from User u") */
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        // Root lets us access properties
        Root<User> root = query.from(User.class);
        CriteriaQuery<User> select = query.select(root);

        TypedQuery<User> query1 = em.createQuery(select);
        List<User> list = query1.getResultList();

        list.forEach(s -> System.out.println(s.getName()));

        transaction.commit();
        em.close();

    }

}
