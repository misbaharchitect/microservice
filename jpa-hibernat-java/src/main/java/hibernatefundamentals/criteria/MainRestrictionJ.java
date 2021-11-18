package hibernatefundamentals.criteria;

import hibernatefundamentals.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

public class MainRestrictionJ {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.training");
        EntityManager em = emf.createEntityManager();
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

//        Query selectQuery = session.createQuery("select u from User u");
        /*Query selectQuery = session.createQuery("from User u " +
                "where u.id > 3 and u.name like '%mit%'" +
                "order by u.name");*/
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        // Hibernate way
       /* SimpleExpression idCriterion = Restrictions.ge("id", 3);
        SimpleExpression name = Restrictions.like("name", "%mit%");*/
        // Root lets us access properties
        Root<User> root = criteriaQuery.from(User.class);
        Path<Integer> idPath = root.get("id");
        Path<String> namePath = root.get("name");

        criteriaQuery.select(root).where(
                cb.and(
                       cb.ge(idPath, 3),
                        cb.like(namePath, "%mit%")
                )
        );
        // order by
//        criteriaQuery.orderBy(cb.desc(namePath));

        TypedQuery<User> query1 = em.createQuery(criteriaQuery);
        List<User> list = query1.getResultList();

        list.forEach(s -> System.out.println(s.getName()));

        transaction.commit();
        em.close();

    }

}
