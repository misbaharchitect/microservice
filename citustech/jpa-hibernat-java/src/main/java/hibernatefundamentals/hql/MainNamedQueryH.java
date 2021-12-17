package hibernatefundamentals.hql;

import hibernatefundamentals.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class MainNamedQueryH {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.training");
        EntityManager em = emf.createEntityManager();
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession(); // works

        Transaction transaction = session.beginTransaction();

        /*Query selectQuery = session.createQuery("from User u " +
                " where u.id > :userId and u.name like :userName " +
                " order by u.name");*/
        Query selectQuery = session.createNamedQuery("User.nameFilter");
        selectQuery.setParameter("userId", 3);
        selectQuery.setParameter("userName", "%mit%");

        List<User> list = selectQuery.list();

        list.forEach(s -> System.out.println(s.getName()));

        transaction.commit();
        session.close();

    }


}
