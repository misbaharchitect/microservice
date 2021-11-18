package hibernatefundamentals.perconcreteclass;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "ONE_WAY_TICKETS_CONCRETE")
public class OneWayTicketPerConcreteClass extends TicketPerConcreteClass {

    private LocalDate latestDepartureDate;

    public LocalDate getLatestDepartureDate() {
        return latestDepartureDate;
    }

    public void setLatestDepartureDate(LocalDate latestDepartureDate) {
        this.latestDepartureDate = latestDepartureDate;
    }
}
