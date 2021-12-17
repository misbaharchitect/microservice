package hibernatefundamentals;

import hibernatefundamentals.singletable.OneWayTicket;
import hibernatefundamentals.singletable.ReturnTicket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class MainSingleTable {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.training");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        OneWayTicket oneWayTicket = new OneWayTicket();
        oneWayTicket.setNumber("AA1234");
        oneWayTicket.setLatestDepartureDate(LocalDate.of(2021, 3, 20));

        ReturnTicket returnTicket = new ReturnTicket();
        returnTicket.setNumber("BB5678");
        returnTicket.setLatestReturnDate(LocalDate.of(2022, 1, 31));

        em.persist(oneWayTicket);
        em.persist(returnTicket);

        em.getTransaction().commit();
        emf.close();
    }
}
