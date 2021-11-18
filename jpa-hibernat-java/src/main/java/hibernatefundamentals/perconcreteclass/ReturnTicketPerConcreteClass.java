package hibernatefundamentals.perconcreteclass;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "RETURN_TICKETS_CONCRETE")
public class ReturnTicketPerConcreteClass extends TicketPerConcreteClass {

    private LocalDate latestReturnDate;

    public LocalDate getLatestReturnDate() {
        return latestReturnDate;
    }

    public void setLatestReturnDate(LocalDate latestReturnDate) {
        this.latestReturnDate = latestReturnDate;
    }
}
