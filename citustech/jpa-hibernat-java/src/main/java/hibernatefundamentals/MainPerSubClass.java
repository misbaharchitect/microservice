package hibernatefundamentals;


import hibernatefundamentals.persubclass.OneWayTicketPerSubClass;
import hibernatefundamentals.persubclass.ReturnTicketPerSubClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class MainPerSubClass {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.training");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        OneWayTicketPerSubClass oneWayTicket = new OneWayTicketPerSubClass();
        oneWayTicket.setNumber("AA1234");
        oneWayTicket.setLatestDepartureDate(LocalDate.of(2021, 3, 20));

        ReturnTicketPerSubClass returnTicket = new ReturnTicketPerSubClass();
        returnTicket.setNumber("BB5678");
        returnTicket.setLatestReturnDate(LocalDate.of(2022, 1, 31));

        em.persist(oneWayTicket);
        em.persist(returnTicket);

        em.getTransaction().commit();
        emf.close();
    }
}
