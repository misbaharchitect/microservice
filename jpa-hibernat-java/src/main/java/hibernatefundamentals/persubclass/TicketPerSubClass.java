package hibernatefundamentals.persubclass;


import javax.persistence.*;

@Entity
@Table(name = "TICKETS_SUBCLASS")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TicketPerSubClass {
    @Id
    @GeneratedValue
    private int id;
    private String number;

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
